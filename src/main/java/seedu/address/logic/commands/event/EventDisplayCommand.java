package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_SCHEDULE_HELP;

import java.time.LocalDateTime;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Displays the schedule of events.
 */
public class EventDisplayCommand extends EventCommand {

    public static final String MESSAGE_SUCCESS = "This is your schedule for the week\n" + MESSAGE_SCHEDULE_HELP;

    /**
     * Creates an EventDisplayCommand.
     */
    public EventDisplayCommand() {

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setEventScheduleLocalDateTime(LocalDateTime.now());
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return true;
    }
}
