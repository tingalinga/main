package seedu.address.logic.commands.notes;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_EXPORT;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Represents a NotesExportCommand which exports currently stored notes, into a .txt file format.
 */
public class NotesExportCommand extends NotesCommand {

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + " "
            + PREFIX_NOTES_EXPORT + " [with no other parameters]";

    public static final String MESSAGE_SUCCESS = "Notes are exported to studentNotes.csv in the data folder";

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
