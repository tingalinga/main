package seedu.address.logic.commands.event;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPORT;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Represents an EventExportCommand which exports current events on the schedule, into a .ics file format.
 */
public class EventExportCommand extends EventCommand {

    public static final String MESSAGE_USAGE = "This command exports the schedule as a .ics file. \n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_EXPORT;

    public static final String MESSAGE_SUCCESS = "Schedule is exported to mySchedule.ics in the data folder";

    /**
     * Overriden execute command which returns CommandResult containing success message.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult
     */
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof EventExportCommand;
    }

}

