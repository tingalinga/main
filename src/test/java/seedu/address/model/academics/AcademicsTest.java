package seedu.address.model.academics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SCIENCE_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_SCIENCE_EXAM;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.academics.TypicalAssessments.getTypicalAcademics;
import static seedu.address.testutil.academics.TypicalAssessments.SCIENCE_HOMEWORK;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.academics.exceptions.DuplicateAssessmentException;
import seedu.address.testutil.academics.AssessmentBuilder;

public class AcademicsTest {

    private final Academics academics = new Academics();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), academics.getAcademicsList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> academics.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAcademics_replacesData() {
        Academics newData = getTypicalAcademics();
        academics.resetData(newData);
        assertEquals(newData, academics);
    }

    @Test
    public void resetData_withDuplicateAssessments_throwsDuplicateAssessmentException() {
        // Two assessments with the same identity fields
        Assessment editedScienceHomework = new AssessmentBuilder(SCIENCE_HOMEWORK)
                .withType(VALID_TYPE_SCIENCE_EXAM)
                .withDate(VALID_DATE_SCIENCE_EXAM)
                .build();
        List<Assessment> newAssessments = Arrays.asList(SCIENCE_HOMEWORK, editedScienceHomework);
        AcademicsStub newData = new AcademicsStub(newAssessments);

        assertThrows(DuplicateAssessmentException.class, () -> academics.resetData(newData));
    }

    @Test
    public void hasAssessment_nullAssessment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> academics.hasAssessment(null));
    }

    @Test
    public void hasAssessment_assessmentNotInAcademics_returnsFalse() {
        assertFalse(academics.hasAssessment(SCIENCE_HOMEWORK));
    }

    @Test
    public void hasAssessment_assessmentInAcademics_returnsTrue() {
        academics.addAssessment(SCIENCE_HOMEWORK);
        assertTrue(academics.hasAssessment(SCIENCE_HOMEWORK));
    }

    @Test
    public void hasAssessment_assessmentWithSameIdentityFieldsInAcademics_returnsTrue() {
        academics.addAssessment(SCIENCE_HOMEWORK);
        Assessment editedScienceHomework = new AssessmentBuilder(SCIENCE_HOMEWORK)
                .withType(VALID_TYPE_SCIENCE_EXAM).withDate(VALID_DATE_SCIENCE_EXAM)
                .build();
        assertTrue(academics.hasAssessment(editedScienceHomework));
    }

    @Test
    public void getAcademicsList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> academics.getAcademicsList().remove(0));
    }

    /**
     * A stub ReadOnlyAcademics whose assessments list can violate interface constraints.
     */
    private static class AcademicsStub implements ReadOnlyAcademics {
        private final ObservableList<Assessment> assessments = FXCollections.observableArrayList();

        AcademicsStub(Collection<Assessment> assessments) {
            this.assessments.setAll(assessments);
        }

        @Override
        public ObservableList<Assessment> getAcademicsList() {
            return assessments;
        }

        @Override
        public ObservableList<Assessment> getHomeworkList() {
            return assessments;
        }

        @Override
        public ObservableList<Assessment> getExamList() {
            return assessments;
        }
    }
}
