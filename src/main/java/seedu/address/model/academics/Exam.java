package seedu.address.model.academics;

import java.time.LocalDate;
import java.util.HashMap;

import seedu.address.model.student.Student;

/**
 * Represents an Exam assigned to the class.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Exam extends Assessment {

    private String description;
    private LocalDate examDate;

    private HashMap<Student, Submission> submissionTracker = new HashMap<>();

    /**
     * Every entry field must be present and not null.
     * @param description description of assessment.
     * @param examDate date of examination.
     */
    public Exam(String description, String examDate) {
        super(description);
        this.description = description;
        this.examDate = LocalDate.parse(examDate);
    }

    /**
     * Returns the date of exam.
     * @return exam date.
     */
    public LocalDate getExamDate() {
        return examDate;
    }

    /**
     * Edit the date of the exam.
     * @param examDate exam date.
     */
    public void setExamDate(String examDate) {
        this.examDate = LocalDate.parse(examDate);
    }

    @Override
    public String toString() {
        return "Exam: " + this.description + "\n"
                + "Due by " + this.examDate;
    }

}
