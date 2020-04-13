package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;
import static seedu.address.testutil.student.TypicalStudents.getTypicalTeaPet;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.UserPrefs;
import seedu.address.model.academics.Assessment;
import seedu.address.model.event.EventHistory;
import seedu.address.model.event.ReadOnlyEvents;
import seedu.address.model.notes.NotesManager;
import seedu.address.model.student.ReadOnlyTeaPet;
import seedu.address.model.student.TeaPet;
import seedu.address.storage.academics.JsonAcademicsStorage;
import seedu.address.storage.admin.JsonAdminStorage;
import seedu.address.storage.event.JsonEventStorage;
import seedu.address.storage.notes.JsonNotesManagerStorage;
import seedu.address.storage.teapet.JsonTeaPetStorage;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonTeaPetStorage teaPetStorage = new JsonTeaPetStorage(getTempFilePath("ab"));
        JsonAcademicsStorage academicsStorage = new JsonAcademicsStorage(getTempFilePath("acad"));
        JsonAdminStorage adminStorage = new JsonAdminStorage(getTempFilePath("ad"));
        JsonNotesManagerStorage notesManagerStorage = new JsonNotesManagerStorage(getTempFilePath("notes"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonEventStorage eventStorage = new JsonEventStorage(getTempFilePath("event"));

        storageManager = new StorageManager(teaPetStorage, adminStorage, academicsStorage,
                userPrefsStorage, eventStorage, notesManagerStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void teaPetReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonTeaPetStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonTeaPetStorageTest} class.
         */
        TeaPet original = getTypicalTeaPet();
        storageManager.saveTeaPet(original);
        ReadOnlyTeaPet retrieved = storageManager.readTeaPet().get();
        assertEquals(original, new TeaPet(retrieved));
    }

    @Test
    public void getTeaPetFilePath() {
        assertNotNull(storageManager.getTeaPetFilePath());
    }

    /*
    @Test
    public void academicsReadSave() throws Exception {

        Academics original = getTypicalAcademics();
        storageManager.saveAcademics(original);
        ReadOnlyAcademics retrieved = storageManager.readAcademics().get();
        assertEquals(original, new Academics(retrieved));
    }
    */

    @Test
    public void getAcademicsFilePath() {
        assertNotNull(storageManager.getAcademicsFilePath());
    }

    @Test
    public void getEventHistoryFilePath() {
        assertNotNull(storageManager.getEventHistoryFilePath());
    }

    @Test
    public void getAdminFilePath() {
        assertNotNull(storageManager.getAdminFilePath());
    }

    @Test
    public void getNotesManagerFilePath() {
        assertNotNull(storageManager.getNotesManagerFilePath());
    }

    @Test
    public void eventRecordReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonEventStorage} class.
         */
        EventHistory original = getTypicalEventHistory();
        storageManager.saveEvents(original);
        ReadOnlyEvents retrieved = storageManager.readEvents().get();
        assertEquals(original, new EventHistory(retrieved));
    }



}
