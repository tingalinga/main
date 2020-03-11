package seedu.address.model.academics;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;

/**
 * Represents a Homework assigned to the class.
 */
public class Homework extends Assessment {

    private String description;
    private LocalDateTime deadline;

    private HashMap<Student, Submission> submissionTracker = new HashMap<>();

    public Homework(String description, String deadline, ObservableList<Student> students) {
        super(description, students);
        this.deadline = LocalDateTime.parse(deadline);
        Iterator<Student> itr = students.iterator();
        while (itr.hasNext()) {
            submissionTracker.put(itr.next(), new Submission());
        }

    }

    public String getDescription() {
        return description;
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
        return "Homework : " + this.description + "\n";
    }

}