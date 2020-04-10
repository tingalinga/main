package seedu.address.logic.commands.notes;

import static java.util.Objects.requireNonNull;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_NOTES;
import static seedu.address.commons.core.Messages.MESSAGE_STUDENT_NOT_FOUND;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_ADD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_STUDENT;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.notes.Notes;


/**
 * Represents NotesAddCommand class which adds Note to a particular Student in the class-list.
 * Subclass of NotesCommand
 */
public class NotesAddCommand extends NotesCommand {

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + " "
            + PREFIX_NOTES_ADD + " "
            + PREFIX_NOTES_STUDENT + "<Name of Student> " + PREFIX_NOTES_CONTENT + "<Content of Note> "
            + PREFIX_NOTES_PRIORITY + "<HIGH/MEDIUM/LOW>";

    public static final String MESSAGE_SUCCESS = "New Student Note added! Wonderful!\n"
            + "%1$s";

    public static final String MESSAGE_DUPLICATE_NOTE = "This same note already exists";

    private final Notes note;

    /**
     * Creates a NotesAddCommand to add note to a student.
     * @param note, the object which is to be added.
     */
    public NotesAddCommand(Notes note) {
        requireAllNonNull(note);
        this.note = note;
    }

    /**
     * Getter of String student in note.
     * @return String
     */
    public String getStudent() {
        return note.getStudent();
    }

    /**
     * Getter of String content in note.
     * @return String
     */
    public String getContent() {
        return note.getContent();
    }

    /**
     * Getter of String priority in note.
     * @return String
     */
    public String getPriority() {
        return note.getPriority();
    }

    /**
     * Getter of note.
     * @return Notes object
     */
    public Notes getNote() {
        return this.note;
    }

    /**
     * The execute() function which returns to the model an updated student with the new note added.
     * @param model {@code Model} which the command should operate on.
     * @return a new CommandResult
     * @throws CommandException if student cannot be found.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        //Performs a check to ensure the student entered by the user is present in the class-list.
        boolean nameFound = model.hasStudentName(getStudent());

        if (!nameFound) {
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
        return note.equals(((NotesAddCommand) other).getNote());
    }

}
