package seedu.address.model.academics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SCIENCE_EXAM;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.academics.TypicalAssessments.SCIENCE_EXAM;
import static seedu.address.testutil.academics.TypicalAssessments.SCIENCE_HOMEWORK;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.academics.exceptions.AssessmentNotFoundException;
import seedu.address.model.academics.exceptions.DuplicateAssessmentException;
import seedu.address.testutil.academics.AssessmentBuilder;

public class UniqueAssessmentListTest {

    private final UniqueAssessmentList uniqueAssessmentList = new UniqueAssessmentList();

    @Test
    public void contains_nullAssessment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssessmentList.contains(null));
    }

    @Test
    public void contains_assessmentNotInList_returnsFalse() {
        assertFalse(uniqueAssessmentList.contains(SCIENCE_HOMEWORK));
    }

    @Test
    public void contains_assessmentInList_returnsTrue() {
        uniqueAssessmentList.add(SCIENCE_HOMEWORK);
        assertTrue(uniqueAssessmentList.contains(SCIENCE_HOMEWORK));
    }

    @Test
    public void contains_assessmentWithSameIdentityFieldsInList_returnsTrue() {
        uniqueAssessmentList.add(SCIENCE_HOMEWORK);
        Assessment editedScienceHomework = new AssessmentBuilder(SCIENCE_HOMEWORK).withDate(VALID_DATE_SCIENCE_EXAM)
                .build();
        assertTrue(uniqueAssessmentList.contains(editedScienceHomework));
    }

    @Test
    public void add_nullAssessment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssessmentList.add(null));
    }

    @Test
    public void add_duplicateAssessment_throwsDuplicateStudentException() {
        uniqueAssessmentList.add(SCIENCE_HOMEWORK);
        assertThrows(DuplicateAssessmentException.class, () -> uniqueAssessmentList.add(SCIENCE_HOMEWORK));
    }

    @Test
    public void setAssessment_nullTargetAssessment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> uniqueAssessmentList.setAssessment(null, SCIENCE_HOMEWORK));
    }

    @Test
    public void setAssessment_nullEditedAssessment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssessmentList.setAssessment(SCIENCE_HOMEWORK, null));
    }

    @Test
    public void setAssessment_targetAssessmentNotInList_throwsAssessmentNotFoundException() {
        assertThrows(AssessmentNotFoundException.class, ()
            -> uniqueAssessmentList.setAssessment(SCIENCE_HOMEWORK, SCIENCE_HOMEWORK));
    }

    @Test
    public void setAssessment_editedAssessmentIsSameAssessment_success() {
        uniqueAssessmentList.add(SCIENCE_HOMEWORK);
        uniqueAssessmentList.setAssessment(SCIENCE_HOMEWORK, SCIENCE_HOMEWORK);
        UniqueAssessmentList expectedUniqueAssessmentList = new UniqueAssessmentList();
        expectedUniqueAssessmentList.add(SCIENCE_HOMEWORK);
        assertEquals(expectedUniqueAssessmentList, uniqueAssessmentList);
    }

    @Test
    public void setAssessment_editedAssessmentHasSameIdentity_success() {
        uniqueAssessmentList.add(SCIENCE_HOMEWORK);
        Assessment editedScienceHomework = new AssessmentBuilder(SCIENCE_HOMEWORK).withDate(VALID_DATE_SCIENCE_EXAM)
                .build();
        uniqueAssessmentList.setAssessment(SCIENCE_HOMEWORK, editedScienceHomework);
        UniqueAssessmentList expectedUniqueAssessmentList = new UniqueAssessmentList();
        expectedUniqueAssessmentList.add(editedScienceHomework);
        assertEquals(expectedUniqueAssessmentList, uniqueAssessmentList);
    }

    @Test
    public void setAssessment_editedAssessmentHasDifferentIdentity_success() {
        uniqueAssessmentList.add(SCIENCE_HOMEWORK);
        uniqueAssessmentList.setAssessment(SCIENCE_HOMEWORK, SCIENCE_EXAM);
        UniqueAssessmentList expectedUniqueAssessmentList = new UniqueAssessmentList();
        expectedUniqueAssessmentList.add(SCIENCE_EXAM);
        assertEquals(expectedUniqueAssessmentList, uniqueAssessmentList);
    }

    @Test
    public void setAssessment_editedAssessmentHasNonUniqueIdentity_throwsDuplicateAssessmentException() {
        uniqueAssessmentList.add(SCIENCE_HOMEWORK);
        uniqueAssessmentList.add(SCIENCE_EXAM);
        assertThrows(DuplicateAssessmentException.class, ()
            -> uniqueAssessmentList.setAssessment(SCIENCE_HOMEWORK, SCIENCE_EXAM));
    }

    @Test
    public void remove_nullAssessment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssessmentList.remove(null));
    }

    @Test
    public void remove_assessmentDoesNotExist_throwsAssessmentNotFoundException() {
        assertThrows(AssessmentNotFoundException.class, () -> uniqueAssessmentList.remove(SCIENCE_HOMEWORK));
    }

    @Test
    public void remove_existingAssessment_removesAssessment() {
        uniqueAssessmentList.add(SCIENCE_HOMEWORK);
        uniqueAssessmentList.remove(SCIENCE_HOMEWORK);
        UniqueAssessmentList expectedUniqueAssessmentList = new UniqueAssessmentList();
        assertEquals(expectedUniqueAssessmentList, uniqueAssessmentList);
    }

    @Test
    public void setAssessments_nullUniqueAssessmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> uniqueAssessmentList.setAssessments((UniqueAssessmentList) null));
    }

    @Test
    public void setAssessments_uniqueAssessmentList_replacesOwnListWithProvidedUniqueAssessmentList() {
        uniqueAssessmentList.add(SCIENCE_HOMEWORK);
        UniqueAssessmentList expectedUniqueAssessmentList = new UniqueAssessmentList();
        expectedUniqueAssessmentList.add(SCIENCE_EXAM);
        uniqueAssessmentList.setAssessments(expectedUniqueAssessmentList);
        assertEquals(expectedUniqueAssessmentList, uniqueAssessmentList);
    }

    @Test
    public void setAssessments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssessmentList.setAssessments((List<Assessment>) null));
    }

    @Test
    public void setAssessments_list_replacesOwnListWithProvidedList() {
        uniqueAssessmentList.add(SCIENCE_HOMEWORK);
        List<Assessment> assessmentList = Collections.singletonList(SCIENCE_EXAM);
        uniqueAssessmentList.setAssessments(assessmentList);
        UniqueAssessmentList expectedUniqueAssessmentList = new UniqueAssessmentList();
        expectedUniqueAssessmentList.add(SCIENCE_EXAM);
        assertEquals(expectedUniqueAssessmentList, uniqueAssessmentList);
    }

    @Test
    public void setAssessments_listWithDuplicateAssessments_throwsDuplicateAssessmentException() {
        List<Assessment> listWithDuplicateAssessments = Arrays.asList(SCIENCE_HOMEWORK, SCIENCE_HOMEWORK);
        assertThrows(DuplicateAssessmentException.class, ()
            -> uniqueAssessmentList.setAssessments(listWithDuplicateAssessments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueAssessmentList.asUnmodifiableObservableList().remove(0));
    }
}
