package seedu.address.storage.academics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.academics.JsonAdaptedSubmission.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.academics.TypicalSubmissions.SIMON_LAM;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class JsonAdaptedSubmissionTest {
    private static final String VALID_STUDENT_NAME = SIMON_LAM.getStudentName();
    private static final String VALID_SUBMITTED = SIMON_LAM.hasSubmitted() ? "Submitted" : "Not Submitted";
    private static final String VALID_MARKED = SIMON_LAM.isMarked() ? "Marked" : "Not Marked";
    private static final String VALID_SCORE = Integer.toString(SIMON_LAM.getScore());

    @Test
    public void toModelType_validSubmissionDetails_returnsSubmission() throws Exception {
        JsonAdaptedSubmission submission = new JsonAdaptedSubmission(SIMON_LAM);
        assertEquals(SIMON_LAM, submission.toModelType());
    }

    @Test
    public void toModelType_nullStudentName_throwsIllegalValueException() {
        JsonAdaptedSubmission submission =
                new JsonAdaptedSubmission(null, VALID_SUBMITTED, VALID_MARKED, VALID_SCORE);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "STUDENT NAME");
        assertThrows(IllegalValueException.class, expectedMessage, submission::toModelType);
    }

    @Test
    public void toModelType_nullSubmitted_throwsIllegalValueException() {
        JsonAdaptedSubmission submission =
                new JsonAdaptedSubmission(VALID_STUDENT_NAME, null, VALID_MARKED, VALID_SCORE);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "SUBMITTED");
        assertThrows(IllegalValueException.class, expectedMessage, submission::toModelType);
    }

    @Test
    public void toModelType_nullMarked_throwsIllegalValueException() {
        JsonAdaptedSubmission submission =
                new JsonAdaptedSubmission(VALID_STUDENT_NAME, VALID_SUBMITTED, null, VALID_SCORE);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "MARKED");
        assertThrows(IllegalValueException.class, expectedMessage, submission::toModelType);
    }

    @Test
    public void toModelType_nullScore_throwsIllegalValueException() {
        JsonAdaptedSubmission submission =
                new JsonAdaptedSubmission(VALID_STUDENT_NAME, VALID_SUBMITTED, VALID_MARKED, null);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "SCORE");
        assertThrows(IllegalValueException.class, expectedMessage, submission::toModelType);
    }
}
