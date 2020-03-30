package seedu.address.model.academics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;

/**
 * Represents a Homework assigned to the class.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Homework extends Assessment {

    private String description;
    private LocalDate date;

    private ObservableList<Student> students;
    private List<Submission> submissionTracker = new ArrayList<>();

    /**
     * Every entry field must be present and not null.
     * @param description description of homework.
     * @param date deadline of homework.
     */
    public Homework(String description, String date) {
        super(description, date);
        this.description = description;
        this.date = LocalDate.parse(date);
    }

    /**
     * Every entry field must be present and not null.
     * @param description description of homework.
     * @param date deadline of homework.
     */
    public Homework(String description, LocalDate date) {
        super(description, date.toString());
        this.description = description;
        this.date = date;
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
     * Returns the type of assessment.
     * @return String assessment type
     */
    public String getType() {
        return "homework";
    }

    /**
     * Returns the deadline of homework.
     * @return LocalDate deadline of homework.
     */
    public LocalDate getDeadline() {
        return date;
    }

    /**
     * Returns the deadline of homework as a string.
     * @return String deadline of homework.
     */
    public String getDateString() {
        return date.toString();
    }

    /**
     * Edit the deadline of the homework.
     * @param date homework deadline.
     */
    public void setDeadline(String date) {
        this.date = LocalDate.parse(date);
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
                + "Due by: " + this.date + "\n";
    }
}
