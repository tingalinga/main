package seedu.address.model.academics;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;

/**
 * Represents a Exam assigned to the class.
 */
public class Exam extends Assessment {

    private String description;
    private LocalDateTime examDate;

    private HashMap<Student, Submission> submissionTracker = new HashMap<>();

    public Exam(String description, String examDate) {
        super(description);
        this.examDate = LocalDateTime.parse(examDate);
    }

    public String getDescription() {
        return description;
    }

    public void setStudents(List<Student> students) {
        for (Student student: students) {
            submissionTracker.put(student, new Submission());
        }
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
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append("Exam")
                .append("Date: ")
                .append(getExamDate())
                .append("Unsubmitted: ")
                .append(noOfUnmarkedSubmissions());
        return builder.toString();
    }

}