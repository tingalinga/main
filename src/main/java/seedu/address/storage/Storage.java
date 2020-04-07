package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

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
 * API of the Storage component
 */
public interface Storage extends TeaPetStorage, AcademicsStorage, NotesManagerStorage,
        UserPrefsStorage, AdminStorage, EventStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    // ==================== TEA PET START ====================
    @Override
    Path getTeaPetFilePath();

    @Override
    Optional<ReadOnlyTeaPet> readTeaPet() throws DataConversionException, IOException;

    @Override
    void saveTeaPet(ReadOnlyTeaPet teaPet) throws IOException;
    // ==================== TEA PET END ====================

    // ==================== ACADEMICS START ====================
    @Override
    Path getAcademicsFilePath();

    @Override
    Optional<ReadOnlyAcademics> readAcademics() throws DataConversionException, IOException;

    @Override
    void saveAcademics(ReadOnlyAcademics academics) throws IOException;
    // ==================== ACADEMICS END ====================

    // ==================== ADMIN START ====================
    @Override
    Path getAdminFilePath();

    @Override
    Optional<ReadOnlyAdmin> readAdmin() throws DataConversionException, IOException;

    @Override
    void saveAdmin(ReadOnlyAdmin admin) throws IOException;

    // ==================== ADMIN END ====================

    // ==================== NOTES START ====================
    @Override
    Optional<ReadOnlyNotes> readNotesManager() throws DataConversionException, IOException;

    @Override
    void saveNotesManager(ReadOnlyNotes notes) throws IOException;

    @Override
    Path getNotesManagerFilePath();
    // ==================== NOTES END ====================

    // ==================== EVENTS START ====================
    @Override
    Optional<ReadOnlyEvents> readEvents() throws DataConversionException, IOException;

    @Override
    Path getEventHistoryFilePath();

    @Override
    void saveEvents(ReadOnlyEvents readOnlyEvents) throws IOException;
    // ==================== EVENTS END ====================
}
