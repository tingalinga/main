package seedu.address.model.academics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;

/**
 * Represents an Exam assigned to the class.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Exam extends Assessment {

    private String description;
    private LocalDate examDate;

    private ObservableList<Student> students;
    private List<Submission> submissionTracker = new ArrayList<>();

    /**
     * Every entry field must be present and not null.
     * @param description description of exam.
     * @param examDate date of examination.
     */
    public Exam(String description, String examDate) {
        super(description);
        this.description = description;
        this.examDate = LocalDate.parse(examDate);
    }

    /**
     * Every entry field must be present and not null.
     * @param description description of exam.
     * @param examDate date of examination.
     */
    public Exam(String description, LocalDate examDate) {
        super(description);
        this.description = description;
        this.examDate = examDate;
    }

    /**
     * Sets the submission tracker to the new submission tracker.
     * @param newSubmissionTracker new submission tracker.
     */
    public void setSubmissionTracker(List<Submission> newSubmissionTracker) {
        for (Submission submission: newSubmissionTracker) {
            submissionTracker.add(submission);
        }
    }

    /**
     * Returns the date of exam.
     * @return exam date.
     */
    public LocalDate getExamDate() {
        return examDate;
    }

    /**
     * Edit the date of the exam.
     * @param examDate exam date.
     */
    public void setExamDate(String examDate) {
        this.examDate = LocalDate.parse(examDate);
    }

    /**
     * Returns the number of students who have yet to submit their assessment.
     */
    public int noOfUnsubmittedStudents() {
        int unsubmitted = 0;
        for (Submission submission: submissionTracker) {
            if (!submission.hasSubmitted()) {
                unsubmitted++;
            }
        }
        return unsubmitted;
    }

    /**
     * Returns the number of students who have submitted their assessment.
     */
    public int noOfSubmittedStudents() {
        int submitted = 0;
        for (Submission submission: submissionTracker) {
            if (submission.hasSubmitted()) {
                submitted++;
            }
        }
        return submitted;
    }

    @Override
    public String toString() {
        return "Exam: " + this.description + "\n"
                + "At: " + this.examDate + "\n";
    }

}
