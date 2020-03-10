package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's temperature in the address book.
 */
public class Temperature {

    public static final String MESSAGE_CONSTRAINTS =
            "Temperature should only contain a 2 digits number followed by 1 decimal place";
    public static final String VALIDATION_REGEX = "\\d{2}" + "\\." + "\\d";
    public final String value;

    /**
     * Constructs a {@code Temperature}.
     *
     * @param temperature A valid temperature.
     */
    public Temperature(String temperature) {
        requireNonNull(temperature);
        checkArgument(isValidTemperature(temperature), MESSAGE_CONSTRAINTS);
        value = temperature;
    }

    /**
     * Returns true if a given string is a valid temperature.
     */
    public static boolean isValidTemperature(String test) {
        if (test.equals("Insert temperature here!")) {
            return true;
        } else {
            return test.matches(VALIDATION_REGEX);
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Temperature // instanceof handles nulls
                && value.equals(((Temperature) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
