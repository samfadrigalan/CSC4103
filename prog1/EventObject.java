/**
* Name:           Samantha Fadrigalan
* Project:        PA-1 (Producer-Consumer)
* File:           EventObject.java
* Instructor:     Feng Chen
* Class:          cs4103-sp16
* LogonID:        cs410328
*/

class EventObject {
    private long timeStamp;
    private String eventType;
    private String threadID;
    private int bufferIndex;
    private int item;


    public EventObject(long timeStamp, String eventType, String threadID, int bufferIndex, int item) {
        this.timeStamp = timeStamp;
        this. eventType = eventType;
        this.threadID = threadID;
        this.bufferIndex = bufferIndex;
        this.item = item;
    }

    public String toString() {
        return String.format("%d %s %s %d %d\n", timeStamp, eventType, threadID, bufferIndex, item);
    }
}
