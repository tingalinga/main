package seedu.address.model.academics;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;

/**
 * Represents a Homework assigned to the class.
 */
public class Homework extends Assessment {

    private String description;
    private LocalDateTime deadline;

    private HashMap<Student, Submission> submissionTracker = new HashMap<>();

    public Homework(String description, String deadline) {
        super(description);
        this.deadline = LocalDateTime.parse(deadline);
    }

    public String getDescription() {
        return description;
    }

    public void setStudents(List<Student> students) {
        for (Student student: students) {
            submissionTracker.put(student, new Submission());
        }
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setDeadline(String deadline) {
        this.deadline = LocalDateTime.parse(deadline);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append("Homework")
                .append("Due by: ")
                .append(getDeadline())
                .append("Unsubmitted: ")
                .append(noOfUnmarkedSubmissions());
        return builder.toString();
    }

}