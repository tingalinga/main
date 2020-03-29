package seedu.address.logic.commands.notes;


import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import java.util.List;

/**
 * NotesFilterCommand class which filters the notes displayed based on input keywords.
 */
public class NotesFilterCommand extends Command {
    public static final String COMMAND_WORD = "notesf";

    public static String MESSAGE_USAGE = COMMAND_WORD + ": Finds all notes which contains any of "
    + "the specified keywords (case-insensitive) and displays them as a list with index numbers. \n"
    + "Example: " + COMMAND_WORD + " late asleep disappointed";

    private final List<String> keywords;

    public NotesFilterCommand(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Displaying Notes with Keywords: " + keywords.toString());
    }
}
