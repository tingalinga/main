package seedu.address.model.academics;

import java.util.HashMap;
import java.util.Iterator;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;

/**
 * Represents a Assessment assigned to the class.
 */
public abstract class Assessment {

    // Assessment properties
    private String description;
    private Submission submission;

    // Tracking submissions
    private ObservableList<Student> students;
    private HashMap<Student, Submission> submissionTracker = new HashMap<>();

    public Assessment(String description, ObservableList<Student> students) {
        this.description = description;
        this.students = students;
        Iterator<Student> itr = students.iterator();
        while (itr.hasNext()) {
            submissionTracker.put(itr.next(), new Submission());
        }
    }

    public String getDescription() {
        return description;
    }

    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setSingleSubmitted(Student student) {
        submissionTracker.get(student).markAsSubmitted();
    }

    /**
     * Marks multiple students' assessments as submitted.
     * @param studentList List of students who have completed their assessment.
     */
    public void setMultipleSubmitted(Student ...studentList) {
        for (Student student: studentList) {
            submissionTracker.get(student).markAsSubmitted();
        }
    }

    public void markAssessment(Student student, int score) {
        submissionTracker.get(student).markAssessment(score);
    }
    
    public int averageScore() {
        int totalScore = 0;
        for (Student student: students) {
            totalScore += submissionTracker.get(student).getScore();
        }
        return totalScore / students.size();
    }

    @Override
    public String toString() {
        return "Assessment : " + this.description + "\n";
    }

}