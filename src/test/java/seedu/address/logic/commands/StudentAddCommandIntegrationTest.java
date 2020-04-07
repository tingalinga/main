package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssessments.getTypicalAcademics;
import static seedu.address.testutil.TypicalDates.getTypicalAdmin;
import static seedu.address.testutil.TypicalNotes.getTypicalNotes;
import static seedu.address.testutil.TypicalStudents.getTypicalTeaPet;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.student.StudentAddCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class StudentAddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(), getTypicalNotes(),
                getTypicalEventHistory(), new UserPrefs());
    }

    @Test
    public void execute_newStudent_success() {
        Student validStudent = new StudentBuilder().build();

        Model expectedModel = new ModelManager(model.getTeaPet(), model.getAcademics(), model.getAdmin(),
                model.getNotesManager(), getTypicalEventHistory(), new UserPrefs());

        expectedModel.addStudent(validStudent);

        assertCommandSuccess(new StudentAddCommand(validStudent), model,
                String.format(StudentAddCommand.MESSAGE_SUCCESS, validStudent), expectedModel);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Student studentInList = model.getTeaPet().getStudentList().get(0);
        assertCommandFailure(new StudentAddCommand(studentInList), model, StudentAddCommand.MESSAGE_DUPLICATE_STUDENT);
    }

}
