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
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class NotesCommandTest {

    private Model model = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
            getTypicalNotesManager(), getTypicalEventHistory(), new UserPrefs());


    @Test
    public void notesCommand_success() throws CommandException {
        NotesCommand notesCommand = new NotesCommand();
        CommandResult commandResult = notesCommand.execute(model);

        String expectedString = NotesCommand.MESSAGE_SUCCESS;

        assertEquals(commandResult.getFeedbackToUser(), expectedString);
    }

    @Test
    public void equals() {
        NotesCommand firstNotesCommand = new NotesCommand();
        NotesCommand secondNotesCommand = new NotesCommand();

        // same object -> returns true
        assertTrue(firstNotesCommand.equals(firstNotesCommand));

        // same class -> returns true
        assertTrue(firstNotesCommand.equals(secondNotesCommand));

        // different class -> returns false
        assertFalse(firstNotesCommand.equals(1));

        // different class -> returns false
        assertFalse(firstNotesCommand.equals(null));
    }
}
