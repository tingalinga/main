package seedu.address.model.homework;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;

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
        Iterator<Student> itr = students.iterator();
        while (itr.hasNext()) {
            submissionTracker.put(itr.next(), false);
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

    public void markSingleSubmitted(Student student) {
        submissionTracker.put(student, true);
    }

    public void markMultipleSubmitted(Student ...studentList) {
        for (Student student: studentList) {
            submissionTracker.put(student, true);
        }
    }

    @Override
    public String toString() {
        return "Homework : " + this.description + "\n";
    }

}
