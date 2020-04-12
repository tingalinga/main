package seedu.address.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_EVENT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EVENT_INDEX;
import static seedu.address.commons.util.EventUtil.vEventToString;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.event.EventEditCommand.MESSAGE_EDIT_EVENT_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;
import static seedu.address.testutil.event.TypicalVEvents.NON_TYPICAL_VEVENT;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import jfxtras.icalendarfx.components.VEvent;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.event.EventEditCommand.EditVEventDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Tests for EventEditCommand
 * Integration testing done also. Since there is interactions between ModelManager and EditCommand.
 */
public class EventEditCommandTest {

    private static final String EDITED_EVENT_NAME = "Edited event name";
    private static final LocalDateTime EDITED_DATE_TIME_START = LocalDateTime.parse("2020-04-11T03:00");
    private static final LocalDateTime EDITED_DATE_TIME_END = LocalDateTime.parse("2020-04-11T04:00");

    private Model model = new ModelManager();

    public EventEditCommandTest() {
        model.setEventHistory(getTypicalEventHistory());
    }

    @Test
    public void execute_editedAllFields_success() {
        VEvent editedVEvent = NON_TYPICAL_VEVENT;
        EditVEventDescriptor descriptor = new EditVEventDescriptor();
        descriptor.setEventName(editedVEvent.getSummary().getValue());
        descriptor.setStartDateTime(LocalDateTime.from(editedVEvent.getDateTimeStart().getValue()));
        descriptor.setEndDateTime(LocalDateTime.from(editedVEvent.getDateTimeEnd().getValue()));
        descriptor.setColorCategory(editedVEvent.getCategories());
        descriptor.setRecurrenceRule(editedVEvent.getRecurrenceRule());

        EventEditCommand editCommand = new EventEditCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_EVENT_SUCCESS, INDEX_FIRST.getOneBased(), vEventToString(editedVEvent));
        Model expectedModel = new ModelManager();
        expectedModel.setEventHistory(getTypicalEventHistory());
        expectedModel.setVEvent(INDEX_FIRST, editedVEvent);

        assertCommandSuccess(editCommand, model, new CommandResult(expectedMessage),
                expectedModel);
    }

    @Test
    public void execute_editSomeFieldsSomeFieldsBlank_success() {
        Index indexVEvent = Index.fromOneBased(1);
        VEvent lastVEvent = model.getVEvent(indexVEvent);

        VEvent editedVEvent = lastVEvent.withSummary(EDITED_EVENT_NAME).withDateTimeStart(EDITED_DATE_TIME_START)
                .withDateTimeEnd(EDITED_DATE_TIME_END);

        EditVEventDescriptor descriptor = new EditVEventDescriptor();
        descriptor.setStartDateTime(LocalDateTime.from(editedVEvent.getDateTimeStart().getValue()));
        descriptor.setEndDateTime(LocalDateTime.from(editedVEvent.getDateTimeEnd().getValue()));

        EventEditCommand editCommand = new EventEditCommand(indexVEvent, descriptor);
        String expectedMessage = String.format(MESSAGE_EDIT_EVENT_SUCCESS, INDEX_FIRST.getOneBased(), vEventToString(editedVEvent));

        Model expectedModel = new ModelManager();
        expectedModel.setEventHistory(getTypicalEventHistory());
        expectedModel.setVEvent(indexVEvent, editedVEvent);

        assertCommandSuccess(editCommand, model, new CommandResult(expectedMessage),
                expectedModel);
    }

    @Test
    public void execute_noFieldsEdited_ommandExceptionThrown() {
        EventEditCommand editCommand = new EventEditCommand(INDEX_FIRST, new EditVEventDescriptor());
        assertThrows(CommandException.class, () -> editCommand.execute(model), EventEditCommand.NO_FIELDS_CHANGED);
    }

    @Test
    public void execute_duplicateVEvent_commandExceptionThrown() {
        VEvent vEventInList = model.getVEvent(INDEX_SECOND);
        EditVEventDescriptor descriptor = new EditVEventDescriptor();
        descriptor.setEventName(vEventInList.getSummary().getValue());
        descriptor.setStartDateTime(LocalDateTime.from(vEventInList.getDateTimeStart().getValue()));
        descriptor.setEndDateTime(LocalDateTime.from(vEventInList.getDateTimeEnd().getValue()));
        EventEditCommand editCommand = new EventEditCommand(INDEX_FIRST, descriptor);
        assertThrows(CommandException.class, () -> editCommand.execute(model), MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_invalidIndexGiven_commandExceptionThrown() {
        Index outOfBoundIndex = Index.fromOneBased(model.getVEvents().size() + 1);
        EditVEventDescriptor descriptor = new EditVEventDescriptor();
        descriptor.setEventName(EDITED_EVENT_NAME);
        EventEditCommand editCommand = new EventEditCommand(outOfBoundIndex, descriptor);
        assertThrows(CommandException.class, () -> editCommand.execute(model), MESSAGE_INVALID_EVENT_INDEX);
    }

    @Test
    public void equals() {
        final EditVEventDescriptor standardDescriptor = new EditVEventDescriptor();
        standardDescriptor.setEventName(EDITED_EVENT_NAME);
        final EventEditCommand standardCommand = new EventEditCommand(INDEX_FIRST, standardDescriptor);

        final EditVEventDescriptor standardDescriptorCopy = new EditVEventDescriptor();
        standardDescriptorCopy.setEventName(EDITED_EVENT_NAME);
        EventEditCommand standardCommandCopy = new EventEditCommand(INDEX_FIRST, standardDescriptorCopy);

        //same content -> returns true
        assertTrue(standardCommand.equals(standardCommandCopy));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(1));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EventEditCommand(INDEX_THIRD, standardDescriptor)));

        // different descriptor -> returns false
        final EditVEventDescriptor differentDescriptor = new EditVEventDescriptor();
        differentDescriptor.setEventName("Another different event name");
        assertFalse(standardCommand.equals(new EventEditCommand(INDEX_FIRST, differentDescriptor)));
    }
}