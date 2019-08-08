// Monitor.java
// works with Driver.java
// A file is to be shared among different processes,
// each of which has a unique number. The file can be
// accessed simultaneously by several processes, subject
// to the following constraint: the sum of all unique
// numbers associated with all the processes currently
// accessing the file must be less than n. Write a monitor
// to coordinate access to the file.

import java.util.*;             // for Scanner
import java.io.*;
import java.lang.management.*;

public class FileMonitor {
  int max_count;
  int count;

  public FileMonitor () {
    max_count = 5000;
    count = 0;
  }
  // String pid = ManagementFactory.getRuntimeMXBean().getName();
  // long current_pid = Long.valueOf(pid.split("@")[0]);
  // int pid1 = (int) current_pid;
  // System.out.println("pid =" + pid1);


  public synchronized void acquire(int unique_number) {
    System.out.println("unique_number" + unique_number);

    if(count + unique_number < max_count) {
        count = count + unique_number;
        // System.out.println("count" + count);

    } else {
      try {
        // System.out.println("waiting");
        this.wait( );
      } catch ( InterruptedException iex ) {}
    }

  }

  public synchronized void release(int unique_number) {
      count = count - unique_number;
      // System.out.println("countn" + count);
      notify();
  }

}
