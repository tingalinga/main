package seedu.address.model.academics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;

/**
 * Represents a Homework assigned to the class.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Homework extends Assessment {

    private String description;
    private LocalDate deadline;

    private ObservableList<Student> students;
    private List<Submission> submissionTracker = new ArrayList<>();

    /**
     * Every entry field must be present and not null.
     * @param description description of homework.
     * @param deadline deadline of homework.
     */
    public Homework(String description, String deadline) {
        super(description);
        this.description = description;
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Every entry field must be present and not null.
     * @param description description of homework.
     * @param deadline deadline of homework.
     */
    public Homework(String description, LocalDate deadline) {
        super(description);
        this.description = description;
        this.deadline = deadline;
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
     * Returns the deadline of homework.
     * @return deadline of homework.
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Edit the deadline of the homework.
     * @param deadline homework deadline.
     */
    public void setDeadline(String deadline) {
        this.deadline = LocalDate.parse(deadline);
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
        return "Homework: " + this.description + "\n"
                + "Due by: " + this.deadline + "\n";
    }
}
