package vttp.ssf_prac2.model;

import vttp.ssf_prac2.util.Util;

public class Event {
    
    private int eventId;
    private String eventName;
    private int eventSize;
    private Long eventDate;
    private int participants;

    public int getEventId() {
        return eventId;
    }
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public int getEventSize() {
        return eventSize;
    }
    public void setEventSize(int eventSize) {
        this.eventSize = eventSize;
    }
    public Long getEventDate() {
        return eventDate;
    }
    public void setEventDate(Long eventDate) {
        this.eventDate = eventDate;
    }
    public int getParticipants() {
        return participants;
    }
    public void setParticipants(int participants) {
        this.participants = participants;
    }
    @Override
    public String toString() {
        return eventId + Util.delimiter 
                + eventName + Util.delimiter  
                + eventSize + Util.delimiter 
                + eventDate + Util.delimiter  
                + participants;
    }

    public void incrementParticipants(int delta){
        this.participants += delta;
    }
    
}
