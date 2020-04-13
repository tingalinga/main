package seedu.address.logic.commands.academics;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.academics.TypicalAssessments.getTypicalAcademics;
import static seedu.address.testutil.admin.TypicalDates.getTypicalAdmin;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotesManager;
import static seedu.address.testutil.student.TypicalStudents.getTypicalTeaPet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.academics.Assessment;
import seedu.address.testutil.academics.AssessmentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AcademicsAddCommand}.
 */
public class AcademicsAddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(), getTypicalNotesManager(),
                getTypicalEventHistory(), new UserPrefs());
    }

    @Test
    public void execute_newAssessment_success() {
        Assessment validAssessment = new AssessmentBuilder().build();

        Model expectedModel = new ModelManager(model.getTeaPet(), model.getAcademics(), model.getAdmin(),
                model.getNotesManager(), model.getEventHistory(), new UserPrefs());

        expectedModel.addAssessment(validAssessment);

        assertCommandSuccess(new AcademicsAddCommand(validAssessment), model,
                String.format(AcademicsAddCommand.MESSAGE_SUCCESS, validAssessment), expectedModel);
    }

    @Test
    public void execute_duplicateAssessment_throwsCommandException() {
        Assessment assessmentInList = model.getAcademics().getAcademicsList().get(0);
        assertCommandFailure(new AcademicsAddCommand(assessmentInList), model,
                AcademicsAddCommand.MESSAGE_DUPLICATE_ASSESSMENT);
    }

}
