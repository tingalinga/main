package seedu.address.logic.commands.academics;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_GRACE_PAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_SHARADH_RAJARAMAN;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSESSMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ASSESSMENT;
import static seedu.address.testutil.academics.TypicalAssessments.getTypicalAcademics;
import static seedu.address.testutil.admin.TypicalDates.getTypicalAdmin;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotesManager;
import static seedu.address.testutil.student.TypicalStudents.getTypicalTeaPet;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code AcademicsMarkCommand}.
 */
public class AcademicsMarkCommandTest {

    private Model model = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
            getTypicalNotesManager(), getTypicalEventHistory(), new UserPrefs());

    /*
    @Test
    public void execute_validIndexUnfilteredList_success() throws CommandException {
        Assessment assessmentToSubmit = model.getFilteredAcademicsList().get(INDEX_FIRST_ASSESSMENT.getZeroBased());
        List<String> studentsToSubmit = new ArrayList<>();
        studentsToSubmit.add(VALID_NAME_AMY);
        AcademicsSubmitCommand submitCommand = new AcademicsSubmitCommand(INDEX_FIRST_ASSESSMENT, studentsToSubmit);

        String expectedMessage = AcademicsSubmitCommand.MESSAGE_SUCCESS + String.join("\n", studentsToSubmit);

        ModelManager expectedModel = new ModelManager(model.getTeaPet(), model.getAcademics(), model.getAdmin(),
                model.getNotesManager(), model.getEventHistory(), new UserPrefs());
        expectedModel.submitAssessment(assessmentToSubmit, studentsToSubmit);

        assertCommandSuccess(submitCommand, model, expectedMessage, expectedModel);
    }
    */

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() throws CommandException {
        List<String> studentsToMark = new ArrayList<>();
        studentsToMark.add(VALID_NAME_AMY + "-" + VALID_SCORE_GRACE_PAN);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAcademicsList().size() + 1);
        AcademicsMarkCommand submitCommand = new AcademicsMarkCommand(outOfBoundIndex, studentsToMark);

        assertCommandFailure(submitCommand, model, Messages.MESSAGE_INVALID_ASSESSMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() throws CommandException {
        List<String> studentsToMark = new ArrayList<>();
        studentsToMark.add(VALID_NAME_AMY + "-" + VALID_SCORE_GRACE_PAN);
        AcademicsMarkCommand markFirstCommand =
                new AcademicsMarkCommand(INDEX_FIRST_ASSESSMENT, studentsToMark);
        studentsToMark.add(VALID_NAME_BOB + "-" + VALID_SCORE_SHARADH_RAJARAMAN);
        AcademicsMarkCommand markSecondCommand =
                new AcademicsMarkCommand(INDEX_SECOND_ASSESSMENT, studentsToMark);

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));

        // same values -> returns true
        AcademicsMarkCommand markFirstCommandCopy =
                new AcademicsMarkCommand(INDEX_FIRST_ASSESSMENT, studentsToMark);
        assertTrue(markFirstCommand.equals(markFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstCommand.equals(null));

        // different assessment index -> returns false
        assertFalse(markFirstCommand.equals(markSecondCommand));
    }
}
