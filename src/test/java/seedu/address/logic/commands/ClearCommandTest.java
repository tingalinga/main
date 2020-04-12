package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.academics.TypicalAssessments.getTypicalAcademics;
import static seedu.address.testutil.admin.TypicalDates.getTypicalAdmin;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotesManager;
import static seedu.address.testutil.student.TypicalStudents.getTypicalTeaPet;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.student.StudentClearCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.TeaPet;

public class ClearCommandTest {

    @Test
    public void execute_emptyTeaPet_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new StudentClearCommand(), model, StudentClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTeaPet_success() {
        Model model = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
                getTypicalNotesManager(), getTypicalEventHistory(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
                getTypicalNotesManager(), getTypicalEventHistory(), new UserPrefs());
        expectedModel.setTeaPet(new TeaPet());

        assertCommandSuccess(new StudentClearCommand(), model, StudentClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
