package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TemperatureTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Temperature(null));
    }

    @Test
    public void constructor_invalidTemperature_throwsIllegalArgumentException() {
        String invalidTemperature = "";
        assertThrows(IllegalArgumentException.class, () -> new Temperature(invalidTemperature));
    }

    @Test
    public void isValidTemperature() {
        // null temperatures
        assertThrows(NullPointerException.class, () -> Temperature.isValidTemperature(null));

        // invalid temperatures
        assertFalse(Temperature.isValidTemperature("")); // empty string
        assertFalse(Temperature.isValidTemperature(" ")); // spaces only
        assertFalse(Temperature.isValidTemperature("91")); // less than 3 numbers
        assertFalse(Temperature.isValidTemperature("temperature")); // non-numeric
        assertFalse(Temperature.isValidTemperature("2f.5")); // alphabets within digits
        assertFalse(Temperature.isValidTemperature("23/5")); // invalid "/" symbol
        assertFalse(Temperature.isValidTemperature("1 5.3")); // spaces within digits

        // valid temperatures
        assertTrue(Temperature.isValidTemperature("Insert temperature here!")); // Insert comment allowed
        assertTrue(Temperature.isValidTemperature("36.5")); // 2 digits, followed by a decimal place
    }
}
