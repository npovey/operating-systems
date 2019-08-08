// processes.cpp
// This program is in a way how the shell works.
// Shell would be running in an infinite loop and waiting for the user input.
// Once it has an input it would fork a process that would call exec()
// To compile: g++ processes.cpp -o processes   or g++ processes.cpp
// To execute: ./processes python or ./a.out python
// The output must be identical to the commad: ps -A | grep python | wc -l
//
//                                    Parent
//
//              ------------                  -----------
//  child #1 --|W  pipe12  R|--->child #2 --->|W pipe23 R |---->child #3
//              ------------                   -----------
//  (ps -A)                    (grep argv[1])                 (wc -l)

// Example 2:
// processes sshd
// ps -A | grep sshd | wc -l
// Example 3:
// processes scsi
// ps -A | grep scsi | wc -l

#include <stdlib.h> //exit
#include <stdio.h> // err
#include <unistd.h> // fork, pipe
#include <sys/wait.h> // wait
#include <iostream>
#include <assert.h>
#include <string>

using namespace std;

int main(int  argc, char* argv[]) {
  enum {R, W};
  pid_t pid1;
  pid_t pid2;
  pid_t pid3;
  int pipe12FD[2];   // pipe between child 1 and 2
  int pipe23FD[2];   // pipie between child 2 and 3

  if (pipe(pipe12FD) < 0) {
    perror("ERROR: Can't create pipe");
    exit(EXIT_FAILURE);
  }

  // int read_fd_val = pipe12FD[0];
  // cout << "read_fd_val: " << read_fd_val << endl;

  // int write_fd_val = pipe12FD[1];
  // cout << "write_fd_val: " << write_fd_val << endl;

  if (pipe(pipe23FD) < 0) {
    perror("ERROR: Can't create pipe");
    exit(EXIT_FAILURE);
  }


  // int read_fd_val2 = pipe23FD[0];
  // cout << "read_fd_val2: " << read_fd_val2 << endl;

  // int write_fd_val2 = pipe23FD[1];
  // cout << "write_fd_val2: " << write_fd_val2 << endl;

  pid1 = fork();
  if (pid1 == 0) {
    // cout << "I am child 1 doing pa -A." << endl;
    close(pipe23FD[R]);
    close(pipe23FD[W]);
    close(pipe12FD[R]);
    dup2(pipe12FD[W],STDOUT_FILENO); // stdout is now child's read pipe
    close(pipe12FD[W]);
    int status = execlp("/bin/ps","ps","-A", NULL);

    // once execlp(..) called, the code below it will never be executed
    // assert(status == 0); // can use assert if wanted
    // in theory things can go wrong
    // the best practice is to exit()
    // cout << "execlp failed" << endl;
    exit(EXIT_FAILURE);
  }

  pid2 = fork();// middle does a lot of work
  if (pid2 == 0) {
    // cout << "I am child 2 doing grep argv[1]" << endl;
    close(pipe23FD[R]);
    close(pipe12FD[W]);
    dup2(pipe12FD[R], STDIN_FILENO);
    close(pipe12FD[R]);
    dup2(pipe23FD[W], STDOUT_FILENO);
    close(pipe23FD[W]);
    int status = execlp("/usr/bin/grep", "grep", argv[1], NULL);
    //assert(status == 0);
    exit(EXIT_FAILURE);
  }
  pid3 = fork();
  if(pid3 == 0) {
    //wait(NULL);
    // cout<<"I am child 3 doing wc -l" <<endl;
    close(pipe12FD[R]);
    close(pipe12FD[W]);
    close(pipe23FD[W]);
    dup2(pipe23FD[R], STDIN_FILENO);
    close(pipe23FD[R]);
    int status = execlp("/bin/wc","wc", "-l", NULL);
    // assert(status == 0);
    exit(EXIT_FAILURE);
  }
  close(pipe12FD[R]);
  close(pipe12FD[W]);
  close(pipe23FD[W]);
  close(pipe23FD[R]);

  // cout << "I am the parent and waiting for my third child to finish" << endl;
  wait(NULL); // waiting for all children to complete
  wait(NULL);
  wait(NULL);
  // cout<<"Parent exiting"<<endl;
  exit(EXIT_SUCCESS);
}


/*
$ emacs processes.cpp
$ g++ processes.cpp -o processes
$ ./processes sshd
read_fd_val: 3
write_fd_val: 4
read_fd_val2: 5
write_fd_val2: 6
I am child 1 doing pa -A.
I am the parent and waiting for my third child to finish
I am child 2 doing grep [argv[1]
I am child 3 doing wc -l
Parent exiting
7
*/
