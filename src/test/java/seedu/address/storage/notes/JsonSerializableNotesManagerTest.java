package seedu.address.storage.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalNotes.getTypicalNotesManager;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.notes.NotesManager;

public class JsonSerializableNotesManagerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableNotesManagerTest");
    private static final Path TYPICAL_NOTES_FILE = TEST_DATA_FOLDER.resolve("typicalNotes.json");
    private static final Path INVALID_NOTES_FILE = TEST_DATA_FOLDER.resolve("invalidNotes.json");
    private static final Path DUPLICATE_NOTES_FILE = TEST_DATA_FOLDER.resolve("duplicateNotes.json");

    @Test
    public void toModelType_typicalNotesFile_success() throws Exception {
        JsonSerializableNotesManager dataFromFile = JsonUtil.readJsonFile(TYPICAL_NOTES_FILE,
                JsonSerializableNotesManager.class).get();
        NotesManager notesManagerFromFile = dataFromFile.toModelType();
        NotesManager typicalNotesManager = getTypicalNotesManager();
        assertEquals(notesManagerFromFile, typicalNotesManager);
    }

    @Test
    public void toModelType_invalidNotesFile_throwsIllegalArgumentException() throws Exception {
        JsonSerializableNotesManager dataFromFile = JsonUtil.readJsonFile(INVALID_NOTES_FILE,
                JsonSerializableNotesManager.class).get();
        assertThrows(IllegalArgumentException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateNotes_throwsIllegalValueException() throws Exception {
        JsonSerializableNotesManager dataFromFile = JsonUtil.readJsonFile(DUPLICATE_NOTES_FILE,
                JsonSerializableNotesManager.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableNotesManager.MESSAGE_DUPLICATE_NOTES,
                dataFromFile::toModelType);
    }

}
