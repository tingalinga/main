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
        Date editedJAN262020 = new DateBuilder(JAN_26_2020).build();
        assertTrue(uniqueDateList.contains(editedJAN262020));
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
    public void setStudent_editedDateIsSameDate_success() {
        uniqueDateList.add(JAN_26_2020);
        uniqueDateList.setDate(JAN_26_2020, JAN_26_2020);
        UniqueDateList expectedUniqueDateList = new UniqueDateList();
        expectedUniqueDateList.add(JAN_26_2020);
        assertEquals(expectedUniqueDateList, uniqueDateList);
    }

    @Test
    public void setDate_editedDateHasSameIdentity_success() {
        uniqueDateList.add(JAN_26_2020);
        Date editedJan262020 = new DateBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueDateList.setStudent(ALICE, editedAlice);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(editedAlice);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasDifferentIdentity_success() {
        uniqueStudentList.add(ALICE);
        uniqueStudentList.setStudent(ALICE, BOB);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(BOB);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasNonUniqueIdentity_throwsDuplicateStudentException() {
        uniqueStudentList.add(ALICE);
        uniqueStudentList.add(BOB);
        assertThrows(DuplicateStudentException.class, () -> uniqueStudentList.setStudent(ALICE, BOB));
    }

    @Test
    public void remove_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.remove(null));
    }

    @Test
    public void remove_studentDoesNotExist_throwsStudentNotFoundException() {
        assertThrows(StudentNotFoundException.class, () -> uniqueStudentList.remove(ALICE));
    }

    @Test
    public void remove_existingStudent_removesStudent() {
        uniqueStudentList.add(ALICE);
        uniqueStudentList.remove(ALICE);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudents_nullUniqueStudentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudents((UniqueStudentList) null));
    }

    @Test
    public void setStudents_uniqueStudentList_replacesOwnListWithProvidedUniqueStudentList() {
        uniqueStudentList.add(ALICE);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(BOB);
        uniqueStudentList.setStudents(expectedUniqueStudentList);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudents_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudents((List<Student>) null));
    }

    @Test
    public void setStudents_list_replacesOwnListWithProvidedList() {
        uniqueStudentList.add(ALICE);
        List<Student> studentList = Collections.singletonList(BOB);
        uniqueStudentList.setStudents(studentList);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(BOB);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudents_listWithDuplicateStudents_throwsDuplicateStudentException() {
        List<Student> listWithDuplicateStudents = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateStudentException.class, () -> uniqueStudentList.setStudents(listWithDuplicateStudents));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueStudentList.asUnmodifiableObservableList().remove(0));
    }
}
