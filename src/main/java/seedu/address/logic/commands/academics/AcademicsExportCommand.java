package seedu.address.logic.commands.academics;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPORT;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Represents a AcademicsExportCommand which exports currently stored notes, into a .txt file format.
 */
public class AcademicsExportCommand extends AcademicsCommand {

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + " " + PREFIX_EXPORT + " [with no other parameters]";

    public static final String MESSAGE_SUCCESS = "Academics are exported to studentAcademics.csv in the data folder";

    /**
     * Overriden execute command which returns CommandResult containing success message.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult
     */
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
