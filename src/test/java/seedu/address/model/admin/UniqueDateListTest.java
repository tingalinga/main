package seedu.address.model.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDates.FEB_26_2020;
import static seedu.address.testutil.TypicalDates.JAN_26_2020;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.admin.exceptions.DateNotFoundException;
import seedu.address.model.admin.exceptions.DuplicateDateException;
import seedu.address.testutil.DateBuilder;

public class UniqueDateListTest {

    private final UniqueDateList uniqueDateList = new UniqueDateList();

    @Test
    public void contains_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDateList.contains(null));
    }

    @Test
    public void contains_dateNotInList_returnsFalse() {
        assertFalse(uniqueDateList.contains(JAN_26_2020));
    }

    @Test
    public void contains_dateInList_returnsTrue() {
        uniqueDateList.add(JAN_26_2020);
        assertTrue(uniqueDateList.contains(JAN_26_2020));
    }

    @Test
    public void contains_dateWithSameIdentityFieldsInList_returnsTrue() {
        uniqueDateList.add(JAN_26_2020);
        Date editedJan262020 = new DateBuilder(JAN_26_2020).build();
        assertTrue(uniqueDateList.contains(editedJan262020));
    }

    @Test
    public void add_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDateList.add(null));
    }

    @Test
    public void add_duplicateDate_throwsDuplicateDateException() {
        uniqueDateList.add(JAN_26_2020);
        assertThrows(DuplicateDateException.class, () -> uniqueDateList.add(JAN_26_2020));
    }

    @Test
    public void setDAte_nullTargetDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDateList.setDate(null, JAN_26_2020));
    }

    @Test
    public void setDate_nullEditedDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDateList.setDate(JAN_26_2020, null));
    }

    @Test
    public void setDate_targetDateNotInList_throwsDateNotFoundException() {
        assertThrows(DateNotFoundException.class, () -> uniqueDateList.setDate(JAN_26_2020, JAN_26_2020));
    }

    @Test
    public void setDatet_editedDateIsSameDate_success() {
        uniqueDateList.add(JAN_26_2020);
        uniqueDateList.setDate(JAN_26_2020, JAN_26_2020);
        UniqueDateList expectedUniqueDateList = new UniqueDateList();
        expectedUniqueDateList.add(JAN_26_2020);
        assertEquals(expectedUniqueDateList, uniqueDateList);
    }

    @Test
    public void setDate_editedDateHasSameIdentity_success() {
        uniqueDateList.add(JAN_26_2020);
        Date editedJan262020 = new DateBuilder(JAN_26_2020).withDate(FEB_26_2020.getDate()).build();
        uniqueDateList.setDate(JAN_26_2020, editedJan262020);
        UniqueDateList expectedUniqueDateList = new UniqueDateList();
        expectedUniqueDateList.add(editedJan262020);
        assertEquals(expectedUniqueDateList, uniqueDateList);
    }

    @Test
    public void setDate_editedDateHasDifferentIdentity_success() {
        uniqueDateList.add(JAN_26_2020);
        uniqueDateList.setDate(JAN_26_2020, FEB_26_2020);
        UniqueDateList expectedUniqueDateList = new UniqueDateList();
        expectedUniqueDateList.add(FEB_26_2020);
        assertEquals(expectedUniqueDateList, uniqueDateList);
    }

    @Test
    public void setDate_editedDateHasNonUniqueIdentity_throwsDuplicateDateException() {
        uniqueDateList.add(JAN_26_2020);
        uniqueDateList.add(FEB_26_2020);
        assertThrows(DuplicateDateException.class, () -> uniqueDateList.setDate(JAN_26_2020, FEB_26_2020));
    }

    @Test
    public void remove_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDateList.remove(null));
    }

    @Test
    public void remove_dateDoesNotExist_throwsDateNotFoundException() {
        assertThrows(DateNotFoundException.class, () -> uniqueDateList.remove(JAN_26_2020));
    }

    @Test
    public void remove_existingDate_removesDate() {
        uniqueDateList.add(JAN_26_2020);
        uniqueDateList.remove(JAN_26_2020);
        UniqueDateList expectedUniqueDateList = new UniqueDateList();
        assertEquals(expectedUniqueDateList, uniqueDateList);
    }

    @Test
    public void setDates_nullUniqueDateList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDateList.setDates((UniqueDateList) null));
    }

    @Test
    public void setDates_uniqueDateList_replacesOwnListWithProvidedUniqueDateList() {
        uniqueDateList.add(JAN_26_2020);
        UniqueDateList expectedUniqueDateList = new UniqueDateList();
        expectedUniqueDateList.add(JAN_26_2020);
        uniqueDateList.setDates(expectedUniqueDateList);
        assertEquals(expectedUniqueDateList, uniqueDateList);
    }

    @Test
    public void setDates_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDateList.setDates((List<Date>) null));
    }

    @Test
    public void setDates_list_replacesOwnListWithProvidedList() {
        uniqueDateList.add(JAN_26_2020);
        List<Date> dateList = Collections.singletonList(FEB_26_2020);
        uniqueDateList.setDates(dateList);
        UniqueDateList expectedUniqueDateList = new UniqueDateList();
        expectedUniqueDateList.add(FEB_26_2020);
        assertEquals(expectedUniqueDateList, uniqueDateList);
    }

    @Test
    public void setDates_listWithDuplicateDates_throwsDuplicateDateException() {
        List<Date> listWithDuplicateDates = Arrays.asList(JAN_26_2020, JAN_26_2020);
        assertThrows(DuplicateDateException.class, () -> uniqueDateList.setDates(listWithDuplicateDates));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, (
        ) -> uniqueDateList.asUnmodifiableObservableList().remove(0));
    }
}
