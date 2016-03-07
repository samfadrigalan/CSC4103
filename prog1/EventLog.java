/**
* Name:           Samantha Fadrigalan
* Project:        PA-1 (Producer-Consumer)
* File:           EventLog.java
* Instructor:     Feng Chen
* Class:          cs4103-sp16
* LogonID:        cs410328
*/

import java.util.LinkedList;
import java.util.Queue;

class EventLog {
    Queue<EventObject> queue;
    private String eventType;

    public EventLog(String eventType) {
        this.eventType = eventType;
        queue = new LinkedList<>();
    }

    public void add(long timeStamp, String threadID, int bufferIndex, int item) {
        queue.add(new EventObject(timeStamp, eventType, threadID, bufferIndex, item));
    }

    public int size() {
        return queue.size();
    }

    public String popTopEntry() {
        return queue.remove().toString();
    }
 }
