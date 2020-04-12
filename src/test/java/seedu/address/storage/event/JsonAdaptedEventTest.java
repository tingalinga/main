package seedu.address.storage.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.event.JsonAdaptedEvent.INVALID_DATE_FORMAT;
import static seedu.address.storage.event.JsonAdaptedEvent.INVALID_DATE_RANGE;
import static seedu.address.storage.event.JsonAdaptedEvent.INVALID_RECUR_TYPE;
import static seedu.address.storage.event.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Event;
import seedu.address.model.event.RecurrenceType;
import seedu.address.testutil.event.EventBuilder;





public class JsonAdaptedEventTest {

    private static final String EMPTY_EVENT_NAME = "";
    private static final String INVALID_FORMAT_START_DATE_TIME = "2020-9999T33:00";
    private static final String INVALID_FORMAT_END_DATE_TIME = "2020-01-01r3:00";
    private static final String INVALID_RANGE_START_DATE_TIME = "2020-04-04T03:00";
    private static final String INVALID_RANGE_END_DATE_TIME = "2020-04-04T02:00";
    private static final String INVALID_COLOR_CATEGORY = "Invalid date range! Start date must be before end date";
    private static final String EMPTY_COLOR_CATEGORY = "";
    private static final String EMPTY_UNIQUE_IDENTIFIER = "";
    private static final String EMPTY_RECURRENCE_TYPE_STRING = "";
    private static final String INVALID_RECURRENCE_TYPE_STRING = "INVALID RECUR";
    private static final String EMPTY_START_DATE_TIME = "";
    private static final String EMPTY_END_DATE_TIME = "";


    private static final String VALID_EVENT_NAME = "event name";
    private static final String VALID_START_DATE_TIME = "2020-04-04T03:00";
    private static final String VALID_END_DATE_TIME = "2020-04-04T04:00";
    private static final String VALID_UNIQUE_IDENTIFIER = "id";
    private static final String VALID_COLOR_CODE = "group01";
    private static final String VALID_RECURRENCE_TYPE_STRING = "NONE";

    private static final RecurrenceType VALID_RECURRENCE_TYPE = RecurrenceType.NONE;

    private static final Event EVENT = new EventBuilder()
            .withStartDateTime(LocalDateTime.parse(VALID_START_DATE_TIME))
            .withEndDateTime(LocalDateTime.parse(VALID_END_DATE_TIME))
            .withEventName(VALID_EVENT_NAME)
            .withUniqueIdentifier(VALID_UNIQUE_IDENTIFIER)
            .withColorCode(VALID_COLOR_CODE)
            .withRecurrenceType(VALID_RECURRENCE_TYPE)
            .build();

    @Test
    public void toModelType_emptyEventName_throwsIllegalValueException() {
        JsonAdaptedEvent jsonEvent = new JsonAdaptedEvent(EMPTY_EVENT_NAME, VALID_START_DATE_TIME, VALID_END_DATE_TIME,
                VALID_UNIQUE_IDENTIFIER, VALID_RECURRENCE_TYPE_STRING, VALID_COLOR_CODE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "EVENT NAME");
        assertThrows(IllegalValueException.class, expectedMessage, jsonEvent::toModelType);
    }

    @Test
    public void toModelType_nullEventName_throwsIllegalValueException() {
        JsonAdaptedEvent jsonEvent = new JsonAdaptedEvent(null, VALID_START_DATE_TIME, VALID_END_DATE_TIME,
                VALID_UNIQUE_IDENTIFIER, VALID_RECURRENCE_TYPE_STRING, VALID_COLOR_CODE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "EVENT NAME");
        assertThrows(IllegalValueException.class, expectedMessage, jsonEvent::toModelType);
    }

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent jsonEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, VALID_START_DATE_TIME, VALID_END_DATE_TIME,
                VALID_UNIQUE_IDENTIFIER, VALID_RECURRENCE_TYPE_STRING, VALID_COLOR_CODE);
        assertEquals(EVENT, jsonEvent.toModelType());
    }

    @Test
    public void toModelType_invalidStartDateTime_throwsIllegalValueException() {
        JsonAdaptedEvent jsonEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, INVALID_FORMAT_START_DATE_TIME,
                VALID_END_DATE_TIME,
                VALID_UNIQUE_IDENTIFIER, VALID_RECURRENCE_TYPE_STRING, VALID_COLOR_CODE);
        assertThrows(IllegalValueException.class, INVALID_DATE_FORMAT, jsonEvent::toModelType);
    }

    @Test
    public void toModelType_nullStartDateTime_throwsIllegalValueException() {
        JsonAdaptedEvent jsonEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, null, VALID_END_DATE_TIME,
                VALID_UNIQUE_IDENTIFIER, VALID_RECURRENCE_TYPE_STRING, VALID_COLOR_CODE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "START DATE TIME");
        assertThrows(IllegalValueException.class, expectedMessage, jsonEvent::toModelType);
    }

    @Test
    public void toModelType_emptyStartDateTime_throwsIllegalValueException() {
        JsonAdaptedEvent jsonEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, EMPTY_START_DATE_TIME, VALID_END_DATE_TIME,
                VALID_UNIQUE_IDENTIFIER, VALID_RECURRENCE_TYPE_STRING, VALID_COLOR_CODE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "START DATE TIME");
        assertThrows(IllegalValueException.class, expectedMessage, jsonEvent::toModelType);
    }

    @Test
    public void toModelType_invalidEndDateTime_throwsIllegalValueException() {
        JsonAdaptedEvent jsonEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, VALID_START_DATE_TIME,
                INVALID_FORMAT_END_DATE_TIME, VALID_UNIQUE_IDENTIFIER, VALID_RECURRENCE_TYPE_STRING, VALID_COLOR_CODE);
        assertThrows(IllegalValueException.class, INVALID_DATE_FORMAT, jsonEvent::toModelType);
    }

    @Test
    public void toModelType_nullEndDateTime_throwsIllegalValueException() {
        JsonAdaptedEvent jsonEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, VALID_START_DATE_TIME,
                null, VALID_UNIQUE_IDENTIFIER, VALID_RECURRENCE_TYPE_STRING, VALID_COLOR_CODE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "END DATE TIME");
        assertThrows(IllegalValueException.class, expectedMessage, jsonEvent::toModelType);
    }

    @Test
    public void toModelType_emptyEndDateTime_throwsIllegalValueException() {
        JsonAdaptedEvent jsonEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, VALID_START_DATE_TIME,
                EMPTY_END_DATE_TIME, VALID_UNIQUE_IDENTIFIER, VALID_RECURRENCE_TYPE_STRING, VALID_COLOR_CODE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "END DATE TIME");
        assertThrows(IllegalValueException.class, expectedMessage, jsonEvent::toModelType);
    }

    @Test
    public void toModelType_invalidEventRange_throwsIllegalValueException() {
        JsonAdaptedEvent jsonEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, INVALID_RANGE_START_DATE_TIME,
                INVALID_RANGE_END_DATE_TIME, VALID_UNIQUE_IDENTIFIER, VALID_RECURRENCE_TYPE_STRING, VALID_COLOR_CODE);
        assertThrows(IllegalValueException.class, INVALID_DATE_RANGE, jsonEvent::toModelType);
    }

    @Test
    public void toModelType_nullColorCategory_throwsIllegalValueException() {
        JsonAdaptedEvent jsonEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, INVALID_RANGE_START_DATE_TIME,
                INVALID_RANGE_END_DATE_TIME, VALID_UNIQUE_IDENTIFIER, VALID_RECURRENCE_TYPE_STRING, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "COLOR CODE");
        assertThrows(IllegalValueException.class, expectedMessage, jsonEvent::toModelType);
    }

    @Test
    public void toModelType_invalidColorCategory_throwsIllegalValueException() {
        JsonAdaptedEvent jsonEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, INVALID_RANGE_START_DATE_TIME,
                INVALID_RANGE_END_DATE_TIME, VALID_UNIQUE_IDENTIFIER, VALID_RECURRENCE_TYPE_STRING,
                INVALID_COLOR_CATEGORY);
        assertThrows(IllegalValueException.class, INVALID_COLOR_CATEGORY, jsonEvent::toModelType);
    }

    @Test
    public void toModelType_emptyColorCategory_throwsIllegalValueException() {
        JsonAdaptedEvent jsonEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, INVALID_RANGE_START_DATE_TIME,
                INVALID_RANGE_END_DATE_TIME, VALID_UNIQUE_IDENTIFIER, VALID_RECURRENCE_TYPE_STRING,
                EMPTY_COLOR_CATEGORY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "COLOR CODE");
        assertThrows(IllegalValueException.class, expectedMessage, jsonEvent::toModelType);
    }

    @Test
    public void toModelType_nullUniqueIdentifier_throwsIllegalValueException() {
        JsonAdaptedEvent jsonEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, INVALID_RANGE_START_DATE_TIME,
                INVALID_RANGE_END_DATE_TIME, null, VALID_RECURRENCE_TYPE_STRING,
                EMPTY_COLOR_CATEGORY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "UNIQUE IDENTIFIER");
        assertThrows(IllegalValueException.class, expectedMessage, jsonEvent::toModelType);
    }

    @Test
    public void toModelType_emptyUniqueIdentifier_throwsIllegalValueException() {
        JsonAdaptedEvent jsonEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, VALID_START_DATE_TIME,
                VALID_END_DATE_TIME, EMPTY_UNIQUE_IDENTIFIER, VALID_RECURRENCE_TYPE_STRING,
                VALID_COLOR_CODE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "UNIQUE IDENTIFIER");
        assertThrows(IllegalValueException.class, expectedMessage, jsonEvent::toModelType);
    }

    @Test
    public void toModelType_invalidRecurrenceType_throwsIllegalValueException() {
        JsonAdaptedEvent jsonEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, VALID_START_DATE_TIME,
                VALID_END_DATE_TIME, VALID_UNIQUE_IDENTIFIER, INVALID_RECURRENCE_TYPE_STRING,
                VALID_COLOR_CODE);
        assertThrows(IllegalValueException.class, INVALID_RECUR_TYPE, jsonEvent::toModelType);
    }

    @Test
    public void toModelType_nullRecurrenceType_throwsIllegalValueException() {
        JsonAdaptedEvent jsonEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, VALID_START_DATE_TIME,
                VALID_END_DATE_TIME, VALID_UNIQUE_IDENTIFIER, null,
                VALID_COLOR_CODE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "RECURRENCE TYPE");
        assertThrows(IllegalValueException.class, expectedMessage, jsonEvent::toModelType);
    }
}
