package seedu.address.storage.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.notes.TypicalNotes.NOTE1;
import static seedu.address.testutil.notes.TypicalNotes.S_NOTE1;
import static seedu.address.testutil.notes.TypicalNotes.S_NOTE2;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotesManager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.notes.NotesManager;
import seedu.address.model.notes.ReadOnlyNotes;

public class JsonNotesManagerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonNotesManagerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readNotesManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readNotesManager(null));
    }

    private Optional<ReadOnlyNotes> readNotesManager(String filePath) throws Exception {
        return new JsonNotesManagerStorage(Paths.get(filePath)).readNotesManager(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readNotesManager("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readNotesManager("notJsonFormat.json"));
    }

    @Test
    public void readTeaPet_invalidNotes_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> readNotesManager("invalidNotesFormat.json"));
    }

    @Test
    public void readTeaPet_invalidAndValidNotes_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> readNotesManager("invalidAndValidNotesFormat.json"));
    }

    @Test
    public void readAndSaveNotesManager_allInOrder_success() throws Exception {
        Path filepath = testFolder.resolve("TempNotesManager.json");
        NotesManager original = getTypicalNotesManager();
        JsonNotesManagerStorage jsonNotesManagerStorage = new JsonNotesManagerStorage(filepath);

        // Save in new file and read back
        jsonNotesManagerStorage.saveNotesManager(original, filepath);
        ReadOnlyNotes readBack = jsonNotesManagerStorage.readNotesManager(filepath).get();
        assertEquals(original, new NotesManager(readBack));

        // Modify data, overwrite existing file, and read back
        original.addNote(S_NOTE1);
        original.removeNote(NOTE1);
        jsonNotesManagerStorage.saveNotesManager(original, filepath);
        readBack = jsonNotesManagerStorage.readNotesManager(filepath).get();
        assertEquals(original, new NotesManager(readBack));

        // Save and read without specifying the file path
        original.addNote(S_NOTE2);
        jsonNotesManagerStorage.saveNotesManager(original); // file path not specified
        readBack = jsonNotesManagerStorage.readNotesManager().get();
        assertEquals(original, new NotesManager(readBack));
    }

    @Test
    public void saveNotesManager_nullNotesManager_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveNotesManager(null, "SomeFile.json"));
    }

    /**
     * Saves {@code notesManager} at the specified {@code filePath}.
     */
    private void saveNotesManager(ReadOnlyNotes notesManager, String filePath) {
        try {
            new JsonNotesManagerStorage(Paths.get(filePath))
                    .saveNotesManager(notesManager, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveNotesManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveNotesManager(new NotesManager(), null));
    }

}
