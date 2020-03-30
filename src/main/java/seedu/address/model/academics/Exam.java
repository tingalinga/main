package seedu.address.model.academics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.student.Student;

/**
 * Represents an Exam assigned to the class.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Exam extends Assessment {

    private String description;
    private LocalDate date;

    private List<Submission> submissionTracker = new ArrayList<>();

    /**
     * Every entry field must be present and not null.
     * @param description description of exam.
     * @param date date of examination.
     */
    public Exam(String description, String date) {
        super(description, date);
        this.description = description;
        this.date = LocalDate.parse(date);
    }

    /**
     * Every entry field must be present and not null.
     * @param description description of exam.
     * @param date date of examination.
     */
    public Exam(String description, LocalDate date) {
        super(description, date.toString());
        this.description = description;
        this.date = date;
    }

    /**
     * Set the submission of each student to not submitted and unmarked.
     * @param students list of students assigned with the assessment.
     */
    public void setSubmissions(List<Student> students) {
        for (Student student: students) {
            submissionTracker.add(new Submission(student.getName().fullName));
        }
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
        return "exam";
    }

    /**
     * Returns the date of exam.
     * @return LocalDate exam date.
     */
    public LocalDate getExamDate() {
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
     * Edit the date of the exam.
     * @param examDate exam date.
     */
    public void setExamDate(String examDate) {
        this.date = LocalDate.parse(examDate);
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
                + "At: " + this.date + "\n";
    }

}
