package seedu.address.model.academics;

import java.time.LocalDate;
import java.util.HashMap;

import seedu.address.model.student.Student;

/**
 * Represents a Homework assigned to the class.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Homework extends Assessment {

    private String description;
    private LocalDate deadline;

    private HashMap<Student, Submission> submissionTracker = new HashMap<>();

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

    @Override
    public String toString() {
        return "Homework: " + this.description + "\n"
                + "Due by: " + this.deadline
                + "Submitted: " + noOfSubmittedStudents()
                + "Unsubmitted: " + noOfUnsubmittedStudents();
    }
}
