package seedu.address.storage.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.event.TypicalEvents.EVENT1;
import static seedu.address.testutil.event.TypicalEvents.NON_TYPICAL_EVENT;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventHistory;
import seedu.address.model.event.ReadOnlyEvents;
import seedu.address.testutil.event.EventBuilder;
import seedu.address.testutil.event.TypicalEvents;




public class JsonEventStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get(
            "src", "test", "data", "JsonEventStorageTest");

    @TempDir
    public Path testFolder;

    private java.util.Optional<ReadOnlyEvents> readEventHistory(String filePath) throws Exception {
        return new JsonEventStorage(Paths.get(filePath)).readEvents(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }



    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readEventHistory("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readEventHistory("notJsonFormatEventHistory.json"));
    }

    @Test
    public void readEventRecord_invalidEventEventHistory_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEventHistory("invalidEventEventHistory.json"));
    }

    @Test
    public void readEventRecord_invalidAndValidEventsEventHistory_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readEventHistory("invalidAndValidEventsEventHistory.json"));
    }

    @Test
    public void readAndSaveEventRecord_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempNotesRecord.json");
        EventHistory originalEvent = TypicalEvents.getTypicalEventHistory();
        JsonEventStorage jsonEventStorage = new JsonEventStorage(filePath);

        jsonEventStorage.saveEvents(originalEvent, filePath);
        ReadOnlyEvents readBack = jsonEventStorage.readEvents(filePath).get();
        assertEquals(originalEvent, new EventHistory(readBack));

        List<Event> eventList = originalEvent.getEvents();
        eventList.set(0, NON_TYPICAL_EVENT);
        originalEvent = new EventHistory(eventList);
        jsonEventStorage.saveEvents(originalEvent, filePath);
        readBack = jsonEventStorage.readEvents(filePath).get();
        assertEquals(originalEvent, new EventHistory(readBack));

        Event modifiedEvent = new EventBuilder(EVENT1).withEventName("another event name").build();
        List<Event> eventList2 = originalEvent.getEvents();
        eventList2.add(modifiedEvent);
        originalEvent = new EventHistory(eventList2);
        // file path not specified
        jsonEventStorage.saveEvents(originalEvent);
        // file path not specified
        readBack = jsonEventStorage.readEvents().get();
        assertEquals(originalEvent, new EventHistory(readBack));

    }

    /**
     * Saves {@code eventRecord} at the specified {@code filePath}.
     */
    private void saveEventHistory(ReadOnlyEvents eventRecord, String filePath) {
        try {
            new JsonEventStorage(Paths.get(filePath))
                    .saveEvents(eventRecord, addToTestDataPathIfNotNull(filePath));
        } catch (IOException e) {
            throw new AssertionError("Something wrong if line reaches here.", e);
        }
    }

    @Test
    public void saveNotesRecord_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveEventHistory(new EventHistory(), null));
    }

    @Test
    public void saveEventRecord_nullEventRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveEventHistory(null, "SomeFile.json"));
    }


}

