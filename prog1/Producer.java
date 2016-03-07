/**
 * Name:           Samantha Fadrigalan
 * Project:        PA-1 (Producer-Consumer)
 * File:           Producer.java
 * Instructor:     Feng Chen
 * Class:          cs4103-sp16
 * LogonID:        cs410328
 */

import java.util.Random;


class Producer extends Thread {
    private static int NUM_PRODUCED = 0;
    private static int PRODUCER_ID = 0;
    private int numItemsToProduce;
    static EventLog EVENT_LOG = new EventLog("Producer");;
    ItemBuffer buffer;
    Random randGenerator;
    long startTime;

    public Producer (long startTime, ItemBuffer buffer, int numItemsToProduce) {
        PRODUCER_ID++;
        this.setName("P" + PRODUCER_ID);
        randGenerator = new Random();
        this.startTime = startTime;
        this.buffer = buffer;
        this.numItemsToProduce = numItemsToProduce;
    }

    public int getNumItemsProduced() {
        return NUM_PRODUCED;
    }

    public EventLog getEventLog() {
        return EVENT_LOG;
    }

    @Override
    public void run() {
        try {
            while (numItemsToProduce-- > 0) {
                NUM_PRODUCED++;
                int item = randGenerator.nextInt(Integer.MAX_VALUE);
                int buffID = buffer.add(item);
                EVENT_LOG.add(System.nanoTime() - startTime, this.getName(), buffID, item);
            }

        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
