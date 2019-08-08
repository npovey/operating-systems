// Driver.java 
// needs FileMonitor.java
// A file is to be shared among different processes,
// each of which has a unique number. The file can be
// accessed simultaneously by several processes, subject
// to the following constraint: the sum of all unique
// numbers associated with all the processes currently
// accessing the file must be less than n. Write a monitor
// to coordinate access to the file.

import java.io.*;               // for File
import java.util.*;             // for Scanner
import java.lang.management.*;
import java.io.*;

public class Driver {
    public static void main(String[] args)throws FileNotFoundException {
      FileMonitor fm = new FileMonitor();
      for (int i = 0; i < 20; i++) {
        int unique_number = 1000 + 2*i;
        File my_file = new File("source.txt");

        fm.acquire(unique_number);
        fm.acquire(unique_number);
        fm.acquire(unique_number);
        fm.acquire(unique_number);
        fm.acquire(unique_number);
        fm.acquire(unique_number);
        fm.acquire(unique_number);

        System.out.println("fm.acquire()");
        Scanner input = new Scanner(my_file);
        while (input.hasNext()) {
          String name = input.next();
          System.out.println(name);
        }
        fm.release(unique_number);
        System.out.println("fm.release()");
      }
    }
}
