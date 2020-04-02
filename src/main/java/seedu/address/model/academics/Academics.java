package seedu.address.model.academics;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * Represents the save academic report of the class.
 */
public class Academics implements ReadOnlyAcademics {

    private final UniqueAssessmentList assessments;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        assessments = new UniqueAssessmentList();
    }

    public Academics() {}

    /**
     * Creates a list of SavedAssessments using the assessments in {@code toBeCopied}
     * @param toBeCopied list of assessments.
     */
    public Academics(ReadOnlyAcademics toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the academic assessments with {@Code Assessments}.
     * {@code Assessments} must not contain duplicate assessments.
     * @param assessments academic list of assessments.
     */
    public void setAssessments(List<Assessment> assessments) {
        this.assessments.setAssessments(assessments);
    }

    /**
     * Resets the existing data of this {@code Academics} with {@code newData}.
     */
    public void resetData(ReadOnlyAcademics newData) {
        requireNonNull(newData);

        setAssessments(newData.getAcademicsList());
    }

    /**
     * Returns true if an assessment with the same identity as {@code assessment} exists in the academics list.
     */
    public boolean hasAssessment(Assessment assessment) {
        requireNonNull(assessment);
        return assessments.contains(assessment);
    }

    /**
     * Adds an assessment to the list of current assessments.
     */
    public void addAssessment(Assessment toAdd) {
        assessments.add(toAdd);
    }

    /**
     * Replaces the given Assessment {@code target} in the list with {@code editedAssessment}. {@code
     * target} must exist in saved academics. The Assessment identity of {@code editedAssessment} must
     * not be the same as another existing Assessment in the saved academics.
     */
    public void setAssessment(Assessment target, Assessment editedAssessment) {
        requireNonNull(editedAssessment);

        assessments.setAssessment(target, editedAssessment);
    }

    /**
     * Removes {@code key} from this {@code Academics}.
     * {@code key} must exist in the assessment list.
     */
    public void removeAssessment(Assessment key) {
        assessments.remove(key);
    }

    /**
     * Submits students' submissions to the assessment in {@code Academics}.
     * {@code target} must exist in the assessment list.
     */
    public void submitAssessment(Assessment target, List<String> students) {
        requireNonNull(target);
        requireNonNull(students);
        assessments.submitAssessment(target, students);
    }

    //// util methods

    @Override
    public String toString() {
        return assessments.asUnmodifiableObservableList().size() + " students";
    }

    @Override
    public ObservableList<Assessment> getAcademicsList() {
        return assessments.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Assessment> getHomeworkList() {
        return assessments.getHomeworkList();
    }

    @Override
    public ObservableList<Assessment> getExamList() {
        return assessments.getExamList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Academics // instanceof handles nulls
                && assessments.equals(((Academics) other).assessments));
    }

    @Override
    public int hashCode() {
        return assessments.hashCode();
    }
}
