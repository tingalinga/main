package seedu.address.commons.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import jfxtras.icalendarfx.components.VEvent;
import jfxtras.icalendarfx.properties.component.recurrence.RecurrenceRule;
import seedu.address.model.event.Event;
import seedu.address.model.event.RecurrenceType;

public class EventUtil {
    public static final String DAILY_RECUR_RULE = "FREQ=DAILY;INTERVAL=1";
    public static final String WEEKLY_RECUR_RULE = "FREQ=WEEKLY;INTERVAL=1";
    public static final String NO_RECUR_RULE = "FREQ=YEARLY;COUNT=1";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd[ HH:mm:ss]").parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR,0).parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();

    public static final String INVALID_RECUR_TYPE = "Invalid recurrence type!";

    public static final String BAD_DATE_FORMAT = "Invalid DateTime format.";

    public static boolean validateDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return startDateTime.compareTo(endDateTime) < 0;
    }

    /**
     * Maps local event to iCalendar APIs VEvent
     */
    public static VEvent eventToVEventMapper(Event event) {
        VEvent mappedVEvent = new VEvent();
        mappedVEvent.setDateTimeEnd(event.getEndDateTime());
        mappedVEvent.setDateTimeStart(event.getStartDateTime());
        mappedVEvent.setUniqueIdentifier(event.getUniqueIdentifier());
        mappedVEvent.setSummary(event.getEventName());
        if (event.getRecurrenceType() == RecurrenceType.DAILY) {
            mappedVEvent.setRecurrenceRule(DAILY_RECUR_RULE);
        } else if (event.getRecurrenceType() == RecurrenceType.WEEKLY) {
            mappedVEvent.setRecurrenceRule(WEEKLY_RECUR_RULE);
        } else if (event.getRecurrenceType() == RecurrenceType.NONE) {
            mappedVEvent.setRecurrenceRule(NO_RECUR_RULE);
        }
        mappedVEvent.withCategories(event.getColorCode());

        return mappedVEvent;
    }

    /**
     * Maps iCalendar APIs VEvent to local event class
     */
    public static Event vEventToEventMapper(VEvent vEvent) {
        Event mappedEvent = new Event();
        mappedEvent.setEndTime(LocalDateTime.parse(vEvent.getDateTimeEnd().getValue().toString()));
        mappedEvent.setStartTime(LocalDateTime.parse(vEvent.getDateTimeStart().getValue().toString()));
        mappedEvent.setUniqueIdentifier(vEvent.getUniqueIdentifier().toString());
        mappedEvent.setEventName(vEvent.getSummary().toString());
        RecurrenceRule vEventRule = vEvent.getRecurrenceRule();
        if (vEventRule.toString().contains("DAILY")) {
            mappedEvent.setRecurrenceType(RecurrenceType.DAILY);
        } else if (vEventRule.toString().contains("WEEKLY")) {
            mappedEvent.setRecurrenceType(RecurrenceType.WEEKLY);
        } else {
            mappedEvent.setRecurrenceType(RecurrenceType.NONE);
        }
        mappedEvent.setColorCode(vEvent.getCategories().get(0).getValue().get(0));

        return mappedEvent;
    }

    /**
     * Checks if 2 VEvents are equal. They are equal if the attributes eventName, start and end date time are the same
     * @param vEvent1 first vevent
     * @param vEvent2 second vevent
     * @return true if equal
     */
    public static boolean isEqual(VEvent vEvent1, VEvent vEvent2) {
        return vEvent1.getSummary().equals(vEvent2.getSummary())
                && vEvent1.getDateTimeStart().equals(vEvent2.getDateTimeEnd())
                && vEvent2.getDateTimeEnd().equals(vEvent2.getDateTimeEnd());
    }

}
