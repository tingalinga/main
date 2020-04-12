package seedu.address.model.academics;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SCIENCE_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SCIENCE_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_SCIENCE_EXAM;
import static seedu.address.testutil.academics.TypicalAssessments.SCIENCE_HOMEWORK;
import static seedu.address.testutil.academics.TypicalAssessments.SCIENCE_EXAM;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.academics.AssessmentBuilder;

public class AssessmentTest {

    @Test
    public void isSameAssessment() {
        // same object -> returns true
        assertTrue(SCIENCE_HOMEWORK.isSameAssessment(SCIENCE_HOMEWORK));

        // null -> returns false
        assertFalse(SCIENCE_HOMEWORK.isSameAssessment(null));

        // different phone and email -> returns false
        Assessment editedScienceHomework = new AssessmentBuilder(SCIENCE_HOMEWORK)
                .withDescription(VALID_DESCRIPTION_SCIENCE_EXAM)
                .withType(VALID_TYPE_SCIENCE_EXAM)
                .withDate(VALID_DATE_SCIENCE_EXAM)
                .build();
        assertFalse(SCIENCE_HOMEWORK.isSameAssessment(editedScienceHomework));

        // different description -> returns false
        editedScienceHomework = new AssessmentBuilder(SCIENCE_HOMEWORK)
                .withDescription(VALID_DESCRIPTION_SCIENCE_EXAM).build();
        assertFalse(SCIENCE_HOMEWORK.isSameAssessment(editedScienceHomework));

        // same description, different attributes -> returns true
        editedScienceHomework = new AssessmentBuilder(SCIENCE_HOMEWORK)
                .withDate(VALID_DATE_SCIENCE_EXAM).withType(VALID_TYPE_SCIENCE_EXAM).build();
        assertTrue(SCIENCE_HOMEWORK.isSameAssessment(editedScienceHomework));

        // same description, same type, different attributes -> returns true
        editedScienceHomework = new AssessmentBuilder(SCIENCE_HOMEWORK).withDate(VALID_DATE_SCIENCE_EXAM).build();
        assertTrue(SCIENCE_HOMEWORK.isSameAssessment(editedScienceHomework));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Assessment scienceHomeworkCopy = new AssessmentBuilder(SCIENCE_HOMEWORK).build();
        assertTrue(SCIENCE_HOMEWORK.equals(scienceHomeworkCopy));

        // same object -> returns true
        assertTrue(SCIENCE_HOMEWORK.equals(SCIENCE_HOMEWORK));

        // null -> returns false
        assertFalse(SCIENCE_HOMEWORK.equals(null));

        // different type -> returns false
        assertFalse(SCIENCE_HOMEWORK.equals(5));

        // different assessment -> returns false
        assertFalse(SCIENCE_HOMEWORK.equals(SCIENCE_EXAM));

        // different description -> returns false
        Assessment editedScienceHomework = new AssessmentBuilder(SCIENCE_HOMEWORK)
                .withDescription(VALID_DESCRIPTION_SCIENCE_EXAM).build();
        assertFalse(SCIENCE_HOMEWORK.equals(editedScienceHomework));

        // different date -> returns true
        editedScienceHomework = new AssessmentBuilder(SCIENCE_HOMEWORK)
                .withDate(VALID_DATE_SCIENCE_EXAM).build();
        assertTrue(SCIENCE_HOMEWORK.equals(editedScienceHomework));

        // different type -> returns false
        editedScienceHomework = new AssessmentBuilder(SCIENCE_HOMEWORK)
                .withType(VALID_TYPE_SCIENCE_EXAM).build();
        assertFalse(SCIENCE_HOMEWORK.equals(editedScienceHomework));
    }
}

