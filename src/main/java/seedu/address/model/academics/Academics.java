package seedu.address.model.academics;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.academics.exceptions.AssessmentNotFoundException;
import seedu.address.model.academics.exceptions.DuplicateAssessmentException;

/**
 * Represents the academic report of the class
 */
public class Academics {
    private final ObservableList<Assessment> assessments = FXCollections.observableArrayList();
    private final ObservableList<Assessment> assessmentsUnmodifiableList =
            FXCollections.unmodifiableObservableList(assessments);

    /**
     * Returns true if the provided assessment is a replica.
     */
    public boolean contains(Assessment toCheck) {
        requireNonNull(toCheck);
        return assessments.stream().anyMatch(toCheck::isSameAssessment);
    }

    /**
     * Adds an assessment to the list of current assessments.
     */
    public void addAssessment(Assessment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAssessmentException();
        }
        this.assessments.add(toAdd);
    }

    /**
     * Sets a current assessment with the updated one.
     */
    // for future edit assessment command
    public void setAssessment(Assessment target, Assessment editedAssessment) {
        requireAllNonNull(target, editedAssessment);

        int index = assessments.indexOf(target);
        if (index == -1) {
            throw new AssessmentNotFoundException();
        }
        if (!target.isSameAssessment(editedAssessment) && contains(editedAssessment)) {
            throw new DuplicateAssessmentException();
        }
        assessments.set(index, editedAssessment);
    }

    /**
     * Removes an assessment.
     */
    // for future remove assessment command
    public void remove(Assessment toRemove) {
        requireNonNull(toRemove);
        if (!assessments.remove(toRemove)) {
            throw new AssessmentNotFoundException();
        }
    }

    /**
     * Few in the functionality here!
     */
    public void setAssessments(Academics replacement) {
        requireAllNonNull(replacement);
        assessments.setAll(replacement.assessments);
    }

    /**
     * Few in the functionality here!
     */
    public void setAssessments(List<Assessment> assessments) {
        requireAllNonNull(assessments);
        if (!assessmentsAreUnique(assessments)) {
            throw new DuplicateAssessmentException();
        }
        this.assessments.setAll(assessments);
    }

    /**
     * Returns all the assessments.
     */
    public ObservableList<Assessment> getAllAssessments() {
        return assessments;
    }

    /**
     * Returns all the homework.
     */
    // for future filter assessment command
    public ObservableList<Assessment> getAllHomework() {
        ObservableList<Assessment> homeworkAssessments = FXCollections.observableArrayList();
        for (Assessment a : assessments) {
            if (a instanceof Homework) {
                homeworkAssessments.add(a);
            }
        }
        return homeworkAssessments;
    }

    /**
     * Returns all the exams.
     */
    // for future filter assessment command
    public ObservableList<Assessment> getAllExams() {
        ObservableList<Assessment> exams = FXCollections.observableArrayList();
        for (Assessment a : exams) {
            if (a instanceof Exam) {
                exams.add(a);
            }
        }
        return exams;
    }

    public ObservableList<Assessment> asUnmodifiableObservableList() {
        return assessmentsUnmodifiableList;
    }

    public Iterator<Assessment> iterator() {
        return assessments.iterator();
    }

    /**
     * Returns true if all the assessments are unique.
     */
    private boolean assessmentsAreUnique(List<Assessment> assessments) {
        for (int i = 0; i < assessments.size() - 1; i++) {
            for (int j = i + 1; j < assessments.size(); j++) {
                if (assessments.get(i).isSameAssessment(assessments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "You have " + assessments.size() + " assessments.\n";
    }

}
