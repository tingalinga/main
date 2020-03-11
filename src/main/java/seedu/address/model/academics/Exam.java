package seedu.address.model.academics;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;

/**
 * Represents a Exam assigned to the class.
 */
public class Exam extends Assessment {

    private String description;
    private LocalDateTime examDate;

    private HashMap<Student, Boolean> submissionTracker = new HashMap<>();

    public Exam(String description, String examDate, ObservableList<Student> students) {
        super(description, students);
        this.examDate = LocalDateTime.parse(examDate);
        Iterator<Student> itr = students.iterator();
        while (itr.hasNext()) {
            submissionTracker.put(itr.next(), false);
        }

    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getExamDate() {
        return examDate;
    }

    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setExamDate(String examDate) {
        this.examDate = LocalDateTime.parse(examDate);
    }

    @Override
    public String toString() {
        return "Exam : " + this.description + "\n";
    }

}