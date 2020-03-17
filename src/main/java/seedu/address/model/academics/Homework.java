package seedu.address.model.academics;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import seedu.address.model.student.Student;

/**
 * Represents a Homework assigned to the class.
 */
public class Homework extends Assessment {

    private String description;
    private LocalDate deadline;

    private HashMap<Student, Submission> submissionTracker = new HashMap<>();

    public Homework(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    public String getDescription() {
        return description;
    }

    public void setStudents(List<Student> students) {
        for (Student student: students) {
            submissionTracker.put(student, new Submission());
        }
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setDeadline(String deadline) {
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        return "Homework: " + this.description + "\n"
                + "due by " + this.deadline;
    }

}