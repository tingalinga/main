package seedu.address.model.academics;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_MATH_ASSIGNMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SCIENCE_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SCIENCE_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_GRACE_PAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_SHARADH_RAJARAMAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_GRACE_PAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_SHARADH_RAJARAMAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_SCIENCE_EXAM;
import static seedu.address.testutil.academics.TypicalAssessments.SCIENCE_EXAM;
import static seedu.address.testutil.academics.TypicalAssessments.SCIENCE_HOMEWORK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public void addStudent() {
        Assessment editedScienceHomework = new AssessmentBuilder(SCIENCE_HOMEWORK).build();
        editedScienceHomework.addStudent(VALID_STUDENT_NAME_GRACE_PAN);
        Submission gracePanSubmission = new Submission(VALID_STUDENT_NAME_GRACE_PAN);
        assertTrue(editedScienceHomework.getSubmissionTracker().contains(gracePanSubmission));
    }

    @Test
    public void markAssessment() {
        Assessment editedScienceHomework = new AssessmentBuilder(SCIENCE_HOMEWORK).build();
        editedScienceHomework.addStudent(VALID_STUDENT_NAME_GRACE_PAN);

        // submitting
        List<String> studentToSubmit = new ArrayList<>();
        studentToSubmit.add(VALID_STUDENT_NAME_GRACE_PAN);
        editedScienceHomework.setSubmitted(studentToSubmit);

        // marking
        HashMap<String, Integer> submissions = new HashMap<>();
        submissions.put(VALID_STUDENT_NAME_GRACE_PAN, Integer.parseInt(VALID_SCORE_GRACE_PAN));
        editedScienceHomework.markAssessment(submissions);

        Submission gracePanSubmission =
                new Submission(VALID_STUDENT_NAME_GRACE_PAN, true, true, Integer.parseInt(VALID_SCORE_GRACE_PAN));
        assertTrue(editedScienceHomework.getSubmissionTracker().contains(gracePanSubmission));
    }

    @Test
    public void mark() {
        Assessment editedScienceHomework = new AssessmentBuilder(SCIENCE_HOMEWORK).build();
        editedScienceHomework.addStudent(VALID_STUDENT_NAME_GRACE_PAN);

        // submitting
        List<String> studentToSubmit = new ArrayList<>();
        studentToSubmit.add(VALID_STUDENT_NAME_GRACE_PAN);
        editedScienceHomework.setSubmitted(studentToSubmit);

        // marking
        editedScienceHomework.mark(VALID_STUDENT_NAME_GRACE_PAN, Integer.parseInt(VALID_SCORE_GRACE_PAN));

        Submission gracePanSubmission =
                new Submission(VALID_STUDENT_NAME_GRACE_PAN, true, true, Integer.parseInt(VALID_SCORE_GRACE_PAN));
        assertTrue(editedScienceHomework.getSubmissionTracker().contains(gracePanSubmission));
    }

    @Test
    public void noOfSubmittedStudents() {
        Assessment scienceHomework = new AssessmentBuilder(SCIENCE_HOMEWORK).build();
        assertTrue(scienceHomework.noOfSubmittedStudents() == 0);

        scienceHomework.addStudent(VALID_STUDENT_NAME_GRACE_PAN);

        // submitting
        List<String> studentToSubmit = new ArrayList<>();
        studentToSubmit.add(VALID_STUDENT_NAME_GRACE_PAN);
        scienceHomework.setSubmitted(studentToSubmit);

        assertTrue(scienceHomework.noOfSubmittedStudents() == 1);
    }

    @Test
    public void noOfMarkedStudents() {
        Assessment scienceHomework = new AssessmentBuilder(SCIENCE_HOMEWORK).build();
        assertTrue(scienceHomework.noOfMarkedSubmissions() == 0);

        scienceHomework.addStudent(VALID_STUDENT_NAME_GRACE_PAN);

        // submitting
        List<String> studentToSubmit = new ArrayList<>();
        studentToSubmit.add(VALID_STUDENT_NAME_GRACE_PAN);
        scienceHomework.setSubmitted(studentToSubmit);

        assertTrue(scienceHomework.noOfMarkedSubmissions() == 0);

        // marking
        scienceHomework.mark(VALID_STUDENT_NAME_GRACE_PAN, Integer.parseInt(VALID_SCORE_GRACE_PAN));

        assertTrue(scienceHomework.noOfMarkedSubmissions() == 1);
    }

    @Test
    public void averageScore() {
        Assessment scienceHomework = new AssessmentBuilder(SCIENCE_HOMEWORK).build();
        assertTrue(scienceHomework.averageScore() == 0);

        scienceHomework.addStudent(VALID_STUDENT_NAME_GRACE_PAN);
        scienceHomework.addStudent(VALID_STUDENT_NAME_SHARADH_RAJARAMAN);

        // submitting
        List<String> studentToSubmit = new ArrayList<>();
        studentToSubmit.add(VALID_STUDENT_NAME_GRACE_PAN);
        studentToSubmit.add(VALID_STUDENT_NAME_SHARADH_RAJARAMAN);
        scienceHomework.setSubmitted(studentToSubmit);

        assertTrue(scienceHomework.noOfMarkedSubmissions() == 0);

        // marking
        scienceHomework.mark(VALID_STUDENT_NAME_GRACE_PAN, Integer.parseInt(VALID_SCORE_GRACE_PAN));
        scienceHomework.mark(VALID_STUDENT_NAME_SHARADH_RAJARAMAN,
                Integer.parseInt(VALID_SCORE_SHARADH_RAJARAMAN));

        double averageScore = ((double) Integer.parseInt(VALID_SCORE_GRACE_PAN)
                + Integer.parseInt(VALID_SCORE_SHARADH_RAJARAMAN)) / 2;

        assertTrue(scienceHomework.averageScore() == averageScore);
    }

    @Test
    public void medianScore() {
        Assessment scienceHomework = new AssessmentBuilder(SCIENCE_HOMEWORK).build();
        assertTrue(scienceHomework.averageScore() == 0);

        scienceHomework.addStudent(VALID_STUDENT_NAME_GRACE_PAN);
        scienceHomework.addStudent(VALID_STUDENT_NAME_SHARADH_RAJARAMAN);

        // submitting
        List<String> studentToSubmit = new ArrayList<>();
        studentToSubmit.add(VALID_STUDENT_NAME_GRACE_PAN);
        studentToSubmit.add(VALID_STUDENT_NAME_SHARADH_RAJARAMAN);
        scienceHomework.setSubmitted(studentToSubmit);

        assertTrue(scienceHomework.noOfMarkedSubmissions() == 0);

        // marking
        scienceHomework.mark(VALID_STUDENT_NAME_GRACE_PAN, Integer.parseInt(VALID_SCORE_GRACE_PAN));

        assertTrue(scienceHomework.medianScore() == Integer.parseInt(VALID_SCORE_GRACE_PAN));

        scienceHomework.mark(VALID_STUDENT_NAME_SHARADH_RAJARAMAN,
                Integer.parseInt(VALID_SCORE_SHARADH_RAJARAMAN));

        int medianScore = (Integer.parseInt(VALID_SCORE_GRACE_PAN)
                + Integer.parseInt(VALID_SCORE_SHARADH_RAJARAMAN)) / 2;

        assertTrue(scienceHomework.medianScore() == medianScore);
    }

    @Test
    public void removeStudent() {
        Assessment editedScienceHomework = new AssessmentBuilder(SCIENCE_HOMEWORK).build();
        editedScienceHomework.addStudent(VALID_STUDENT_NAME_GRACE_PAN);

        Submission gracePanSubmission = new Submission(VALID_STUDENT_NAME_GRACE_PAN);
        assertTrue(editedScienceHomework.getSubmissionTracker().contains(gracePanSubmission));

        editedScienceHomework.removeStudent(VALID_STUDENT_NAME_GRACE_PAN);
        assertFalse(editedScienceHomework.getSubmissionTracker().contains(gracePanSubmission));
    }

    @Test
    public void checkValidDate() {
        // correct date format -> returns true
        assertTrue(Assessment.checkValidDate(VALID_DATE_MATH_ASSIGNMENT));

        // invalid date format -> returns false
        assertFalse(Assessment.checkValidDate(INVALID_DATE_DESC));

        // invalid year format -> returns false
        assertFalse(Assessment.checkValidDate("202-20-13"));

        // invalid month format -> returns false
        assertFalse(Assessment.checkValidDate("2020-20-13"));

        // invalid february day format -> returns false
        assertFalse(Assessment.checkValidDate("2020-02-30"));

        // invalid january day format -> returns false
        assertFalse(Assessment.checkValidDate("2020-01-41"));

        // invalid april day format -> returns false
        assertFalse(Assessment.checkValidDate("2020-04-31"));
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

