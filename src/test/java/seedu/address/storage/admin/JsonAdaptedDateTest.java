package seedu.address.storage.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDates.JAN_26_2020;
import static seedu.address.testutil.TypicalStudents.BENSON;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.admin.Date;
import seedu.address.storage.teapet.JsonAdaptedStudent;

public class JsonAdaptedDateTest {
    private static final String INVALID_DATE = "DATE";
    private static final String INVALID_DATE_FORMAT = "2020-24-12";

    @Test
    public void toModelType_validDateDetails_returnsDate() throws Exception {
        JsonAdaptedDate date = new JsonAdaptedDate(JAN_26_2020);
        assertEquals(JAN_26_2020, date.toModelType());
    }

    @Test
    public void toModelType_invalidDateFormat_throwsIllegalValueException() {
        List<JsonAdaptedStudent> students = new ArrayList<>();
        students.add(new JsonAdaptedStudent(BENSON));
        boolean thrown = false;
        try {
            JsonAdaptedDate date =
                    new JsonAdaptedDate(LocalDate.parse(INVALID_DATE_FORMAT), students);
        } catch (DateTimeParseException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        List<JsonAdaptedStudent> students = new ArrayList<>();
        students.add(new JsonAdaptedStudent(BENSON));
        boolean thrown = false;
        try {
            JsonAdaptedDate date =
                    new JsonAdaptedDate(LocalDate.parse(INVALID_DATE), students);
        } catch (DateTimeParseException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        List<JsonAdaptedStudent> students = new ArrayList<>();
        students.add(new JsonAdaptedStudent(BENSON));
        JsonAdaptedDate date = new JsonAdaptedDate(null, students);
        String expectedMessage = String.format(JsonAdaptedDate.MISSING_FIELD_MESSAGE_FORMAT,
                Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, date::toModelType);
    }
}
