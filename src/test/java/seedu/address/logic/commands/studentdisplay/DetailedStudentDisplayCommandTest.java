package seedu.address.logic.commands.studentdisplay;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.student.DetailedStudentDisplayCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.academics.TypicalAssessments.getTypicalAcademics;
import static seedu.address.testutil.admin.TypicalDates.getTypicalAdmin;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotesManager;
import static seedu.address.testutil.student.TypicalStudents.getTypicalTeaPet;

public class DetailedStudentDisplayCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {

        model = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(), getTypicalNotesManager(),
                getTypicalEventHistory(), new UserPrefs());
        expectedModel = new ModelManager(model.getTeaPet(), model.getAcademics(), model.getAdmin(),
                model.getNotesManager(), getTypicalEventHistory(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new DetailedStudentDisplayCommand(), model, DetailedStudentDisplayCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
