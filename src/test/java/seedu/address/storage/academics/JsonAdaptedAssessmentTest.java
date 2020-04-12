package seedu.address.storage.academics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.address.logic.parser.AcademicsCommandParser.HELP_MESSAGE;
import static seedu.address.storage.academics.JsonAdaptedAssessment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.academics.TypicalAssessments.CHINESE_HOMEWORK;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.academics.AcademicsAddCommand;

public class JsonAdaptedAssessmentTest {
    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_TYPE = "assignment";
    private static final String INVALID_DATE = "2020/03/04";

    private static final String VALID_DESCRIPTION = CHINESE_HOMEWORK.getDescription();
    private static final String VALID_TYPE = CHINESE_HOMEWORK.getType();
    private static final String VALID_DATE = CHINESE_HOMEWORK.getDate()
            .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    private static final List<JsonAdaptedSubmission> VALID_SUBMISSIONS = CHINESE_HOMEWORK.getSubmissionTracker()
            .stream()
            .map(JsonAdaptedSubmission::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validAssessmentDetails_returnsAssessment() throws Exception {
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(CHINESE_HOMEWORK);
        assertEquals(CHINESE_HOMEWORK, assessment.toModelType());
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment =
                new JsonAdaptedAssessment(INVALID_DESCRIPTION, VALID_TYPE, VALID_DATE, VALID_SUBMISSIONS);

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AcademicsAddCommand.MESSAGE_USAGE);
        assertThrows(IllegalValueException.class, expectedMessage, assessment::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment =
                new JsonAdaptedAssessment(null, VALID_TYPE, VALID_DATE, VALID_SUBMISSIONS);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "DESCRIPTION");
        assertThrows(IllegalValueException.class, expectedMessage, assessment::toModelType);
    }

    @Test
    public void toModelType_invalidType_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment =
                new JsonAdaptedAssessment(VALID_DESCRIPTION, INVALID_TYPE, VALID_DATE, VALID_SUBMISSIONS);

        String expectedMessage = Messages.MESSAGE_INVALID_ASSESSMENT_TYPE;
        assertThrows(IllegalValueException.class, expectedMessage, assessment::toModelType);
    }

    @Test
    public void toModelType_nullType_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment =
                new JsonAdaptedAssessment(VALID_DESCRIPTION, null, INVALID_DATE, VALID_SUBMISSIONS);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "TYPE");
        assertThrows(IllegalValueException.class, expectedMessage, assessment::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment =
                new JsonAdaptedAssessment(VALID_DESCRIPTION, VALID_TYPE, INVALID_DATE, VALID_SUBMISSIONS);

        String expectedMessage = String.format(MESSAGE_INVALID_DATE_FORMAT, HELP_MESSAGE);
        assertThrows(IllegalValueException.class, expectedMessage, assessment::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment =
                new JsonAdaptedAssessment(VALID_DESCRIPTION, VALID_TYPE, null, VALID_SUBMISSIONS);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "DATE");
        assertThrows(IllegalValueException.class, expectedMessage, assessment::toModelType);
    }
}
