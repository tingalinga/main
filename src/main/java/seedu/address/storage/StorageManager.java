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
import seedu.address.model.event.Event;
import seedu.address.model.event.ReadOnlyEvents;
import seedu.address.storage.academics.AcademicsStorage;
import seedu.address.storage.event.EventStorage;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private AcademicsStorage academicsStorage;
    private UserPrefsStorage userPrefsStorage;
    private EventStorage eventStorage;


    public StorageManager(AddressBookStorage addressBookStorage,
                          AcademicsStorage academicsStorage,
                          UserPrefsStorage userPrefsStorage, EventStorage eventStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.academicsStorage = academicsStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.eventStorage = eventStorage;
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

    // ================ Event methods ==============================

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

}
