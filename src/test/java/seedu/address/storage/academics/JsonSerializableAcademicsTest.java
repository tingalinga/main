package seedu.address.storage.academics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.academics.Academics;
import seedu.address.testutil.academics.TypicalAssessments;

public class JsonSerializableAcademicsTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAcademicsTest");
    private static final Path TYPICAL_ASSESSMENTS_FILE = TEST_DATA_FOLDER.resolve("typicalAssessmentsAcademics.json");
    private static final Path INVALID_ASSESSMENT_FILE = TEST_DATA_FOLDER.resolve("invalidAssessmentAcademics.json");
    private static final Path DUPLICATE_ASSESSMENT_FILE = TEST_DATA_FOLDER.resolve("duplicateAssessmentAcademics.json");

    @Test
    public void toModelType_typicalAssessmentsFile_success() throws Exception {
        JsonSerializableAcademics dataFromFile = JsonUtil.readJsonFile(TYPICAL_ASSESSMENTS_FILE,
                JsonSerializableAcademics.class).get();
        Academics academicsFromFile = dataFromFile.toModelType();
        Academics typicalAssessmentsAcademics = TypicalAssessments.getTypicalAcademics();
        assertEquals(academicsFromFile, typicalAssessmentsAcademics);
    }

    @Test
    public void toModelType_invalidAssessmentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAcademics dataFromFile = JsonUtil.readJsonFile(INVALID_ASSESSMENT_FILE,
                JsonSerializableAcademics.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateAssessments_throwsIllegalValueException() throws Exception {
        JsonSerializableAcademics dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ASSESSMENT_FILE,
                JsonSerializableAcademics.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAcademics.MESSAGE_DUPLICATE_ASSESSMENT,
                dataFromFile::toModelType);
    }
}
