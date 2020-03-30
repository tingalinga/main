package seedu.address.logic.commands.notes;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.notes.Notes;
import seedu.address.model.student.Address;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.NextOfKin;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Remark;
import seedu.address.model.student.Student;
import seedu.address.model.student.Temperature;

import seedu.address.model.tag.Tag;

/**
 *  Represents NotesDeleteCommand which deletes a note from storage.
 */
public class NotesDeleteCommand extends Command {

    public static final String COMMAND_WORD = "notesd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " Index of Note to be deleted.";

    public static final String MESSAGE_SUCCESS = "Student Note deleted.";

    private final Index targetIndex;

    public NotesDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Overriden execute method which deletes a specified note from a student, and returning the
     * updated student to the model.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        ArrayList<Notes> allNotes = new ArrayList<>();
        for (Student student : lastShownList) {
            allNotes.addAll(student.getNotes());
        }

        if (targetIndex.getZeroBased() >= allNotes.size() || targetIndex.getZeroBased() < 0) {
            throw new CommandException(Messages.MESSGAE_INVALID_NOTES_DISPLAYED_INDEX);
        }

        Notes noteToDelete = allNotes.get(targetIndex.getZeroBased());

        //Iterate through the list of students
        int indexOfStudent = -1;
        for (int i = 0; i < lastShownList.size(); i++) {
            if (lastShownList.get(i).getName().toString().equals(noteToDelete.getStudent())) {
                indexOfStudent = i;
            }
        }

        if (indexOfStudent == -1) {
            throw new CommandException("Note not found");
        }

        Name originalName = lastShownList.get(indexOfStudent).getName();
        Phone originalPhone = lastShownList.get(indexOfStudent).getPhone();
        Email originalEmail = lastShownList.get(indexOfStudent).getEmail();
        Address originalAddress = lastShownList.get(indexOfStudent).getAddress();
        Temperature originalTemperature = lastShownList.get(indexOfStudent).getTemperature();
        Attendance originalAttendance = lastShownList.get(indexOfStudent).getAttendance();
        NextOfKin originalNok = lastShownList.get(indexOfStudent).getNok();
        ArrayList<Notes> originalNotes = lastShownList.get(indexOfStudent).getNotes();
        Remark originalRemarks = lastShownList.get(indexOfStudent).getRemark();
        Set<Tag> originalTags = lastShownList.get(indexOfStudent).getTags();

        originalNotes.remove(noteToDelete);

        Student editedStudent = new Student(originalName, originalPhone, originalEmail,
                originalAddress, originalTemperature, originalAttendance, originalNok, originalNotes, originalRemarks,
                originalTags);

        model.setStudent(lastShownList.get(indexOfStudent), editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NotesDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((NotesDeleteCommand) other).targetIndex)); // state check
    }

}
