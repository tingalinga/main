package seedu.address.logic.commands.notes;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_FILTER;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.notes.NotesContainKeywordsPredicate;

/**
 * Represents a NotesFilterCommand which filters the notes displayed based on input keywords.
 * Subclass of NotesCommand
 */
public class NotesFilterCommand extends NotesCommand {

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + " "
            + PREFIX_NOTES_FILTER + " KEYWORD(S)\n"
            + "This command looks for keywords present in Student, "
            + "DateTime, Priority and Content fields";

    public static final String MESSAGE_SUCCESS = "Displaying Notes with Keywords: ";

    private final NotesContainKeywordsPredicate predicate;

    /**
     * Constructor of NotesFilterCommand
     * @param predicate
     */
    public NotesFilterCommand(NotesContainKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Overriden execute method which updates the model which notes that pass the given predicate.
     * Predicate refers to the containment of keywords given by user.
     * @param model {@code Model} which the command should operate on.
     * @return a new CommandResult
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredNotesList(predicate);
        return new CommandResult(MESSAGE_SUCCESS + predicate.getKeywords().toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof NotesFilterCommand)) {
            return false;
        }

        NotesFilterCommand o = (NotesFilterCommand) other;
        return predicate.equals(o.predicate);
    }
}
