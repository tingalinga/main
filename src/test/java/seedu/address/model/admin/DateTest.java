package seedu.address.model.admin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDates.FEB_26_2020;
import static seedu.address.testutil.TypicalDates.JAN_26_2020;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DateBuilder;

public class DateTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Date date = new DateBuilder().build();
        assertThrows(IndexOutOfBoundsException.class, () -> date.getStudents().remove(7));
    }

    @Test
    public void isSameDate() {
        // same object -> returns true
        assertTrue(JAN_26_2020.isSameDate(JAN_26_2020));

        // null -> returns false
        assertFalse(JAN_26_2020.isSameDate(null));

        // different dates -> returns false
        assertFalse(JAN_26_2020.isSameDate(FEB_26_2020));

        // different dates -> returns false
        Date editedJAN262020 = new DateBuilder(JAN_26_2020).withDate(LocalDate.parse("2020-02-26")).build();
        assertFalse(JAN_26_2020.isSameDate(editedJAN262020));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Date JAN262020Copy = new DateBuilder(JAN_26_2020).build();
        assertTrue(JAN262020Copy.equals(JAN262020Copy));

        // same object -> returns true
        assertTrue(JAN_26_2020.equals(JAN_26_2020));

        // null -> returns false
        assertFalse(JAN_26_2020.equals(null));

        // different type -> returns false
        assertFalse(JAN_26_2020.equals(5));

        // different date -> returns false
        assertFalse(JAN_26_2020.equals(FEB_26_2020));

        // different dates -> returns false
        Date editedJAN262020 = new DateBuilder(JAN_26_2020).withDate(LocalDate.parse("2020-02-26")).build();
        assertFalse(JAN_26_2020.isSameDate(editedJAN262020));

    }
}
