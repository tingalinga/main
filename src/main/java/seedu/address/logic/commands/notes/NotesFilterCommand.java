package seedu.address.logic.commands.notes;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a NotesFilterCommand which filters the notes displayed based on input keywords.
 */
public class NotesFilterCommand extends Command {
    public static final String COMMAND_WORD = "notesf";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all notes which contains any of "
        + "the specified keywords (case-insensitive) and displays them as a list with index numbers. \n"
        + "Example: " + COMMAND_WORD + " late asleep disappointed";

    public static final String MESSAGE_SUCCESS = "Displaying Notes with Keywords: ";

    private final List<String> keywords;

    /**
     * Constructor of NotesFilterCommand
     * @param keywords
     */
    public NotesFilterCommand(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Overriden execute method which returns a CommandResult with success message.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_SUCCESS + keywords.toString());
    }
}
