package seedu.address.model.academics;

import java.util.HashMap;
import java.util.Iterator;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;

/**
 * Represents a Homework assigned to the class.
 */
public abstract class Assessment {

    // Homework properties
    private String description;

    // Tracking submissions
    private ObservableList<Student> students;
    private HashMap<Student, Boolean> submissionTracker = new HashMap<>();

    public Assessment(String description, ObservableList<Student> students) {
        this.description = description;
        this.students = students;
        Iterator<Student> itr = students.iterator();
        while (itr.hasNext()) {
            submissionTracker.put(itr.next(), false);
        }

    }

    public String getDescription() {
        return description;
    }

    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    public void markSingleSubmitted(Student student) {
        submissionTracker.put(student, true);
    }

    /**
     * Marks multiple students' homework as submitted.
     * @param studentList List of students who have completed their homework.
     */
    public void markMultipleSubmitted(Student ...studentList) {
        for (Student student: studentList) {
            submissionTracker.put(student, true);
        }
    }

    @Override
    public String toString() {
        return "Assessment : " + this.description + "\n";
    }

}