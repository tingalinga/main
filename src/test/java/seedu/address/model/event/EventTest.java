package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.event.TypicalEvents.EVENT1;
import static seedu.address.testutil.event.TypicalEvents.EVENT2;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.event.EventBuilder;


public class EventTest {
    public static final String EMPTY_EVENT_NAME = "";
    public static final String VALID_EVENT_NAME = "Consultation";
    public static final LocalDateTime INVALID_DATE_TIME_START = LocalDateTime.parse("2020-04-08T12:00");
    public static final LocalDateTime INVALID_DATE_TIME_END = LocalDateTime.parse("2020-04-08T10:00");
    public static final LocalDateTime VALID_DATE_TIME_START = LocalDateTime.parse("2020-04-08T10:00");
    public static final LocalDateTime VALID_DATE_TIME_END = LocalDateTime.parse("2020-04-08T12:00");
    public static final String INVALID_COLOR_CODE = "group55";
    public static final String VALID_COLOR_CODE = "group02";
    public static final String VALID_UNIQUE_IDENTIFIER = "validUniqueIdentifier";

    @Test
    public void constructor_nullEventName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(null, VALID_DATE_TIME_START,
                VALID_DATE_TIME_END, VALID_COLOR_CODE, VALID_UNIQUE_IDENTIFIER, RecurrenceType.NONE));
    }

    @Test
    public void constructor_emptyEventName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Event(EMPTY_EVENT_NAME, VALID_DATE_TIME_START,
                VALID_DATE_TIME_END, VALID_COLOR_CODE, VALID_UNIQUE_IDENTIFIER, RecurrenceType.NONE));
    }

    @Test
    public void constructor_nullDateTimeStart_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(VALID_EVENT_NAME, null,
                VALID_DATE_TIME_END, VALID_COLOR_CODE, VALID_UNIQUE_IDENTIFIER, RecurrenceType.NONE));
    }

    @Test
    public void constructor_invalidDateTimeStart_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Event(VALID_EVENT_NAME, INVALID_DATE_TIME_START,
                VALID_DATE_TIME_END, VALID_COLOR_CODE, VALID_UNIQUE_IDENTIFIER, RecurrenceType.NONE));
    }

    @Test
    public void constructor_nullDateTimeEnd_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(VALID_EVENT_NAME, VALID_DATE_TIME_START,
                null, VALID_COLOR_CODE, VALID_UNIQUE_IDENTIFIER, RecurrenceType.NONE));
    }

    @Test
    public void constructor_invalidDateTimeEnd_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Event(VALID_EVENT_NAME, VALID_DATE_TIME_START,
                INVALID_DATE_TIME_END, VALID_COLOR_CODE, VALID_UNIQUE_IDENTIFIER, RecurrenceType.NONE));
    }

    @Test
    public void constructor_invalidDateTimeRange_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Event(VALID_EVENT_NAME, INVALID_DATE_TIME_START,
                INVALID_DATE_TIME_END, VALID_COLOR_CODE, VALID_UNIQUE_IDENTIFIER, RecurrenceType.NONE));
    }

    @Test
    public void constructor_nullColorCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(VALID_EVENT_NAME, VALID_DATE_TIME_START,
                VALID_DATE_TIME_END, null, VALID_UNIQUE_IDENTIFIER, RecurrenceType.NONE));
    }

    @Test
    public void constructor_invalidColorCode_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Event(VALID_EVENT_NAME, VALID_DATE_TIME_START,
                VALID_DATE_TIME_END, INVALID_COLOR_CODE, VALID_UNIQUE_IDENTIFIER, RecurrenceType.NONE));
    }

    @Test
    public void constructor_nullUniqueIdentifier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(VALID_EVENT_NAME, VALID_DATE_TIME_START,
                VALID_DATE_TIME_END, VALID_COLOR_CODE, null, RecurrenceType.NONE));
    }

    @Test
    public void constructor_nullRecurrenceType_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(VALID_EVENT_NAME, VALID_DATE_TIME_START,
                VALID_DATE_TIME_END, VALID_COLOR_CODE, VALID_UNIQUE_IDENTIFIER, null));
    }

    @Test
    public void equals() {
        Event newEvent1 = new EventBuilder(EVENT1).withEventName(VALID_EVENT_NAME).build();
        // same object -> returns true
        assertTrue(EVENT1.equals(EVENT1));

        // null -> returns false
        assertFalse(EVENT1.equals(null));

        // different event -> return false
        assertFalse(EVENT1.equals(EVENT2));


        // different event name -> returns false
        assertFalse(EVENT1.equals(newEvent1));

        // different date time start name -> returns false
        newEvent1 = new EventBuilder(EVENT1).withStartDateTime(VALID_DATE_TIME_START)
                .withEndDateTime(VALID_DATE_TIME_END).build();
        assertFalse(newEvent1.equals(EVENT1));

        // different end time start name -> returns false
        newEvent1 = new EventBuilder(EVENT1).withEndDateTime(VALID_DATE_TIME_END).build();
        assertFalse(EVENT1.equals(newEvent1));

        // different color code -> returns false
        newEvent1 = new EventBuilder(EVENT1).withColorCode(EVENT2.getColorCode()).build();
        assertFalse(EVENT1.equals(newEvent1));

        // different unique identifier -> returns false
        newEvent1 = new EventBuilder(EVENT1).withUniqueIdentifier(VALID_UNIQUE_IDENTIFIER).build();
        assertFalse(EVENT1.equals(newEvent1));
    }


}

