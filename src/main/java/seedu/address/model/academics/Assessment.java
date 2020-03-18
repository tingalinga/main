package seedu.address.model.academics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;

/**
 * Represents an Assessment given to the class to complete.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Assessment {

    // Assessment properties
    private String description;
    private Submission submission;

    // Tracking submissions
    private ObservableList<Student> students;
    private HashMap<Student, Submission> submissionTracker = new HashMap<>();

    /**
     * Every entry field must be present and not null.
     * @param description description of assessment.
     */
    public Assessment(String description) {
        this.description = description;
        this.students = null;
    }

    /**
     * Returns the description of the assessment.
     * @return description of assessment.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the submission of each student to not submitted and unmarked.
     * @param students list of students assigned with the assessment.
     */
    public void setStudents(List<Student> students) {
        for (Student student: students) {
            submissionTracker.put(student, new Submission());
        }
    }

    /**
     * Edit the description of the assessment.
     * @param newDescription new description of the assessment.
     */
    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Submits the submission of the student.
     * @param student student submitting his/her assessment.
     */
    public void setSingleSubmitted(Student student) {
        submissionTracker.get(student).markAsSubmitted();
    }

    /**
     * Submits multiple students' assessments.
     * @param studentList list of students who have completed their assessment.
     */
    public void setMultipleSubmitted(Student ...studentList) {
        for (Student student: studentList) {
            submissionTracker.get(student).markAsSubmitted();
        }
    }

    /**
     * Marks student's submission and assigns a score to the student's submission.
     * @param student student submitting his/her assessment.
     * @param score score given to the student's submission.
     */
    public void mark(Student student, int score) {
        submissionTracker.get(student).markAssessment(score);
    }

    /**
     * Returns a list of students who have yet to submit their assessment.
     */
    public ArrayList<Student> checkUnsubmittedStudents() {
        ArrayList<Student> unsubmitted = new ArrayList<>();
        for (Student student: students) {
            if (!submissionTracker.get(student).hasSubmitted()) {
                unsubmitted.add(student);
            }
        }
        return unsubmitted;
    }

    /**
     * Returns a list of students whose submissions have not been marked.
     */
    public ArrayList<Student> checkUnmarkedSubmissions() {
        ArrayList<Student> unmarked = new ArrayList<>();
        for (Student student: students) {
            if (!submissionTracker.get(student).isMarked()) {
                unmarked.add(student);
            }
        }
        return unmarked;
    }

    /**
     * Returns the number of students whose submissions have not been marked.
     */
    public int noOfUnmarkedSubmissions() {
        int unmarked = 0;
        for (Student student: students) {
            if (!submissionTracker.get(student).isMarked()) {
                unmarked++;
            }
        }
        return unmarked;
    }

    /**
     * Returns true if both assessments have the same description.
     */
    public boolean isSameAssessment(Assessment otherAssessment) {
        if (otherAssessment == this) {
            return true;
        }
        return otherAssessment.getDescription().equals(getDescription());
    }

    /**
     * Returns the average score scored by the class for this assessment.
     */
    public int averageScore() {
        int totalScore = 0;
        for (Student student: students) {
            totalScore += submissionTracker.get(student).getScore();
        }
        return totalScore / students.size();
    }

    /**
     * Returns the median score scored by the class for this assessment.
     */
    public int medianScore() {
        ArrayList<Integer> scores = new ArrayList<>();
        for (Student student: students) {
            scores.add(submissionTracker.get(student).getScore());
        }
        Collections.sort(scores);
        int median = (scores.size() % 2) == 0 ? scores.get(scores.size() / 2) : scores.get((scores.size() / 2) + 1);
        return median;
    }

    @Override
    public String toString() {
        return "Assessment: " + this.description + "\n";
    }
}
