package seedu.address.logic.commands.academics;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPORT;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Represents a AcademicsExportCommand which exports currently stored notes, into a .txt file format.
 */
public class AcademicsExportCommand extends AcademicsCommand {

    public static final String MESSAGE_USAGE = "This command exports the academics as a csv file. \n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_EXPORT;

    public static final String MESSAGE_SUCCESS = "Academics are exported to studentAcademics.csv in the data folder";

    /**
     * Overrides execute command which returns CommandResult containing success message.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult
     */
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AcademicsExportCommand); // instanceof handles nulls
    }
}
