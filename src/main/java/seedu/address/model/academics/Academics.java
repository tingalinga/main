package seedu.address.model.academics;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.academics.exceptions.AssessmentNotFoundException;
import seedu.address.model.academics.exceptions.DuplicateAssessmentException;

public class Academics {
    private final ObservableList<Assessment> assessments = FXCollections.observableArrayList();
    private final ObservableList<Assessment> assessmentsUnmodifiableList =
            FXCollections.unmodifiableObservableList(assessments);

    public boolean contains(Assessment toCheck) {
        requireNonNull(toCheck);
        return assessments.stream().anyMatch(toCheck::isSameAssessment);
    }

    public void addAssessment(Assessment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAssessmentException();
        }
        this.assessments.add(toAdd);
    }

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

    // for future remove assessment command
    public void remove(Assessment toRemove) {
        requireNonNull(toRemove);
        if (!assessments.remove(toRemove)) {
            throw new AssessmentNotFoundException();
        }
    }

    public void setAssessments(Academics replacement) {
        requireAllNonNull(replacement);
        assessments.setAll(replacement.assessments);
    }

    public void setAssessments(List<Assessment> assessments) {
        requireAllNonNull(assessments);
        if (!assessmentsAreUnique(assessments)) {
            throw new DuplicateAssessmentException();
        }
        this.assessments.setAll(assessments);
    }

    public ObservableList<Assessment> getAllAssessments() {
        return assessments;
    }

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