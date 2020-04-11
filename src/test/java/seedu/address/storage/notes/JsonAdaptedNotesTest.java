package seedu.address.storage.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalNotes.NOTE1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.notes.Notes;

public class JsonAdaptedNotesTest {

    private static final String INVALID_STUDENT_NAME = "H@yley $oh";

    private static final String VALID_STUDENT_NAME = "Hayley Soh";
    private static final String VALID_CONTENT = "Test Content";
    private static final String VALID_PRIORITY = "HIGH";
    private static final String VALID_DATE = "29/03/2020 22:40";

    @Test
    public void toModelType_validNotesDetails_returnsNote() throws Exception {
        JsonAdaptedNotes note = new JsonAdaptedNotes(NOTE1);
        assertEquals(NOTE1, note.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalArgumentException() {
        JsonAdaptedNotes note = new JsonAdaptedNotes(INVALID_STUDENT_NAME, VALID_CONTENT,
                VALID_PRIORITY, VALID_DATE);

        String expectedMessage = Notes.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, note::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedNotes note = new JsonAdaptedNotes(null, VALID_CONTENT,
                VALID_PRIORITY, VALID_DATE);

        String expectedMessage = JsonAdaptedNotes.MISSING_FIELD_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, note::toModelType);
    }

    @Test
    public void toModelType_nullContent_throwsIllegalValueException() {
        JsonAdaptedNotes note = new JsonAdaptedNotes(VALID_STUDENT_NAME, null,
                VALID_PRIORITY, VALID_DATE);

        String expectedMessage = JsonAdaptedNotes.MISSING_FIELD_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, note::toModelType);
    }

    @Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        JsonAdaptedNotes note = new JsonAdaptedNotes(VALID_STUDENT_NAME, VALID_CONTENT,
                null, VALID_DATE);

        String expectedMessage = JsonAdaptedNotes.MISSING_FIELD_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, note::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedNotes note = new JsonAdaptedNotes(VALID_STUDENT_NAME, VALID_CONTENT,
                VALID_PRIORITY, null);

        String expectedMessage = JsonAdaptedNotes.MISSING_FIELD_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, note::toModelType);
    }
}
