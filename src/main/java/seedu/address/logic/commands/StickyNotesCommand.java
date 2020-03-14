package seedu.address.logic.commands;

import seedu.address.model.notes.StickyNotes;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

public class StickyNotesCommand extends Command {

    public static final String COMMAND_WORD = "snotes";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " +
            PREFIX_NAME + " [Name of Student] " + PREFIX_CONTENT + " [Content of Sticky Note]";

    public static final String MESSAGE_SUCCESS = "New Sticky Note added! Yay!";

    private final StickyNotes stickyNotes;


    public StickyNotesCommand(StickyNotes stickyNotes) {
        requireNonNull(stickyNotes);
        this.stickyNotes = stickyNotes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(String.format(MESSAGE_SUCCESS + '\n' +
                stickyNotes.toString(), stickyNotes));

    }

}
