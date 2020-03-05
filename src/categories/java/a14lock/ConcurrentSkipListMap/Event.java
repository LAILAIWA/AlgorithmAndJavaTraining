package categories.java.a14lock.ConcurrentSkipListMap;

import java.time.ZonedDateTime;

public class Event {
    private ZonedDateTime eventTime;
    private String content;

    public Event(ZonedDateTime eventTime, String content) {
        this.eventTime = eventTime;
        this.content = content;
    }

    public ZonedDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(ZonedDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
