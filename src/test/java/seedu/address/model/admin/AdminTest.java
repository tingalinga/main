package seedu.address.model.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.admin.TypicalDates.JAN_26_2020;
import static seedu.address.testutil.admin.TypicalDates.getTypicalAdmin;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.admin.exceptions.DuplicateDateException;
import seedu.address.testutil.admin.DateBuilder;

public class AdminTest {

    private final Admin admin = new Admin();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), admin.getDateList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> admin.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAdmin_replacesData() {
        Admin newData = getTypicalAdmin();
        admin.resetData(newData);
        assertEquals(newData, admin);
    }

    @Test
    public void resetData_withDuplicateDates_throwsDuplicateDateException() {
        // Two dates with the same identity fields
        Date editedJan262020 = new DateBuilder(JAN_26_2020).build();
        List<Date> newDates = Arrays.asList(JAN_26_2020, editedJan262020);
        AdminTest.AdminStub newData = new AdminTest.AdminStub(newDates);
        assertThrows(DuplicateDateException.class, () -> admin.resetData(newData));
    }

    @Test
    public void hasDate_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> admin.hasDate(null));
    }

    @Test
    public void hasDate_dateNotInAdmin_returnsFalse() {
        assertFalse(admin.hasDate(JAN_26_2020));
    }

    @Test
    public void hasDate_studentInAdmin_returnsTrue() {
        admin.addDate(JAN_26_2020);
        assertTrue(admin.hasDate(JAN_26_2020));
    }

    @Test
    public void hasDate_dateWithSameIdentityFieldsInTeaPet_returnsTrue() {
        admin.addDate(JAN_26_2020);
        Date editedJan262020 = new DateBuilder(JAN_26_2020).build();
        assertTrue(admin.hasDate(editedJan262020));
    }

    @Test
    public void getDateList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> admin.getDateList().remove(0));
    }

    /**
     * A stub ReadOnlyAdmin whose dates list can violate interface constraints.
     */
    private static class AdminStub implements ReadOnlyAdmin {
        private final ObservableList<Date> dates = FXCollections.observableArrayList();

        AdminStub(Collection<Date> dates) {
            this.dates.setAll(dates);
        }

        @Override
        public ObservableList<Date> getDateList() {
            return dates;
        }
    }
}
