package seedu.address.testutil.event;

import seedu.address.model.event.Event;
import seedu.address.model.event.RecurrenceType;

import java.time.LocalDateTime;

public class EventBuilder {

    public static final String DEFAULT_EVENT_NAME = "Event";
    public static final LocalDateTime DEFAULT_EVENT_START_TIME = LocalDateTime.now();
    public static final LocalDateTime DEFAULT_EVENT_END_TIME = LocalDateTime.now().plusHours(3);
    public static final String DEFAULT_COLOR_CODE = "group01";
    public static final String DEFAULT_UNIQUE_IDENTIFIER = "uniqueIdentifier" + LocalDateTime.now().toString();
    public static final RecurrenceType DEFAULT_RECUR_TYPE = RecurrenceType.NONE;

    private String eventName;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String colorCode;
    private String uniqueIdentifer;
    private RecurrenceType recurrenceType;

    public EventBuilder() {
        eventName = DEFAULT_EVENT_NAME;
        startDateTime = DEFAULT_EVENT_START_TIME;
        endDateTime = DEFAULT_EVENT_END_TIME;
        colorCode = DEFAULT_COLOR_CODE;
        uniqueIdentifer = DEFAULT_UNIQUE_IDENTIFIER;
        recurrenceType = DEFAULT_RECUR_TYPE;
    }

    public EventBuilder withEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public EventBuilder withStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
        return this;
    }

    public EventBuilder withEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
        return this;
    }

    public EventBuilder withColorCode(String colorCode) {
        this.colorCode = colorCode;
        return this;
    }

    public EventBuilder withUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifer = uniqueIdentifier;
        return this;
    }

    public EventBuilder withRecurrenceType(RecurrenceType recurrenceType) {
        this.recurrenceType = recurrenceType;
        return this;
    }

    public Event build() {
        return new Event(eventName, startDateTime, endDateTime, colorCode, uniqueIdentifer, recurrenceType);
    }
}
