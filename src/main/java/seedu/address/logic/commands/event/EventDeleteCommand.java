package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.EventUtil.vEventToString;

import java.util.List;

import jfxtras.icalendarfx.components.VEvent;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * EventDeleteCommand deletes an event according to its index, Which is represented by EventIndexCommand.
 */
public class EventDeleteCommand extends EventCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an event identified by the index of the event"
            + "\n"
            + "Parameters:"
            + " delete INDEX: deletes the event identified by the index beside the event name." + "\n"
            + "Note: INDEX must be a positive integer\n"
            + "Example: schedule delete 3";

    public static final String MESSAGE_SUCCESS = "Deleted Event: %1$s";

    private final Index targetIndex;

    public EventDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<VEvent> lastShownList = model.getVEvents();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_INDEX);
        }

        VEvent vEventToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.delete(targetIndex);
        return new CommandResult(generateDeleteSuccessMessage(vEventToDelete));
    }

    private String generateDeleteSuccessMessage(VEvent vEvent) {
        return String.format(MESSAGE_SUCCESS, vEventToString(vEvent));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((EventDeleteCommand) other).targetIndex)); // state check
    }

}
