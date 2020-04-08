package seedu.address.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_EVENT;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jfxtras.icalendarfx.components.VEvent;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.event.ModelStubWithVEvent;





public class EventAddCommandTest {

    public static final String VALID_EVENT_NAME = "Consultation";
    public static final String VALID_OTHER_EVENT_NAME = "Consultation again";
    public static final LocalDateTime INVALID_DATE_TIME_START = LocalDateTime.parse("2020-04-08T12:00");
    public static final LocalDateTime INVALID_DATE_TIME_END = LocalDateTime.parse("2020-04-08T10:00");
    public static final LocalDateTime VALID_DATE_TIME_START = LocalDateTime.parse("2020-04-08T10:00");
    public static final LocalDateTime VALID_DATE_TIME_END = LocalDateTime.parse("2020-04-08T12:00");
    public static final String INVALID_COLOR_CODE = "group55";
    public static final String VALID_COLOR_CODE = "group02";
    public static final String VALID_UNIQUE_IDENTIFIER = "validUniqueIdentifier";

    @Test
    public void constructor_nullVEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventAddCommand(null));
    }

    @Test
    public void equals() {
        VEvent myVEvent = new VEvent().withSummary(VALID_EVENT_NAME);
        VEvent otherVEvent = new VEvent().withSummary(VALID_OTHER_EVENT_NAME);
        EventAddCommand myEventAddCommand = new EventAddCommand(myVEvent);
        EventAddCommand otherEventAddCommand = new EventAddCommand(otherVEvent);

        // same object -> return true
        assertTrue(myEventAddCommand.equals(myEventAddCommand));

        //same val -> return true
        EventAddCommand copiedEventAddCommand = new EventAddCommand(myVEvent);
        assertTrue(myEventAddCommand.equals(copiedEventAddCommand));

        // null -> return false
        assertFalse(myEventAddCommand.equals(null));

        // different type -> return false
        assertFalse(myEventAddCommand.equals(0));

        // different event -> return false
        assertFalse(myEventAddCommand.equals(otherEventAddCommand));
    }

    @Test
    public void execute_duplicateVEvent_throwsCommandException() {
        VEvent vEvent = new VEvent().withSummary(VALID_EVENT_NAME).withDateTimeStart(VALID_DATE_TIME_START)
                .withDateTimeEnd(VALID_DATE_TIME_END);
        EventAddCommand eventAddCommand = new EventAddCommand(vEvent);
        ModelStub modelStub = new ModelStubWithVEvent(vEvent);
        Assertions.assertThrows(CommandException.class, () -> eventAddCommand.execute(modelStub),
                MESSAGE_DUPLICATE_EVENT);
    }


}
