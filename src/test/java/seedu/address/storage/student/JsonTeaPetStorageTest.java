package seedu.address.storage.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.student.TypicalStudents.ALICE;
import static seedu.address.testutil.student.TypicalStudents.HOON;
import static seedu.address.testutil.student.TypicalStudents.IDA;
import static seedu.address.testutil.student.TypicalStudents.getTypicalTeaPet;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.student.ReadOnlyTeaPet;
import seedu.address.model.student.TeaPet;
import seedu.address.storage.teapet.JsonTeaPetStorage;

public class JsonTeaPetStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTeaPetStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTeaPet_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTeaPet(null));
    }

    private java.util.Optional<ReadOnlyTeaPet> readTeaPet(String filePath) throws Exception {
        return new JsonTeaPetStorage(Paths.get(filePath)).readTeaPet(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTeaPet("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTeaPet("notJsonFormatTeaPet.json"));
    }

    @Test
    public void readTeaPet_invalidStudentTeaPet_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTeaPet("invalidStudentTeaPet.json"));
    }

    @Test
    public void readTeaPet_invalidAndValidStudentTeaPet_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTeaPet("invalidAndValidStudentTeaPet.json"));
    }

    @Test
    public void readAndSaveTeaPet_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTeaPet.json");
        TeaPet original = getTypicalTeaPet();
        JsonTeaPetStorage jsonTeaPetStorage = new JsonTeaPetStorage(filePath);

        // Save in new file and read back
        jsonTeaPetStorage.saveTeaPet(original, filePath);
        ReadOnlyTeaPet readBack = jsonTeaPetStorage.readTeaPet(filePath).get();
        assertEquals(original, new TeaPet(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonTeaPetStorage.saveTeaPet(original, filePath);
        readBack = jsonTeaPetStorage.readTeaPet(filePath).get();
        assertEquals(original, new TeaPet(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonTeaPetStorage.saveTeaPet(original); // file path not specified
        readBack = jsonTeaPetStorage.readTeaPet().get(); // file path not specified
        assertEquals(original, new TeaPet(readBack));

    }

    @Test
    public void saveTeaPet_nullTeaPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTeaPet(null, "SomeFile.json"));
    }

    /**
     * Saves {@code teaPet} at the specified {@code filePath}.
     */
    private void saveTeaPet(ReadOnlyTeaPet teaPet, String filePath) {
        try {
            new JsonTeaPetStorage(Paths.get(filePath))
                    .saveTeaPet(teaPet, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTeaPet_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTeaPet(new TeaPet(), null));
    }
}
