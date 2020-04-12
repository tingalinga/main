package seedu.address.storage.academics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.academics.TypicalAssessments.MATH_HOMEWORK;
import static seedu.address.testutil.academics.TypicalAssessments.SCIENCE_EXAM;
import static seedu.address.testutil.academics.TypicalAssessments.getTypicalAcademics;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.academics.Academics;
import seedu.address.model.academics.ReadOnlyAcademics;

public class JsonAcademicsStorageTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonAcademicsStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAcademics_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAcademics(null));
    }

    private java.util.Optional<ReadOnlyAcademics> readAcademics(String filePath) throws Exception {
        return new JsonAcademicsStorage(Paths.get(filePath)).readAcademics(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAcademics("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAcademics("notJsonFormatAcademics.json"));
    }

    @Test
    public void readAcademics_invalidAssessmentAcademics_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAcademics("invalidAssessmentAcademics.json"));
    }

    @Test
    public void readAcademics_invalidAndValidAssessmentAcademics_throwDataConversionException() {
        assertThrows(DataConversionException.class, ()
            -> readAcademics("invalidAndValidAssessmentAcademics.json"));
    }

    @Test
    public void readAndSaveAcademics_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAcademics.json");
        Academics original = getTypicalAcademics();
        JsonAcademicsStorage jsonAcademicsStorage = new JsonAcademicsStorage(filePath);

        // Save in new file and read back
        jsonAcademicsStorage.saveAcademics(original, filePath);
        ReadOnlyAcademics readBack = jsonAcademicsStorage.readAcademics(filePath).get();
        assertEquals(original, new Academics(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addAssessment(MATH_HOMEWORK);
        jsonAcademicsStorage.saveAcademics(original, filePath);
        readBack = jsonAcademicsStorage.readAcademics(filePath).get();
        assertEquals(original, new Academics(readBack));

        // Save and read without specifying file path
        original.addAssessment(SCIENCE_EXAM);
        jsonAcademicsStorage.saveAcademics(original); // file path not specified
        readBack = jsonAcademicsStorage.readAcademics().get(); // file path not specified
        assertEquals(original, new Academics(readBack));

    }

    @Test
    public void saveAcademics_nullAcademics_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAcademics(null, "SomeFile.json"));
    }

    /**
     * Saves {@code academics} at the specified {@code filePath}.
     */
    private void saveAcademics(ReadOnlyAcademics academics, String filePath) {
        try {
            new JsonAcademicsStorage(Paths.get(filePath))
                    .saveAcademics(academics, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAcademics_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAcademics(new Academics(), null));
    }
}
