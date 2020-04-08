package seedu.address.logic.commands.notes;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_NOTES;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a NotesCommand, which displays help on specific notes commands
 * and refreshes the list of notes.
 */
public class NotesCommand extends Command {

    public static final String COMMAND_WORD = "notes";
    public static final String NOTES_ADD = "add";
    public static final String NOTES_EDIT = "edit";
    public static final String NOTES_DELETE = "delete";
    public static final String NOTES_FILTER = "filter";
    public static final String NOTES_EXPORT = "export";

    public static final String MESSAGE_USAGE = "The Column on the right displays all your notes." + '\n'
            + "[HELP ON NOTES COMMANDS]\n"
            + "ADD NOTE: notes add n/<Name of Student c/<Content> pr/<Priority>\n"
            + "EDIT NOTE: notes edit n/<Name of Student c/<Content> pr/<Priority>\n"
            + "DELETE NOTE: notes delete <Index>\n"
            + "FILTER SEARCH NOTES: notes filter <Keyword(s)>\n"
            + "EXPORT NOTES: notes export";

    /**
     * Creates a NotesCommand
     */
    public NotesCommand() {}

    /**
     * The execute() function which updated model with all notes, performing a refresh
     * Message on how to use notes feature is displayed to the user.
     * @param model {@code Model} which the command should operate on.
     * @return
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredNotesList(PREDICATE_SHOW_ALL_NOTES);
        return new CommandResult(MESSAGE_USAGE);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NotesCommand)) {
            return false;
        } else {
            return true;
        }
    }
}
