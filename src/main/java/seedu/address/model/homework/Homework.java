package seedu.address.model.homework;

import java.time.LocalDateTime;
import java.util.HashMap;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;

public class Homework {

    // Homework properties
    private String description;
    private LocalDateTime deadline;

    // Tracking submissions
    private final ObservableList<Student> students;
    private final HashMap<Student, Boolean> submissionTracker = new HashMap<>();

    public Homework(String description, String deadline, ObservableList<Student> students) {
        this.description = description;
        this.deadline = LocalDateTime.parse(deadline);
        this.students = students;
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
