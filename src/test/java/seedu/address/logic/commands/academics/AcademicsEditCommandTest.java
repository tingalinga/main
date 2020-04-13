package seedu.address.logic.commands.academics;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MATH_ASSIGNMENT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SCIENCE_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SCIENCE_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SCIENCE_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_SCIENCE_EXAM;
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
import seedu.address.logic.commands.academics.AcademicsEditCommand.EditAssessmentDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.academics.Academics;
import seedu.address.model.academics.Assessment;
import seedu.address.model.admin.Admin;
import seedu.address.model.event.EventHistory;
import seedu.address.model.notes.NotesManager;
import seedu.address.model.student.TeaPet;
import seedu.address.testutil.academics.AssessmentBuilder;
import seedu.address.testutil.academics.EditAssessmentDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * AcademicsEditCommand.
 */
public class AcademicsEditCommandTest {

    private Model model = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
            getTypicalNotesManager(), getTypicalEventHistory(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Assessment editedAssessment = new AssessmentBuilder().build();
        EditAssessmentDescriptor descriptor = new EditAssessmentDescriptorBuilder(editedAssessment).build();
        AcademicsEditCommand editCommand = new AcademicsEditCommand(INDEX_FIRST_ASSESSMENT, descriptor);

        String expectedMessage = String.format(AcademicsEditCommand.MESSAGE_SUCCESS, editedAssessment);

        Model expectedModel = new ModelManager(new TeaPet(model.getTeaPet()), model.getAcademics(),
                model.getAdmin(), model.getNotesManager(), model.getEventHistory(), new UserPrefs());
        expectedModel.setAssessment(model.getFilteredAcademicsList().get(0), editedAssessment);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastAssessment = Index.fromOneBased(model.getFilteredAcademicsList().size());
        Assessment lastAssessment = model.getFilteredAcademicsList().get(indexLastAssessment.getZeroBased());

        AssessmentBuilder assessmentInList = new AssessmentBuilder(lastAssessment);
        Assessment editedAssessment = assessmentInList.withDescription(VALID_DESCRIPTION_SCIENCE_EXAM)
                .withType(VALID_TYPE_SCIENCE_EXAM).withDate(VALID_DATE_SCIENCE_EXAM).build();

        EditAssessmentDescriptor descriptor = new EditAssessmentDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_SCIENCE_EXAM)
                .withType(VALID_TYPE_SCIENCE_EXAM).withDate(VALID_DATE_SCIENCE_EXAM).build();
        AcademicsEditCommand editCommand = new AcademicsEditCommand(indexLastAssessment, descriptor);

        String expectedMessage = String.format(AcademicsEditCommand.MESSAGE_SUCCESS, editedAssessment);

        Model expectedModel = new ModelManager(new TeaPet(model.getTeaPet()),
                new Academics(model.getAcademics()), new Admin(model.getAdmin()),
                new NotesManager(model.getNotesManager()), new EventHistory(model.getEventHistory()), new UserPrefs());

        expectedModel.setAssessment(lastAssessment, editedAssessment);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        AcademicsEditCommand editCommand = new AcademicsEditCommand(INDEX_FIRST_ASSESSMENT,
                new EditAssessmentDescriptor());
        Assessment editedAssessment = model.getFilteredAcademicsList().get(INDEX_FIRST_ASSESSMENT.getZeroBased());

        String expectedMessage = String.format(AcademicsEditCommand.MESSAGE_SUCCESS, editedAssessment);

        Model expectedModel = new ModelManager(new TeaPet(model.getTeaPet()),
                new Academics(model.getAcademics()), new Admin(model.getAdmin()),
                new NotesManager(model.getNotesManager()), new EventHistory(model.getEventHistory()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showAssessmentAtIndex(model, INDEX_FIRST_ASSESSMENT);

        Assessment assessmentInFilteredList = model.getFilteredAcademicsList()
                .get(INDEX_FIRST_ASSESSMENT.getZeroBased());
        Assessment editedAssessment = new AssessmentBuilder(assessmentInFilteredList)
                .withDescription(VALID_DESCRIPTION_SCIENCE_EXAM).build();
        AcademicsEditCommand editCommand = new AcademicsEditCommand(INDEX_FIRST_ASSESSMENT,
                new EditAssessmentDescriptorBuilder().withDescription(VALID_DESCRIPTION_SCIENCE_EXAM).build());

        String expectedMessage = String.format(AcademicsEditCommand.MESSAGE_SUCCESS, editedAssessment);

        Model expectedModel = new ModelManager(new TeaPet(model.getTeaPet()),
                new Academics(model.getAcademics()), new Admin(model.getAdmin()),
                new NotesManager(model.getNotesManager()), new EventHistory(model.getEventHistory()), new UserPrefs());

        expectedModel.setAssessment(model.getFilteredAcademicsList().get(0), editedAssessment);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAssessmentUnfilteredList_failure() {
        Assessment firstAssessment = model.getFilteredAcademicsList().get(INDEX_FIRST_ASSESSMENT.getZeroBased());
        EditAssessmentDescriptor descriptor = new EditAssessmentDescriptorBuilder(firstAssessment).build();
        AcademicsEditCommand editCommand = new AcademicsEditCommand(INDEX_SECOND_ASSESSMENT, descriptor);

        assertCommandFailure(editCommand, model, AcademicsEditCommand.MESSAGE_DUPLICATE_ASSESSMENT);
    }

    @Test
    public void execute_duplicateAssessmentFilteredList_failure() {
        showAssessmentAtIndex(model, INDEX_FIRST_ASSESSMENT);

        // edit assessment in filtered list into a duplicate in academics
        Assessment assessmentInList = model.getAcademics().getAcademicsList()
                .get(INDEX_SECOND_ASSESSMENT.getZeroBased());
        AcademicsEditCommand editCommand = new AcademicsEditCommand(INDEX_FIRST_ASSESSMENT,
                new EditAssessmentDescriptorBuilder(assessmentInList).build());

        assertCommandFailure(editCommand, model, AcademicsEditCommand.MESSAGE_DUPLICATE_ASSESSMENT);
    }

    @Test
    public void execute_invalidAssessmentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAcademicsList().size() + 1);
        EditAssessmentDescriptor descriptor = new EditAssessmentDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_SCIENCE_EXAM).build();
        AcademicsEditCommand editCommand = new AcademicsEditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ASSESSMENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of academics
     */
    @Test
    public void execute_invalidAssessmentIndexFilteredList_failure() {
        showAssessmentAtIndex(model, INDEX_FIRST_ASSESSMENT);
        Index outOfBoundIndex = INDEX_SECOND_ASSESSMENT;
        // ensures that outOfBoundIndex is still in bounds of academics list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAcademics().getAcademicsList().size());

        AcademicsEditCommand editCommand = new AcademicsEditCommand(outOfBoundIndex,
                new EditAssessmentDescriptorBuilder().withDescription(VALID_DESCRIPTION_SCIENCE_EXAM).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ASSESSMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AcademicsEditCommand standardCommand =
                new AcademicsEditCommand(INDEX_FIRST_ASSESSMENT, DESC_MATH_ASSIGNMENT);

        // same values -> returns true
        EditAssessmentDescriptor copyDescriptor = new EditAssessmentDescriptor(DESC_MATH_ASSIGNMENT);
        AcademicsEditCommand commandWithSameValues = new AcademicsEditCommand(INDEX_FIRST_ASSESSMENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new AcademicsExportCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AcademicsEditCommand(INDEX_SECOND_ASSESSMENT, DESC_MATH_ASSIGNMENT)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new AcademicsEditCommand(INDEX_FIRST_ASSESSMENT, DESC_SCIENCE_EXAM)));
        assertFalse(standardCommand.equals(new AcademicsEditCommand(INDEX_FIRST_ASSESSMENT, DESC_SCIENCE_EXAM)));
    }

}
