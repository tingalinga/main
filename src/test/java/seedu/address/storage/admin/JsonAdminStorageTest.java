package seedu.address.storage.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.admin.TypicalDates.JAN_26_2020;
import static seedu.address.testutil.admin.TypicalDates.getTypicalAdmin;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.admin.Admin;
import seedu.address.model.admin.ReadOnlyAdmin;

public class JsonAdminStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAdminStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAdmin_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAdmin(null));
    }

    private java.util.Optional<ReadOnlyAdmin> readAdmin(String filePath) throws Exception {
        return new JsonAdminStorage(Paths.get(filePath)).readAdmin(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAdmin("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAdmin("notJsonFormatAdmin.json"));
    }

    @Test
    public void readAdmin_invalidDateAdmin_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAdmin("invalidDateAdmin.json"));
    }

    @Test
    public void readTeaPet_invalidAndValidDateAdmin_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAdmin("invalidAndValidDateAdmin.json"));
    }

    @Test
    public void readAndSaveAdmin_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAdmin.json");
        Admin original = getTypicalAdmin();
        JsonAdminStorage jsonAdminStorage = new JsonAdminStorage(filePath);

        // Save in new file and read back
        jsonAdminStorage.saveAdmin(original, filePath);
        ReadOnlyAdmin readBack = jsonAdminStorage.readAdmin(filePath).get();
        assertEquals(original, new Admin(readBack));

        // Modify data, overwrite exiting file, and read back
        original.removeDate(JAN_26_2020);
        jsonAdminStorage.saveAdmin(original, filePath);
        readBack = jsonAdminStorage.readAdmin(filePath).get();
        assertEquals(original, new Admin(readBack));

        // Save and read without specifying file path
        original.addDate(JAN_26_2020);
        jsonAdminStorage.saveAdmin(original); // file path not specified
        readBack = jsonAdminStorage.readAdmin().get(); // file path not specified
        assertEquals(original, new Admin(readBack));

    }

    @Test
    public void saveTeaPet_nullAdmin_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAdmin(null, "SomeFile.json"));
    }

    /**
     * Saves {@code teaPet} at the specified {@code filePath}.
     */
    private void saveAdmin(ReadOnlyAdmin admin, String filePath) {
        try {
            new JsonAdminStorage(Paths.get(filePath))
                    .saveAdmin(admin, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAdmin_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAdmin(new Admin(), null));
    }
}
