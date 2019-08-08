// ThreadPoolServer.java

// The idea is to take the given DateServer and change it
// to the server that has a pool of threads on hand to do the work.
// This is how the modern servers organized. While testing we will witness
// the same behaviour, in real life this methhod is faster when server is busy.

// How to run:
// Step 1: open the terminal and execute ThreadPoolServer.java and Worker.java
// javac Work.java
// javac ThreadPoolServer.java
// java ThreadPoolServer

// Step 2: Open the second terminal and execute DateClient.java
// $ javac DateClient.java
// $ java DateClient


// output:
// $ javac DateClient.java
// $ java DateClient
// Tue Apr 23 13:36:47 PDT 2019
// $ java DateClient
// Tue Apr 23 13:36:49 PDT 2019
// $ java DateClient
// Tue Apr 23 13:36:51 PDT 2019
// $ java DateClient
// Tue Apr 23 13:36:52 PDT 2019
// $ java DateClient
// Tue Apr 23 13:36:53 PDT 2019
// $ java DateClient
// Tue Apr 23 13:36:54 PDT 2019
// $ java DateClient
// Tue Apr 23 13:36:55 PDT 2019
// $


import java.net.*;
import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor; // for fixed amount of threads
import java.util.concurrent.ExecutorService; // fo cached pool threads


public class ThreadPoolServer
{
    public static void main(String[] args)
    {
         // --- this method will give us a fixed amount threads------
        // ThreadPoolExecutor executor =
        //          (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        // --- this way computer can increase or decrease thread pool
        // depending on how busy the server is
        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            ServerSocket sock = new ServerSocket(6013);
            // listen to the given socket
            while (true) {
              Socket client = sock.accept();
              // must pass client info to the Task
              Work task = new Work(client);
              // System.out.println("Created : " + task.getClient());
              executor.execute(task);
              // try{
              // Thread.sleep(100);
              // } catch (InterruptedException ioe) {
              //     System.err.println(ioe);
              // }
              // client.close();
            }
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
        // ask: how to terminated a running servers
        // currently typing ctrl x c
        // if we are terminating like that will the code reach this
        // line ?
        // executor.shutdown();
     }
}



// np$ javac ThreadPoolServer.java
// np$ java ThreadPoolServer
// Created : Socket[addr=/127.0.0.1,port=57283,localport=6013]
// Created : Socket[addr=/127.0.0.1,port=57284,localport=6013]
// Created : Socket[addr=/127.0.0.1,port=57285,localport=6013]
// Created : Socket[addr=/127.0.0.1,port=57286,localport=6013]
// Created : Socket[addr=/127.0.0.1,port=57287,localport=6013]
// Created : Socket[addr=/127.0.0.1,port=57288,localport=6013]
// Created : Socket[addr=/127.0.0.1,port=57289,localport=6013]
// Created : Socket[addr=/127.0.0.1,port=57290,localport=6013]
