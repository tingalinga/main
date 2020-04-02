package seedu.address.logic.commands.notes;

import static java.util.Objects.requireNonNull;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_NOTES;
import static seedu.address.commons.core.Messages.MESSAGE_STUDENT_NOT_FOUND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.notes.Notes;
import seedu.address.model.student.Student;


/**
 * Represents NotesAddCommand class which adds Note to a Student.
 */
public class NotesAddCommand extends Command {

    public static final String COMMAND_WORD = "notesa";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_NAME + " [Name of Student] " + PREFIX_CONTENT + " [Content of Sticky Note]";

    public static final String MESSAGE_SUCCESS = "New Student Note added! Yay!";

    private final String name;
    private final String content;
    private final Notes note;

    /**
     * Creates a NotesCommand to add note to a student.
     * @param name of the student which the note belongs to
     * @param content of the note
     */
    public NotesAddCommand(String name, String content) {
        requireNonNull(name, content);
        this.name = name;
        this.content = content;
        this.note = new Notes(name, content);
    }

    /**
     * Getter of String name.
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Getter of String content.
     * @return String
     */
    public String getContent() {
        return content;
    }

    /**
     * The execute() function which returns to the model an updated student with the new note added.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult
     * @throws CommandException if student cannot be found.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Student> students = model.getFilteredStudentList();

        boolean nameFound = false;

        for (Student student : students) {
            if (student.getName().toString().toLowerCase().equals(this.name.toLowerCase())) {
                nameFound = true;
            }
        }

        if (nameFound == false) {
            throw new CommandException(MESSAGE_STUDENT_NOT_FOUND);
        }

        if (model.hasNote(note)) {
            throw new CommandException(MESSAGE_DUPLICATE_NOTES);
        }

        model.addNote(note);
        return new CommandResult(String.format(MESSAGE_SUCCESS, note));

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof NotesAddCommand)) {
            return false;
        }
        NotesAddCommand s = (NotesAddCommand) other;
        return name.equals(((NotesAddCommand) other).getName())
                && content.equals(((NotesAddCommand) other).getContent());
    }

}
