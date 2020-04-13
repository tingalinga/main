package seedu.address.logic.commands.academics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.academics.TypicalAssessments.getTypicalAcademics;
import static seedu.address.testutil.admin.TypicalDates.getTypicalAdmin;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotesManager;
import static seedu.address.testutil.student.TypicalStudents.getTypicalTeaPet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class AcademicsDisplayCommandTest {

    private Model model = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
            getTypicalNotesManager(), getTypicalEventHistory(), new UserPrefs());

    @Test
    public void execute_display_success() {
        try {
            AcademicsDisplayCommand academicsDisplayCommand = new AcademicsDisplayCommand("homework");
            CommandResult commandResult = academicsDisplayCommand.execute(model);
            ModelManager expectedModel = new ModelManager(model.getTeaPet(), model.getAcademics(), model.getAdmin(),
                    model.getNotesManager(), model.getEventHistory(), new UserPrefs());
            CommandResult expectedCommandResult = academicsDisplayCommand.execute(expectedModel);
            assertEquals(commandResult, expectedCommandResult);
        } catch (CommandException ce) {
            //do nothing because it wont happen
        }
    }

    @Test
    public void equals() {

        AcademicsDisplayCommand displayCommand = new AcademicsDisplayCommand("");
        AcademicsDisplayCommand displayHomeworkCommand = new AcademicsDisplayCommand("homework");
        AcademicsDisplayCommand displayReportCommand = new AcademicsDisplayCommand("report");
        AcademicsDisplayCommand displayExamCommand = new AcademicsDisplayCommand("exam");

        // same object -> returns true
        assertTrue(displayHomeworkCommand.equals(displayHomeworkCommand));

        // same values -> returns true
        AcademicsDisplayCommand displayHomeworkCommandCopy = new AcademicsDisplayCommand("homework");
        assertTrue(displayHomeworkCommand.equals(displayHomeworkCommandCopy));

        // different types -> returns false
        assertFalse(displayHomeworkCommand.equals(1));

        // null -> returns false
        assertFalse(displayHomeworkCommand.equals(null));

        // different academicsDisplay -> returns true
        assertFalse(displayHomeworkCommand.equals(displayCommand));
        assertFalse(displayHomeworkCommand.equals(displayReportCommand));
        assertFalse(displayHomeworkCommand.equals(displayExamCommand));
        assertFalse(displayReportCommand.equals(displayExamCommand));
    }
}
