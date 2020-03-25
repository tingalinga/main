package seedu.address.model.academics;

import java.util.ArrayList;
import java.util.Collections;
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

    // Tracking submissions
    private ObservableList<Student> students;
    private List<Submission> submissionTracker = new ArrayList<>();

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
     * Returns the submission tracker of the assessment.
     * @return submission tracker of assessment.
     */
    public List<Submission> getSubmissionTracker() {
        return submissionTracker;
    }

    /**
     * Set the submission of each student to not submitted and unmarked.
     * @param students list of students assigned with the assessment.
     */
    public void setStudents(List<Student> students) {
        for (Student student: students) {
            submissionTracker.add(new Submission(student.getName().fullName));
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
     * @param studentName student submitting his/her assessment.
     */
    public void setSingleSubmitted(String studentName) {
        for (Submission submission: submissionTracker) {
            if (submission.getStudentName().equals(studentName)) {
                submission.markAsSubmitted();
            }
        }
    }

    /**
     * Submits multiple students' assessments.
     * @param studentList list of students who have completed their assessment.
     */
    public void setMultipleSubmitted(String ...studentList) {
        for (String studentName: studentList) {
            for (Submission submission: submissionTracker) {
                if (submission.getStudentName().equals(studentName)) {
                    submission.markAsSubmitted();
                }
            }
        }
    }

    /**
     * Marks student's submission and assigns a score to the student's submission.
     * @param student student submitting his/her assessment.
     * @param score score given to the student's submission.
     */
    public void mark(String student, int score) {
        for (Submission submission: submissionTracker) {
            if (submission.getStudentName().equals(student)) {
                submission.markAssessment(score);
            }
        }
    }

    /**
     * Returns a list of students who have yet to submit their assessment.
     */
    public ArrayList<String> checkUnsubmittedStudents() {
        ArrayList<String> unsubmitted = new ArrayList<>();
        for (Submission submission: submissionTracker) {
            if (!submission.hasSubmitted()) {
                unsubmitted.add(submission.getStudentName());
            }
        }
        return unsubmitted;
    }

    /**
     * Returns the number of students who have yet to submit their assessment.
     */
    public int noOfUnsubmittedStudents() {
        int unsubmitted = 0;
        for (Submission submission: submissionTracker) {
            if (!submission.hasSubmitted()) {
                unsubmitted++;
            }
        }
        return unsubmitted;
    }

    /**
     * Returns the number of students who have submitted their assessment.
     */
    public int noOfSubmittedStudents() {
        int submitted = 0;
        for (Submission submission: submissionTracker) {
            if (submission.hasSubmitted()) {
                submitted++;
            }
        }
        return submitted;
    }

    /**
     * Returns a list of students whose submissions have not been marked.
     */
    public ArrayList<String> checkUnmarkedSubmissions() {
        ArrayList<String> unmarked = new ArrayList<>();
        for (Submission submission: submissionTracker) {
            if (!submission.isMarked()) {
                unmarked.add(submission.getStudentName());
            }
        }
        return unmarked;
    }

    /**
     * Returns the number of students whose submissions have not been marked.
     */
    public int noOfUnmarkedSubmissions() {
        int unmarked = 0;
        for (Submission submission: submissionTracker) {
            if (!submission.isMarked()) {
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
        for (Submission submission: submissionTracker) {
            totalScore += submission.getScore();
        }
        return totalScore / students.size();
    }

    /**
     * Returns the median score scored by the class for this assessment.
     */
    public int medianScore() {
        ArrayList<Integer> scores = new ArrayList<>();
        for (Submission submission: submissionTracker) {
            scores.add(submission.getScore());
        }
        Collections.sort(scores);
        int median = (scores.size() % 2) == 0 ? scores.get(scores.size() / 2) : scores.get((scores.size() / 2) + 1);
        return median;
    }

    @Override
    public String toString() {
        return "Assessment: " + this.description + "\n"
                + "Submitted: " + noOfSubmittedStudents()
                + "Unsubmitted: " + noOfUnsubmittedStudents();
    }
}
