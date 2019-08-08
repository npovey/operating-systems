// ThreadServer.java

// The idea is to take the given DateServer and change it
// to the server that creates a thread to run the given Work
// How to run:
// Step 1: open the terminal and execute ThreadServer.java and Worker.java
// javac Work.java
// javac ThreadServer.java
// java ThreadServer

// Step 2: Open the second terminal and execute DateClient.java
// np$ javac DateClient.java
// np$ java DateClient


// output:
// np$ javac DateClient.java
// np$ java DateClient
// Tue Apr 23 13:36:47 PDT 2019
// np$ java DateClient
// Tue Apr 23 13:36:49 PDT 2019
// np$ java DateClient
// Tue Apr 23 13:36:51 PDT 2019
// np$ java DateClient
// Tue Apr 23 13:36:52 PDT 2019
// np$ java DateClient
// Tue Apr 23 13:36:53 PDT 2019
// np$ java DateClient
// Tue Apr 23 13:36:54 PDT 2019
// np$ java DateClient
// Tue Apr 23 13:36:55 PDT 2019
// np$

import java.net.*;
import java.io.*;

public class ThreadServer {
    public static void main(String[] args) {
        Thread ts;
        try {
            ServerSocket sock = new ServerSocket(6013);
            // listen to the given socket
            while (true) {
              Socket client = sock.accept();
              // must pass client info to the Work
              // System.out.println("Created : " + task.getClient());
              ts = new Thread(new Work(client));
              ts.start();
            }
        } catch (IOException ioe) {
          System.err.println(ioe);
        }
     }
}
