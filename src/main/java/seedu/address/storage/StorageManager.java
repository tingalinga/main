package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.academics.ReadOnlyAcademics;
import seedu.address.model.notes.ReadOnlyNotes;
import seedu.address.storage.academics.AcademicsStorage;
import seedu.address.storage.notes.NotesManagerStorage;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private AcademicsStorage academicsStorage;
    private NotesManagerStorage notesManagerStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(AddressBookStorage addressBookStorage,
                          AcademicsStorage academicsStorage,
                          NotesManagerStorage notesManagerStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.academicsStorage = academicsStorage;
        this.notesManagerStorage = notesManagerStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

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


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    @Override
    public Path getSavedAcademicsFilePath() {
        return academicsStorage.getSavedAcademicsFilePath();
    }

    @Override
    public Optional<ReadOnlyAcademics> readAcademics() throws DataConversionException, IOException {
        return readAcademics(getSavedAcademicsFilePath());
    }

    @Override
    public Optional<ReadOnlyAcademics> readAcademics(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return academicsStorage.readAcademics(filePath);
    }

    @Override
    public void saveAcademics(ReadOnlyAcademics academics) throws IOException {
        saveAcademics(academics, academicsStorage.getSavedAcademicsFilePath());
    }

    @Override
    public void saveAcademics(ReadOnlyAcademics academics, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        academicsStorage.saveAcademics(academics, filePath);
    }

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



}
