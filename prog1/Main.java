/**
* Name:           Samantha Fadrigalan
* Project:        PA-1 (Producer-Consumer)
* File:           Main.java
* Instructor:     Feng Chen
* Class:          cs4103-sp16
* LogonID:        cs410328
*/

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main extends Thread{
    static Scanner in;
    static long startTime;
    static int numProducers, numConsumers, bufferSize, numItems;
    static ItemBuffer buffer;
    static Producer producers[];
    static Consumer consumers[];
    static File producerLogFile, consumerLogFile;


    static void init() throws IOException{
        producerLogFile = new File("producer-event.log");
        consumerLogFile = new File("consumer-event.log");
        producerLogFile.createNewFile();
        consumerLogFile.createNewFile();
        in = new Scanner(System.in);
    }

    static void input() {
        numProducers = in.nextInt();
        numConsumers = in.nextInt();
        bufferSize = in.nextInt();
        numItems = in.nextInt();
    }

    static void initBuffer() {
        buffer = new ItemBuffer(bufferSize);
        startTime = System.nanoTime();
    }

    static void createProducers() {
        producers = new Producer[numProducers];

        if(numItems > numProducers) {
            int itemsPerProducer = numItems / numProducers;
            int remainder = numItems % numProducers;

            /* Add the remainder to one Producer Thread */
            producers[0] = new Producer(startTime, buffer, itemsPerProducer + remainder);
            for (int i=1; i < producers.length; i++) {
                producers[i] = new Producer(startTime, buffer, itemsPerProducer);
            }
        }
        else {
            /* If numItems < numProducers, only allocate 0 items to create to remaining Producer Threads */
            int counter;
            for (counter = 0; counter < numItems; counter++) {
                producers[counter] = new Producer(startTime, buffer, 1);
            }
            for ( ; counter < producers.length; counter++) {
                producers[counter] = new Producer(startTime, buffer, 0);
            }
        }

    }

    static void createConsumers() {
        consumers = new Consumer[numConsumers];

        if(numItems > numConsumers) {
            int itemsPerConsumer = numItems / numConsumers;
            int remainder = numItems % numConsumers;

            /* Add the remainder to one Consumer Thread */
            consumers[0] = new Consumer(startTime, buffer, itemsPerConsumer + remainder);
            for (int i=1; i < consumers.length; i++) {
                consumers[i] = new Consumer(startTime, buffer, itemsPerConsumer);
            }
        }
        else {
            /* If numItems < numConsumers, only allocate 0 items to create to remaining Consumer Threads */
            int counter;
            for (counter = 0; counter < numItems; counter++) {
                consumers[counter] = new Consumer(startTime, buffer, 1);
            }
            for ( ; counter < consumers.length; counter++) {
                consumers[counter] = new Consumer(startTime, buffer, 0);
            }
        }
    }

    static void createThreads() {
        createProducers();
        createConsumers();
    }

    static void startThreads() throws InterruptedException{
        for(int i=0; i<producers.length; i++) {
            producers[i].start();
        }
        for(int i=0; i<consumers.length; i++) {
            consumers[i].start();
        }

        /* make sure threads finish before moving onto next task */
        for(int i=0; i<producers.length; i++) {
            producers[i].join();
        }

        for(int i=0; i<consumers.length; i++) {
            consumers[i].join();
        }
    }


    static void output() throws IOException{
        FileWriter pLogWriter = new FileWriter(producerLogFile);
        FileWriter cLogWriter = new FileWriter(consumerLogFile);
        EventLog producerLog = producers[0].getEventLog();
        EventLog consumerLog = consumers[0].getEventLog();

        System.out.printf("Number of items produced: %d \n", producers[0].getNumItemsProduced());
        System.out.printf("Number of items consumed: %d \n", consumers[0].getNumItemsConsumed());

        while (producerLog.size() > 0) {
            pLogWriter.write(producerLog.popTopEntry());
        }
        pLogWriter.flush();
        pLogWriter.close();

        while (consumerLog.size() > 0) {
            cLogWriter.write(consumerLog.popTopEntry());
        }
        cLogWriter.flush();
        cLogWriter.close();
    }


    public static void main(String[] args) throws IOException, InterruptedException{
	// write your code here
        init();
        input();
        initBuffer();
        createThreads();
        startThreads();
        output();
    }
}
