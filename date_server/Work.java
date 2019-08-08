// The idea is to take the given DateServer and change it
// to the server that has a pool of threads on hand to do the work.
// This is how the modern servers organized. While testing we will witness
// the same behaviour, in real life this methhod is faster when server is busy.
// Each thread will be doing this work, which is to tell the client the date.
// Work.java
// How to run:
// javac ThreadPoolServer.java
// javac Work.java
// java ThreadPoolServer

import java.net.*;
import java.io.*;

public class Work implements Runnable {
    private Socket client;
    public Work(Socket client) {
        this.client = client;
    }
    public Socket getClient() {
        return client;
    }
    public void run() {
      try {
        PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
        pout.println(new java.util.Date().toString());

        // must close the soket
        client.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

}
