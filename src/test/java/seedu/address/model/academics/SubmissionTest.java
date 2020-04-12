package seedu.address.model.academics;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SCIENCE_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MARKED_GRACE_PAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MARKED_SHARADH_RAJARAMAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_GRACE_PAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_GRACE_PAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBMITTED_GRACE_PAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBMITTED_SHARADH_RAJARAMAN;
import static seedu.address.testutil.academics.TypicalSubmissions.GRACE_PAN;
import static seedu.address.testutil.academics.TypicalSubmissions.SIMON_LAM;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.academics.SubmissionBuilder;

public class SubmissionTest {

    @Test
    public void isSameSubmission() {
        // same object -> returns true
        assertTrue(SIMON_LAM.isSameSubmission(SIMON_LAM));

        // null -> returns false
        assertFalse(SIMON_LAM.isSameSubmission(null));

        // different student name, submitted, marked and score -> returns false
        Submission editedSimonLam = new SubmissionBuilder(SIMON_LAM)
                .withStudentName(VALID_DESCRIPTION_SCIENCE_EXAM)
                .withSubmitted(VALID_SUBMITTED_GRACE_PAN)
                .withMarked(VALID_MARKED_GRACE_PAN)
                .withScore(VALID_SCORE_GRACE_PAN)
                .build();
        assertFalse(SIMON_LAM.isSameSubmission(editedSimonLam));

        // different student name -> returns false
        editedSimonLam = new SubmissionBuilder(SIMON_LAM)
                .withStudentName(VALID_DESCRIPTION_SCIENCE_EXAM).build();
        assertFalse(SIMON_LAM.isSameSubmission(editedSimonLam));

        // same student name, different attributes -> returns false
        editedSimonLam = new SubmissionBuilder(SIMON_LAM)
                .withSubmitted(VALID_SUBMITTED_SHARADH_RAJARAMAN).withMarked(VALID_MARKED_SHARADH_RAJARAMAN).build();
        assertFalse(SIMON_LAM.isSameSubmission(editedSimonLam));

        // same description, same submitted and marked, different score -> returns false
        editedSimonLam = new SubmissionBuilder(SIMON_LAM).withScore(VALID_SCORE_GRACE_PAN).build();
        assertFalse(SIMON_LAM.isSameSubmission(editedSimonLam));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Submission simonLamCopy = new SubmissionBuilder(SIMON_LAM).build();
        assertTrue(SIMON_LAM.equals(simonLamCopy));

        // same object -> returns true
        assertTrue(SIMON_LAM.equals(SIMON_LAM));

        // null -> returns false
        assertFalse(SIMON_LAM.equals(null));

        // different type -> returns false
        assertFalse(SIMON_LAM.equals(5));

        // different assessment -> returns false
        assertFalse(SIMON_LAM.equals(GRACE_PAN));

        // different student name -> returns false
        Submission editedSimonLam = new SubmissionBuilder(SIMON_LAM)
                .withStudentName(VALID_STUDENT_NAME_GRACE_PAN).build();
        assertFalse(SIMON_LAM.equals(editedSimonLam));

        // different submitted -> returns false
        editedSimonLam = new SubmissionBuilder(SIMON_LAM)
                .withSubmitted(VALID_SUBMITTED_SHARADH_RAJARAMAN).build();
        assertFalse(SIMON_LAM.equals(editedSimonLam));

        // different marked -> returns false
        editedSimonLam = new SubmissionBuilder(SIMON_LAM)
                .withMarked(VALID_MARKED_SHARADH_RAJARAMAN).build();
        assertFalse(SIMON_LAM.equals(editedSimonLam));

        // different score -> returns false
        editedSimonLam = new SubmissionBuilder(SIMON_LAM)
                .withScore(VALID_SCORE_GRACE_PAN).build();
        assertFalse(SIMON_LAM.equals(editedSimonLam));
    }
}

