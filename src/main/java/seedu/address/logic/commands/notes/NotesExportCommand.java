package seedu.address.logic.commands.notes;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public class NotesExportCommand extends Command {
    public static final String COMMAND_WORD = "notese";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " [with no other parameters]";

    public static final String MESSAGE_SUCCESS = "Notes are exported to studentNotes.txt";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
