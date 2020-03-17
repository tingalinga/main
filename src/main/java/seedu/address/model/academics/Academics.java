package seedu.address.model.academics;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;

public class Academics {
    private final ObservableList<Assessment> assessments = FXCollections.observableArrayList();
    private final ObservableList<Assessment> assessmentsFiltered = FXCollections.observableArrayList();
    private final ObservableList<Assessment> assessmentsUnmodifiableList =
            FXCollections.unmodifiableObservableList(assessments);

    public void setAssessments(List<Assessment> assessments) {
        requireAllNonNull(assessments);
        if (isUniqueList(assessments)) {

        }
        this.assessments.addAll(assessments);
    }

    public void addAssessment(Assessment assessment) {
        requireNonNull(assessment);
        this.assessments.add(assessment);
    }

    public Assessment deleteAssessment(Index index) {
        return assessments.remove(index.getZeroBased());
    }

    public Assessment getAssessment(Index index) {
        return assessments.get(index.getZeroBased());

    }

    public void setAssessment(Index index, Assessment assessment) {
        assessments.set(index.getZeroBased(), assessment);
    }

    public void setQuestion(Assessment target, Assessment editedAssessment) {
        requireAllNonNull(target, editedAssessment);

        int index = assessments.indexOf(target);
        assessments.set(index, editedAssessment);
    }

    public ObservableList<Assessment> getAllAssessments() {
        return assessments;
    }

    public ObservableList<Assessment> getAllHomework() {
        ObservableList<Assessment> homeworkAssessments = FXCollections.observableArrayList();
        for (Assessment a : assessments) {
            if (a instanceof Homework) {
                homeworkAssessments.add(a);
            }
        }
        return homeworkAssessments;
    }

    public ObservableList<Assessment> getAllExams() {
        ObservableList<Assessment> exams = FXCollections.observableArrayList();
        for (Assessment a : exams) {
            if (a instanceof Exam) {
                exams.add(a);
            }
        }
        return exams;
    }

    public boolean isUniqueList(List<Assessment> assessments) {
        for (Assessment assessment : assessments) {
            for (Assessment otherAssessment : assessments) {
                if (assessment.isSameAssessment(otherAssessment)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean contains(Assessment toCheck) {
        requireNonNull(toCheck);
        return assessments.stream().anyMatch((assessment) -> assessment.equals(toCheck));
    }

    public ObservableList<Assessment> asUnmodifiableObservableList() {
        return assessmentsUnmodifiableList;
    }

    public Iterator<Assessment> iterator() {
        return assessments.iterator();
    }

    @Override
    public String toString() {
        return "You have " + assessments.size() + " assessments.\n";
    }

}