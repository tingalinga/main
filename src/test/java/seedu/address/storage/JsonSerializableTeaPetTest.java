package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.student.TeaPet;
import seedu.address.storage.teapet.JsonSerializableTeaPet;
import seedu.address.testutil.TypicalStudents;

public class JsonSerializableTeaPetTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTeaPetTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsTeaPet.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentTeaPet.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentTeaPet.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableTeaPet dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableTeaPet.class).get();
        TeaPet teaPetFromFile = dataFromFile.toModelType();
        TeaPet typicalStudentsTeaPet = TypicalStudents.getTypicalTeaPet();
        file:///Users/freddy/Desktop/main/build/reports/checkstyle/main.html
        assertEquals(teaPetFromFile, typicalStudentsTeaPet);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTeaPet dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableTeaPet.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableTeaPet dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableTeaPet.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTeaPet.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}
