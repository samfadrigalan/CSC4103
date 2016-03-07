/**
* Name:           Samantha Fadrigalan
* Project:        PA-1 (Producer-Consumer)
* File:           ItemBuffer.java
* Instructor:     Feng Chen
* Class:          cs4103-sp16
* LogonID:        cs410328
*/

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

class ItemBuffer {
    private Queue<BufferObject> queue = new LinkedList<>();
    private static int BUFFER_ID = 0;
    private int MAX_SIZE;
    private static Semaphore semProd = new Semaphore(1);
    private static Semaphore semCon = new Semaphore(0);

    public ItemBuffer(int max_size) {
        this.MAX_SIZE = max_size;
    }

    public int add(int item) throws InterruptedException {
        if(queue.size() == MAX_SIZE) {
            semCon.release();
        }

        semProd.acquire();
        BUFFER_ID++;
        // System.out.println(Thread.currentThread().getName()+" Putting(In Q) Product ID:"+ BUFFER_ID);
        queue.add(new BufferObject(item, BUFFER_ID));
        semCon.release();

        return BUFFER_ID;
    }

    public BufferObject remove() throws InterruptedException {
        semCon.acquire();
        queue.peek();
        String str = String.format(Thread.currentThread().getName()+ " Getting(From Q) Product ID:" + queue.peek().ID);
        BufferObject retVal = queue.remove();
        // System.out.println(str);
        semProd.release();
        return retVal;
    }
}
