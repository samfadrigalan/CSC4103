# Producer-Consumer Problem: Spring 2016 CSC 4101 Project 1
This project is written by Samantha Fadrigalan. This addresses the Producer-Consumer problem. I used Semaphores to fix the synchronization issue.
## Running the Project
1. Run `make all` to compile sources.
2. Run `java Main`.
3. Type your input in the following format:
```
<number of producer threads> <the number of consumer threads> <the size of the buffer> <the number of items to be produced>
```
The outputs a log file for the Producer and Consumer thread events. The format of the output is as follows
```
<Timestamp (in nanoseconds)> <Thread type (“Producer” or “Consumer”)> <Thread ID> <Buffer Entry Index> <Item>
```
## Comments
There are some issues with >1000 items to produce.

4. Run `make clean` to delete executables and log files.
