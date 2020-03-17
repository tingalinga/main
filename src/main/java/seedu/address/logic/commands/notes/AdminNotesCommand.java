package seedu.address.logic.commands.notes;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.NextOfKin;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Remark;
import seedu.address.model.student.Student;
import seedu.address.model.student.Temperature;
import seedu.address.model.student.notes.AdminNotes;
import seedu.address.model.student.notes.Notes;
import seedu.address.model.tag.Tag;


/**
 * Adds Admin Notes to a Student
 */
public class AdminNotesCommand extends Command {

    public static final String COMMAND_WORD = "anotes";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_NAME + " [Name of Student] " + PREFIX_CONTENT + " [Content of Sticky Note]";

    public static final String MESSAGE_SUCCESS = "New Admin Note added! Yay!";

    private final String name;
    private final String content;
    private final AdminNotes adminNotes;

    /**
     *
     * @param name of the student which the note belongs to
     * @param content of the note
     */
    public AdminNotesCommand(String name, String content) {
        requireNonNull(name, content);
        this.name = name;
        this.content = content;
        this.adminNotes = new AdminNotes(name, content);
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();
        int indexOfStudent = -1;
        for (int i = 0; i < lastShownList.size(); i++) {
            if (lastShownList.get(i).getName().toString().equals(name)) {
                indexOfStudent = i;
            }
        }
        if (indexOfStudent == -1) {
            throw new CommandException("Name of Student not found");
        }

        Name originalName = lastShownList.get(indexOfStudent).getName();
        Phone originalPhone = lastShownList.get(indexOfStudent).getPhone();
        Email originalEmail = lastShownList.get(indexOfStudent).getEmail();
        Address originalAddress = lastShownList.get(indexOfStudent).getAddress();
        Temperature originalTemperature = lastShownList.get(indexOfStudent).getTemperature();
        NextOfKin originalNok = lastShownList.get(indexOfStudent).getNok();
        ArrayList<Notes> originalNotes = lastShownList.get(indexOfStudent).getNotes();
        Remark originalRemarks = lastShownList.get(indexOfStudent).getRemark();
        Set<Tag> originalTags = lastShownList.get(indexOfStudent).getTags();

        originalNotes.add(adminNotes);
        Student editedStudent = new Student(originalName, originalPhone, originalEmail,
                originalAddress, originalTemperature, originalNok, originalNotes, originalRemarks, originalTags);

        model.setStudent(lastShownList.get(indexOfStudent), editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_SUCCESS + '\n'
                + adminNotes.toString(), editedStudent));

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AdminNotesCommand)) {
            return false;
        }
        AdminNotesCommand s = (AdminNotesCommand) other;
        return name.equals(((AdminNotesCommand) other).getName())
                && content.equals(((AdminNotesCommand) other).getContent());
    }

}
