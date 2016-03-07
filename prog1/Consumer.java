/**
* Name:           Samantha Fadrigalan
* Project:        PA-1 (Producer-Consumer)
* File:           Consumer.java
* Instructor:     Feng Chen
* Class:          cs4103-sp16
* LogonID:        cs410328
*/

import java.util.Random;


/*
* <Timestamp (in nanoseconds)> <Thread type (“Producer” or “Consumer”)> <Thread ID> <Buffer Entry Index> <Item>
* */
public class Consumer extends Thread {
    private static int NUM_CONSUMED = 0;
    private static int CONSUMER_ID = 0;
    private int numItemsToConsume;
    static EventLog EVENT_LOG = new EventLog("Consumer");
    ItemBuffer buffer;
    Random randGenerator;
    long startTime;

    public Consumer (long startTime, ItemBuffer buffer, int numItemsToConsume) {
        CONSUMER_ID++;
        this.setName("C" + CONSUMER_ID);
        this.startTime = startTime;
        randGenerator = new Random();
        this.buffer = buffer;
        this.numItemsToConsume = numItemsToConsume;
    }

    public int getNumItemsConsumed() {
        return NUM_CONSUMED;
    }

    public EventLog getEventLog() {
        return EVENT_LOG;
    }

    @Override
    public void run() {
        try {
            while (numItemsToConsume-- > 0) {
                NUM_CONSUMED++;
                BufferObject buffObj = buffer.remove();
                EVENT_LOG.add(System.nanoTime() - startTime, this.getName(), buffObj.ID, buffObj.item);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
