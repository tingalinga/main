package seedu.address.logic.commands.notes;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.academics.TypicalAssessments.getTypicalAcademics;
import static seedu.address.testutil.admin.TypicalDates.getTypicalAdmin;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_NOTE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_NOTE;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotesManager;
import static seedu.address.testutil.student.TypicalStudents.getTypicalTeaPet;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.notes.Notes;
import seedu.address.model.student.Student;
import seedu.address.testutil.student.StudentBuilder;


public class NotesDeleteCommandTest {
    private Model model = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
            getTypicalNotesManager(), getTypicalEventHistory(), new UserPrefs());

    /**
     * In order for a note to exist, the corresponding student tagged in the note must be in the class-list.
     * This method creates a student with name similar to the one in the note, simply for testing purposes.
     */
    public static Student createStudentStubForModel(Notes note) {
        Student student = new StudentBuilder().withName(note.getStudent()).build();
        return student;
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Notes noteToDelete = model.getFilteredNotesList().get(INDEX_FIRST_NOTE.getZeroBased());
        NotesDeleteCommand deleteCommand = new NotesDeleteCommand(INDEX_FIRST_NOTE);

        String expectedMessage = String.format(NotesDeleteCommand.MESSAGE_SUCCESS, noteToDelete);

        ModelManager expectedModel = new ModelManager(model.getTeaPet(), model.getAcademics(), model.getAdmin(),
                model.getNotesManager(), getTypicalEventHistory(), new UserPrefs());
        expectedModel.deleteNote(noteToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredNotesList().size() + 1);
        NotesDeleteCommand deleteCommand = new NotesDeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_NOTES_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        NotesDeleteCommand deleteFirstCommand = new NotesDeleteCommand(INDEX_FIRST_NOTE);
        NotesDeleteCommand deleteSecondCommand = new NotesDeleteCommand(INDEX_SECOND_NOTE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        NotesDeleteCommand deleteFirstCommandCopy = new NotesDeleteCommand(INDEX_FIRST_NOTE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
