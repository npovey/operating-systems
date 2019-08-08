##### Date Server

The idea is to take the given DateServer and change it
to the server that creates a thread to run the given Work
How to run:
Step 1: open the terminal and execute ThreadServer.java and Worker.java
javac Work.java
javac ThreadServer.java
java ThreadServer

Step 2: Open the second terminal and execute DateClient.java
javac DateClient.java
java DateClient

```{python}
(base) [npovey@ka operating_systems]$ ls
date_server  file_monitor  fork  GANTT_CHART.numbers
(base) [npovey@ka operating_systems]$ cd date_server/
(base) [npovey@ka date_server]$ ls
DateClient.java  ThreadPoolServer.java  ThreadServer.java  Work.java
(base) [npovey@ka date_server]$ javac Work.java
(base) [npovey@ka date_server]$ javac ThreadServer.java
(base) [npovey@ka date_server]$ java ThreadServer
```





```{python}
(base) [npovey@ka date_server]$ ls
DateClient.java        ThreadServer.class  Work.class
ThreadPoolServer.java  ThreadServer.java   Work.java
(base) [npovey@ka date_server]$ javac DateClient.java
(base) [npovey@ka date_server]$ java DateClient
Wed Aug 07 21:25:47 EDT 2019
(base) [npovey@ka date_server]$ java DateClient
Wed Aug 07 21:25:50 EDT 2019
(base) [npovey@ka date_server]$ java DateClient
Wed Aug 07 21:25:51 EDT 2019
(base) [npovey@ka date_server]$ java DateClient
Wed Aug 07 21:25:53 EDT 2019
(base) [npovey@ka date_server]$ 

```

