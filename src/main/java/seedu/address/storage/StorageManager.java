package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.academics.ReadOnlyAcademics;
import seedu.address.model.admin.ReadOnlyAdmin;
import seedu.address.model.event.ReadOnlyEvents;
import seedu.address.model.notes.ReadOnlyNotes;
import seedu.address.model.student.ReadOnlyTeaPet;
import seedu.address.storage.academics.AcademicsStorage;
import seedu.address.storage.admin.AdminStorage;
import seedu.address.storage.event.EventStorage;
import seedu.address.storage.notes.NotesManagerStorage;
import seedu.address.storage.teapet.TeaPetStorage;

/**
 * Manages storage of TeaPet data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TeaPetStorage teaPetStorage;
    private AdminStorage adminStorage;
    private AcademicsStorage academicsStorage;
    private NotesManagerStorage notesManagerStorage;
    private UserPrefsStorage userPrefsStorage;
    private EventStorage eventStorage;


    public StorageManager(TeaPetStorage teaPetStorage,
                          AdminStorage adminStorage,
                          AcademicsStorage academicsStorage,
                          UserPrefsStorage userPrefsStorage, EventStorage eventStorage,
                          NotesManagerStorage notesManagerStorage) {

        super();
        this.teaPetStorage = teaPetStorage;
        this.adminStorage = adminStorage;
        this.academicsStorage = academicsStorage;
        this.notesManagerStorage = notesManagerStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.eventStorage = eventStorage;
    }

    // ==================== USER PREFS ====================
    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ==================== TEA PET START ====================
    @Override
    public Path getTeaPetFilePath() {
        return teaPetStorage.getTeaPetFilePath();
    }

    @Override
    public Optional<ReadOnlyTeaPet> readTeaPet() throws DataConversionException, IOException {
        return readTeaPet(teaPetStorage.getTeaPetFilePath());
    }

    @Override
    public Optional<ReadOnlyTeaPet> readTeaPet(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return teaPetStorage.readTeaPet(filePath);
    }

    @Override
    public void saveTeaPet(ReadOnlyTeaPet teaPet) throws IOException {
        saveTeaPet(teaPet, teaPetStorage.getTeaPetFilePath());
    }

    @Override
    public void saveTeaPet(ReadOnlyTeaPet teaPet, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        teaPetStorage.saveTeaPet(teaPet, filePath);
    }
    // ==================== TEA PET END ====================

    // ==================== ACADEMICS START ====================
    @Override
    public Path getAcademicsFilePath() {
        return academicsStorage.getAcademicsFilePath();
    }

    @Override
    public Optional<ReadOnlyAcademics> readAcademics() throws DataConversionException, IOException {
        return readAcademics(getAcademicsFilePath());
    }

    @Override
    public Optional<ReadOnlyAcademics> readAcademics(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return academicsStorage.readAcademics(filePath);
    }

    @Override
    public void saveAcademics(ReadOnlyAcademics academics) throws IOException {
        saveAcademics(academics, academicsStorage.getAcademicsFilePath());
    }

    @Override
    public void saveAcademics(ReadOnlyAcademics academics, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        academicsStorage.saveAcademics(academics, filePath);
    }
    // ==================== ACADEMICS END ====================

    // ==================== ADMIN START ====================
    @Override
    public Path getAdminFilePath() {
        return adminStorage.getAdminFilePath();
    }

    @Override
    public Optional<ReadOnlyAdmin> readAdmin() throws DataConversionException, IOException {
        return readAdmin(adminStorage.getAdminFilePath());
    }

    @Override
    public Optional<ReadOnlyAdmin> readAdmin(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return adminStorage.readAdmin(filePath);
    }

    @Override
    public void saveAdmin(ReadOnlyAdmin admin) throws IOException {
        saveAdmin(admin, adminStorage.getAdminFilePath());
    }

    @Override
    public void saveAdmin(ReadOnlyAdmin admin, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        adminStorage.saveAdmin(admin, filePath);
    }
    // ==================== ADMIN END ====================

    // ==================== NOTES START ====================
    @Override
    public Path getNotesManagerFilePath() {
        return notesManagerStorage.getNotesManagerFilePath();
    }

    @Override
    public Optional<ReadOnlyNotes> readNotesManager() throws DataConversionException, IOException {
        return readNotesManager(getNotesManagerFilePath());
    }

    @Override
    public Optional<ReadOnlyNotes> readNotesManager(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return notesManagerStorage.readNotesManager(filePath);
    }

    @Override
    public void saveNotesManager(ReadOnlyNotes notes) throws IOException {
        saveNotesManager(notes, notesManagerStorage.getNotesManagerFilePath());
    }

    @Override
    public void saveNotesManager(ReadOnlyNotes notes, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        notesManagerStorage.saveNotesManager(notes, filePath);
    }
    // ==================== NOTES END ====================

    // ==================== EVENTS START ====================
    @Override
    public Path getEventHistoryFilePath() {
        return eventStorage.getEventHistoryFilePath();
    }

    @Override
    public Optional<ReadOnlyEvents> readEvents() throws DataConversionException, IOException {
        return readEvents(eventStorage.getEventHistoryFilePath());
    }

    @Override
    public Optional<ReadOnlyEvents> readEvents(Path filePath) throws DataConversionException, IOException {
        logger.fine("Reading events from event file: " + filePath);
        return eventStorage.readEvents(filePath);
    }

    @Override
    public void saveEvents(ReadOnlyEvents readOnlyEvents) throws IOException {
        saveEvents(readOnlyEvents, eventStorage.getEventHistoryFilePath());
    }

    @Override
    public void saveEvents(ReadOnlyEvents events, Path filePath) throws IOException {
        logger.fine("Writing events into event file :" + filePath);
        eventStorage.saveEvents(events, filePath);
    }
    // ==================== EVENTS END ====================
}
