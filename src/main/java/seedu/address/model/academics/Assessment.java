package seedu.address.model.academics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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

    public Assessment(String description) {
        this.description = description;
        this.students = null;
    }

    public String getDescription() {
        return description;
    }

    public void setStudents(List<Student> students) {
        for (Student student: students) {
            submissionTracker.put(student, new Submission());
        }
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

    public void mark(Student student, int score) {
        submissionTracker.get(student).markAssessment(score);
    }

    public ArrayList<Student> checkUnsubmittedStudents() {
        ArrayList<Student> unsubmitted = new ArrayList<>();
        for (Student student: students) {
            if (!submissionTracker.get(student).hasSubmitted()) {
                unsubmitted.add(student);
            }
        }
        return unsubmitted;
    }

    public ArrayList<Student> checkUnmarkedSubmissions() {
        ArrayList<Student> unmarked = new ArrayList<>();
        for (Student student: students) {
            if (!submissionTracker.get(student).isMarked()) {
                unmarked.add(student);
            }
        }
        return unmarked;
    }

    public int noOfUnmarkedSubmissions() {
        int unmarked = 0;
        for (Student student: students) {
            if (!submissionTracker.get(student).isMarked()) {
                unmarked++;
            }
        }
        return unmarked;
    }

    public boolean isSameAssessment(Assessment otherAssessment) {
        if (otherAssessment == this) {
            return true;
        }

        return otherAssessment.getDescription().equals(getDescription());
    }
    
    public int averageScore() {
        int totalScore = 0;
        for (Student student: students) {
            totalScore += submissionTracker.get(student).getScore();
        }
        return totalScore / students.size();
    }

    public int medianScore() {
        ArrayList<Integer> scores = new ArrayList<>();
        for (Student student: students) {
            scores.add(submissionTracker.get(student).getScore());
        }
        Collections.sort(scores);
        int median = (scores.size() % 2) == 0 ? scores.get(scores.size() / 2) :  scores.get((scores.size() / 2) + 1);
        return median;
    }

    @Override
    public String toString() {
        return "Assessment: " + this.description + "\n";
    }

}