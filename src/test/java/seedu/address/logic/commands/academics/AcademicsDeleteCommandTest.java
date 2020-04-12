package seedu.address.logic.commands.academics;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAssessmentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSESSMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ASSESSMENT;
import static seedu.address.testutil.academics.TypicalAssessments.getTypicalAcademics;
import static seedu.address.testutil.admin.TypicalDates.getTypicalAdmin;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotesManager;
import static seedu.address.testutil.student.TypicalStudents.getTypicalTeaPet;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.academics.Assessment;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code AcademicsDeleteCommand}.
 */
public class AcademicsDeleteCommandTest {

    private Model model = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
            getTypicalNotesManager(), getTypicalEventHistory(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Assessment assessmentToDelete = model.getFilteredAcademicsList().get(INDEX_FIRST_ASSESSMENT.getZeroBased());
        AcademicsDeleteCommand deleteCommand = new AcademicsDeleteCommand(INDEX_FIRST_ASSESSMENT);

        String expectedMessage = String.format(AcademicsDeleteCommand.MESSAGE_SUCCESS, assessmentToDelete);

        ModelManager expectedModel = new ModelManager(model.getTeaPet(), model.getAcademics(), model.getAdmin(),
                model.getNotesManager(), model.getEventHistory(), new UserPrefs());
        expectedModel.deleteAssessment(assessmentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAcademicsList().size() + 1);
        AcademicsDeleteCommand deleteCommand = new AcademicsDeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ASSESSMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showAssessmentAtIndex(model, INDEX_FIRST_ASSESSMENT);

        Assessment assessmentToDelete = model.getFilteredAcademicsList().get(INDEX_FIRST_ASSESSMENT.getZeroBased());
        AcademicsDeleteCommand deleteCommand = new AcademicsDeleteCommand(INDEX_FIRST_ASSESSMENT);

        String expectedMessage = String.format(AcademicsDeleteCommand.MESSAGE_SUCCESS, assessmentToDelete);

        Model expectedModel = new ModelManager(model.getTeaPet(), model.getAcademics(), model.getAdmin(),
                model.getNotesManager(), model.getEventHistory(), new UserPrefs());

        expectedModel.deleteAssessment(assessmentToDelete);
        showNoAssessment(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        AcademicsDeleteCommand deleteFirstCommand = new AcademicsDeleteCommand(INDEX_FIRST_ASSESSMENT);
        AcademicsDeleteCommand deleteSecondCommand = new AcademicsDeleteCommand(INDEX_SECOND_ASSESSMENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        AcademicsDeleteCommand deleteFirstCommandCopy = new AcademicsDeleteCommand(INDEX_FIRST_ASSESSMENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoAssessment(Model model) {
        model.updateFilteredAcademicsList(p -> false);

        assertTrue(model.getFilteredAcademicsList().isEmpty());
    }
}
