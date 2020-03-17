package seedu.address.model.academics;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import seedu.address.model.student.Student;

/**
 * Represents a Exam assigned to the class.
 */
public class Exam extends Assessment {

    private String description;
    private LocalDate examDate;

    private HashMap<Student, Submission> submissionTracker = new HashMap<>();

    public Exam(String description, String examDate) {
        super(description);
        this.examDate = LocalDate.parse(examDate);
    }

    public String getDescription() {
        return description;
    }

    public void setStudents(List<Student> students) {
        for (Student student: students) {
            submissionTracker.put(student, new Submission());
        }
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setExamDate(String examDate) {
        this.examDate = LocalDate.parse(examDate);
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