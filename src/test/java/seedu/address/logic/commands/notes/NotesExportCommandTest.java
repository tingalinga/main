package seedu.address.logic.commands.notes;

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
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class NotesExportCommandTest {

    private Model model = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
            getTypicalNotesManager(), getTypicalEventHistory(), new UserPrefs());

    @Test
    public void notesExported_success() {
        NotesExportCommand exportCommand = new NotesExportCommand();
        CommandResult commandResult = exportCommand.execute(model);

        String expectedString = NotesExportCommand.MESSAGE_SUCCESS;

        assertEquals(commandResult.getFeedbackToUser(), expectedString);
    }

    @Test
    public void equals() {
        NotesExportCommand firstExportCommand = new NotesExportCommand();
        NotesExportCommand secondExportCommand = new NotesExportCommand();

        // same object -> returns true
        assertTrue(firstExportCommand.equals(firstExportCommand));

        // same class -> returns true
        assertTrue(firstExportCommand.equals(secondExportCommand));

        // different class -> returns false
        assertFalse(firstExportCommand.equals(1));

        // different class -> returns false
        assertFalse(firstExportCommand.equals(null));
    }

}
