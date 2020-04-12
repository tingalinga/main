package seedu.address.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.EventUtil.formatIndexVEventPair;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.event.EventIndexCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.event.EventIndexCommand.MESSAGE_SUGGESTION_EVENT;
import static seedu.address.logic.commands.event.EventIndexCommand.MESSAGE_NO_EVENT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;
import static seedu.address.testutil.event.TypicalVEvents.VEVENT1;

import org.junit.jupiter.api.Test;

import org.apache.commons.math3.util.Pair;

import jfxtras.icalendarfx.components.VEvent;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Test EventIndexCommand. Note that this is a integration test as there is interaction between ModelManager and
 * EventIndexCommand.
 */
public class EventIndexCommandTest {
    private static final String VALID_EVENT_NAME = "valid event name";
    private static final String VALID_OTHER_EVENT_NAME = "another valid event name";
    private Model model = new ModelManager();

    public EventIndexCommandTest() {
        model.setEventHistory(getTypicalEventHistory());
    }

    @Test
    public void execute_validIndexGet_showsCorrectEvent() {
        Model expectedModel = new ModelManager();
        expectedModel.setEventHistory(getTypicalEventHistory());

        String desiredEventName = VEVENT1.getSummary().getValue();

        String expectedResult = formatIndexVEventPair(new Pair<Index, VEvent>(Index.fromOneBased(1), VEVENT1));
        String expectedResultFormatted = String.format(MESSAGE_SUCCESS,expectedResult);

        assertCommandSuccess(new EventIndexCommand(desiredEventName), model,
                new CommandResult(expectedResultFormatted), expectedModel);
    }

    @Test
    public void execute_eventNameNotFound_displayMostSimilarEvent() {
        Model expectedModel = new ModelManager();
        expectedModel.setEventHistory(getTypicalEventHistory());

        String eventName = VEVENT1.getSummary().getValue().substring(0, 6);
        Pair<Index, VEvent> expectedEventIndexPair = new Pair<Index, VEvent>(Index.fromOneBased(1), VEVENT1);
        String expectedResult = String.format(MESSAGE_SUGGESTION_EVENT, formatIndexVEventPair(expectedEventIndexPair));

        assertCommandSuccess(new EventIndexCommand(eventName), model,
                new CommandResult(expectedResult), expectedModel);
    }

    @Test
    public void constructor_nullEventName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventIndexCommand(null));
    }

    @Test
    public void execute_emptyEventHistory_throwsCommandException() {
        Model model = new ModelManager();
        EventIndexCommand indexCommand = new EventIndexCommand("an event name");
        assertThrows(CommandException.class, MESSAGE_NO_EVENT, () -> indexCommand.execute(model));
    }

    @Test
    public void equals() {
        EventIndexCommand indexEventCommand = new EventIndexCommand(VALID_EVENT_NAME);
        EventIndexCommand otherIndexEventCommand = new EventIndexCommand(VALID_OTHER_EVENT_NAME);

        // same object -> returns true
        assertTrue(indexEventCommand.equals(indexEventCommand));

        // same values -> returns true
        assertTrue(indexEventCommand.equals(new EventIndexCommand(VALID_EVENT_NAME)));

        // different types -> returns false
        assertFalse(indexEventCommand.equals(1));

        // null -> returns false
        assertFalse(indexEventCommand.equals(null));

        // different note -> returns false
        assertFalse(indexEventCommand.equals(otherIndexEventCommand));
    }

}