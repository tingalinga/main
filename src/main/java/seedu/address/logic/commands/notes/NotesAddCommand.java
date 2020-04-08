package seedu.address.logic.commands.notes;

import static java.util.Objects.requireNonNull;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_NOTES;
import static seedu.address.commons.core.Messages.MESSAGE_STUDENT_NOT_FOUND;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_ADD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_STUDENT;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.notes.Notes;
import seedu.address.model.student.Student;

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

    public static final String MESSAGE_SUCCESS = "New Student Note added! Wonderful!";

    public static final String MESSAGE_DUPLICATE_NOTE = "This same note already exists";

    private final String name;
    private final String content;
    private final String priority;
    private final Notes note;

    /**
     * Creates a NotesAddCommand to add note to a student.
     * @param name of the student which the note belongs to
     * @param content of the note
     * @param priority of the note
     */
    public NotesAddCommand(String name, String content, String priority) {
        requireAllNonNull(name, content, priority);
        this.name = name;
        this.content = content;
        this.priority = priority;
        this.note = new Notes(name, content, priority);
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
     * Getter of String priority.
     * @return String
     */
    public String getPriority() {
        return priority;
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

        ObservableList<Student> students = model.getFilteredStudentList();

        //Performs a check to ensure the student entered by the user is present in the class-list.
        boolean nameFound = false;

        for (Student student : students) {
            if (student.getName().toString().equals(this.name)) {
                nameFound = true;
            }
        }

        if (!nameFound) {
            throw new CommandException(MESSAGE_STUDENT_NOT_FOUND);
        }

        if (model.hasNote(note)) {
            throw new CommandException(MESSAGE_DUPLICATE_NOTES);
        }

        model.addNote(note);
        return new CommandResult(MESSAGE_SUCCESS + "\n" + note.toString());
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
                && content.equals(((NotesAddCommand) other).getContent())
                && priority.equals(((NotesAddCommand) other).getPriority());
    }

}
