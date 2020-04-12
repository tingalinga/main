package seedu.address.logic.commands.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.academics.TypicalAssessments.getTypicalAcademics;
import static seedu.address.testutil.admin.TypicalDates.getTypicalAdmin;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotesManager;
import static seedu.address.testutil.student.TypicalStudents.getTypicalTeaPet;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class AdminDatesCommandTest {

    private Model model = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
            getTypicalNotesManager(), getTypicalEventHistory(), new UserPrefs());

    @Test
    public void execute_display_success() {
        AdminDatesCommand adminDatesCommand = new AdminDatesCommand();
        CommandResult commandResult = adminDatesCommand.execute(model);
        ModelManager expectedModel = new ModelManager(model.getTeaPet(), model.getAcademics(), model.getAdmin(),
                model.getNotesManager(), model.getEventHistory(), new UserPrefs());
        CommandResult expectedCommandResult = adminDatesCommand.execute(expectedModel);
        assertEquals(commandResult, expectedCommandResult);

    }

    @Test
    public void equals() {
        AdminDatesCommand displayFirstCommand = new AdminDatesCommand();
        AdminDatesCommand displaySecondCommand = new AdminDatesCommand();

        // same object -> returns true
        assertTrue(displayFirstCommand.equals(displayFirstCommand));

        // same values -> returns true
        AdminDatesCommand displayThirdCommand = new AdminDatesCommand();
        assertTrue(displayFirstCommand.equals(displayThirdCommand));

        // different types -> returns false
        assertFalse(displayFirstCommand.equals(1));

        // null -> returns false
        assertFalse(displayFirstCommand.equals(null));

        // different adminDisplay -> returns true
        assertTrue(displayFirstCommand.equals(displaySecondCommand));
    }
}
