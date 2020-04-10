package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_EVENT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EVENT_DATETIME_RANGE;
import static seedu.address.commons.util.EventUtil.isEqualEvent;
import static seedu.address.commons.util.EventUtil.vEventToString;
import static seedu.address.commons.util.EventUtil.validateDateTime;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import jfxtras.icalendarfx.components.VEvent;
import jfxtras.icalendarfx.properties.component.descriptive.Categories;
import jfxtras.icalendarfx.properties.component.recurrence.RecurrenceRule;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * EventEditCommand represents the edit command which edits the events details in the events history.
 */
public class EventEditCommand extends EventCommand {

    public static final String COMMAND_WORD = "schedule edit";

    public static final String MESSAGE_USAGE = "This command edits the details of an event identified.\n"
            + "Format: schedule edit INDEX (must be a positive integer) "
            + "[eventName/EVENT_DESCRIPTION] [startDateTime/YYYY-MM-DDTHH:MM]"
            + " [endDateTime/YYYY-MM-DDTHH:MM] [recur/RECUR_DESCRIPTION] (none, daily, weekly) "
            + "[color/COLOR_CODE] (1 to 23)\n"
            + "Example: schedule edit 2 eventName/cs2100 lecture startDateTime/2019-10-21T14:00 "
            + "endDateTime/2019-10-21T15:00 recur/none color/1";
    public static final String NO_FIELDS_CHANGED = "Please provide at least one field to edit.";
    public static final String MESSAGE_EDIT_EVENT_SUCCESS = "Edited Event: %1$s";

    private final EditVEventDescriptor editVEventDescriptor;
    private final Index index;

    /**
     * EventEditCommand represent the edit command to edit events in the schedule.
     *
     * @param index                Index of the event to be edited.
     * @param editVEventDescriptor Object used to edit the event which was specified.
     */
    public EventEditCommand(Index index, EditVEventDescriptor editVEventDescriptor) {
        this.index = index;
        this.editVEventDescriptor = editVEventDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index.getZeroBased() >= model.getVEvents().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_INDEX);
        }

        if (!editVEventDescriptor.isAnyFieldEdited()) {
            throw new CommandException(NO_FIELDS_CHANGED);
        }

        VEvent vEventObjectToEdit = model.getVEvent(index);
        VEvent editedVEvent;
        try {
            editedVEvent = createdEditedVEvent(vEventObjectToEdit, editVEventDescriptor);
        } catch (IllegalValueException ex) {
            throw new CommandException(ex.getMessage(), ex);
        }

        if (!isEqualEvent(editedVEvent, vEventObjectToEdit) && model.hasVEvent(editedVEvent)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.setVEvent(index, editedVEvent);

        return new CommandResult(generateSuccessMessage(editedVEvent));
    }

    /**
     * Generates a command execution success message.
     *
     * @param vEvent that has been editted.
     */
    private String generateSuccessMessage(VEvent vEvent) {
        return String.format(MESSAGE_EDIT_EVENT_SUCCESS, vEventToString(vEvent));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventEditCommand)) {
            return false;
        }

        //state check
        EventEditCommand e = (EventEditCommand) other;
        return index.equals(e.index)
                && editVEventDescriptor.equals(e.editVEventDescriptor);
    }

    /**
     * Creates and returns a {@code VEvent} with the details of {@code eventToEdit}
     * edited with {@code editVEventDescriptor}.
     */
    private static VEvent createdEditedVEvent(VEvent eventToEdit, EditVEventDescriptor editVEventDescriptor)
            throws IllegalValueException {
        assert eventToEdit != null;

        String updatedEventName = editVEventDescriptor.getEventName().orElse(eventToEdit.getSummary().getValue());
        LocalDateTime updatedStartDateTime = editVEventDescriptor.getStartDateTime()
                .orElse(LocalDateTime.from(eventToEdit.getDateTimeStart().getValue()));
        LocalDateTime updatedEndDateTime = editVEventDescriptor.getEndDateTime()
                .orElse(LocalDateTime.from(eventToEdit.getDateTimeEnd().getValue()));
        List<Categories> updatedColorCategory = editVEventDescriptor.getColorCategory()
                .orElse(eventToEdit.getCategories());
        RecurrenceRule updatedRecurrenceRule = editVEventDescriptor.getRecurrenceRule()
                .orElse(eventToEdit.getRecurrenceRule());
        String uniqueIdentifier = eventToEdit.getUniqueIdentifier().getValue();

        if (!validateDateTime(updatedStartDateTime, updatedEndDateTime)) {
            throw new IllegalValueException(MESSAGE_INVALID_EVENT_DATETIME_RANGE);
        }

        return new VEvent().withRecurrenceRule(updatedRecurrenceRule).withCategories(updatedColorCategory)
                .withDateTimeEnd(updatedEndDateTime).withDateTimeStart(updatedStartDateTime)
                .withUniqueIdentifier(uniqueIdentifier).withSummary(updatedEventName);
    }


    /**
     * Contains the details to edit the VEvent with. Each field value that is not empty will replace the
     * corresponding field value of the current Event.
     */
    public static class EditVEventDescriptor {
        private String eventName;
        private LocalDateTime startDateTime;
        private LocalDateTime endDateTime;
        private RecurrenceRule recurrenceRule;
        private List<Categories> colorCategory;

        public EditVEventDescriptor() {

        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditVEventDescriptor(EventEditCommand.EditVEventDescriptor toCopy) {
            setEventName(toCopy.eventName);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(eventName, startDateTime, endDateTime, recurrenceRule, colorCategory);
        }

        public void setEventName(String eventName) {
            this.eventName = eventName;
        }

        public Optional<String> getEventName() {
            return Optional.ofNullable(eventName);
        }

        public void setStartDateTime(LocalDateTime startDateTime) {
            this.startDateTime = startDateTime;
        }

        public Optional<LocalDateTime> getStartDateTime() {
            return Optional.ofNullable(startDateTime);
        }

        public void setEndDateTime(LocalDateTime endDateTime) {
            this.endDateTime = endDateTime;
        }

        public Optional<LocalDateTime> getEndDateTime() {
            return Optional.ofNullable(endDateTime);
        }

        public void setRecurrenceRule(RecurrenceRule recurrenceRule) {
            this.recurrenceRule = recurrenceRule;
        }

        public Optional<RecurrenceRule> getRecurrenceRule() {
            return Optional.ofNullable(recurrenceRule);
        }

        public void setColorCategory(List<Categories> colorCategory) {
            this.colorCategory = colorCategory;
        }

        public Optional<List<Categories> > getColorCategory() {
            return Optional.ofNullable(colorCategory);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EventEditCommand.EditVEventDescriptor)) {
                return false;
            }

            // state check
            EditVEventDescriptor e = (EditVEventDescriptor) other;

            return getEventName().equals(e.getEventName())
                    && getColorCategory().equals(e.getColorCategory())
                    && getEndDateTime().equals(e.getEndDateTime())
                    && getStartDateTime().equals(e.getStartDateTime())
                    && getColorCategory().equals(e.getColorCategory());
        }
    }
}
