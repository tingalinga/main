package seedu.address.logic.commands.notes;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_DELETE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.notes.Notes;

/**
 *  Represents NotesDeleteCommand which deletes a note from model and storage.
 *  Subclass of NotesCommand
 */
public class NotesDeleteCommand extends NotesCommand {

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + " "
            + PREFIX_NOTES_DELETE + " <Index>";

    public static final String MESSAGE_SUCCESS = "Student Note deleted.\n"
            + "%1$s";

    private final Index targetIndex;

    public NotesDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * The execute() method which deletes a specified note from the model and storage
     * @param model {@code Model} which the command should operate on.
     * @return a new CommandResult
     * @throws CommandException if index given by User is invalid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Notes> lastShownList = model.getFilteredNotesList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTES_DISPLAYED_INDEX);
        }

        Notes toBeDeleted = lastShownList.get(targetIndex.getZeroBased());
        model.deleteNote(toBeDeleted);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toBeDeleted));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NotesDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((NotesDeleteCommand) other).targetIndex)); // state check
    }



}
