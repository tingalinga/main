package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssessments.getTypicalAcademics;
import static seedu.address.testutil.TypicalDates.getTypicalAdmin;
import static seedu.address.testutil.TypicalNotes.getTypicalNotes;
import static seedu.address.testutil.TypicalStudents.getTypicalTeaPet;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.TeaPet;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
                getTypicalNotes(), getTypicalEventHistory(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
                getTypicalNotes(), getTypicalEventHistory(), new UserPrefs());
        expectedModel.setTeaPet(new TeaPet());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
