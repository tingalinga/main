package seedu.address.model.academics;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.academics.exceptions.DuplicateAssessmentException;

/**
 * Represents the save academic report of the class.
 */
public class SavedAcademics implements ReadOnlyAcademics {

    private final Academics academics;

    {
        academics = new Academics();
    }

    public SavedAcademics() {
    }

    /**
     * Creates a list of SavedAssessments using the assessments in {@code toBeCopied}
     * @param toBeCopied list of assessments.
     */
    public SavedAcademics(ReadOnlyAcademics toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Returns the academic tracker for assessments.
     * @return academic tracker for assessments.
     */
    public Academics getAcademics() {
        return academics;
    }

    /**
     * Replaces the contents of the academic assessments with {@Code Assessments}.
     * {@code Assessments} must not contain duplicate assessments.
     * @param academics academic list of assessments.
     */
    public void setAcademics(List<Assessment> academics) {
        this.academics.setAssessments(academics);
    }

    /**
     * Returns true if an assessment with the same identity as {@code assessment} exists in the academics list.
     */
    public boolean hasAssessment(Assessment assessment) {
        requireNonNull(assessment);
        return academics.hasAssessment(assessment);
    }

    /**
     * Returns the assessment object.
     *
     * @param index of the assessment in the list.
     * @return Assessment object.
     */
    public Assessment getAssessment(Index index) {
        return academics.getAssessment(index);
    }

    /**
     * Adds an assessment to the list of current assessments.
     */
    public void addAssessment(Assessment toAdd) {
        academics.addAssessment(toAdd);
    }

    /**
     * Removes {@code key} from this {@code SavedAcademics}. {@code key} must exist in saved
     * academics.
     */
    public Assessment deleteAssessment(Index index) {
        return academics.deleteAssessment(index);
    }

    /**
     * Replaces the given Assessment {@code target} in the list with {@code editedAssessment}. {@code
     * target} must exist in saved academics. The Assessment identity of {@code editedAssessment} must
     * not be the same as another existing Assessment in the saved academics.
     */
    public void setAssessment(Index index, Assessment editedAssessment) {
        requireNonNull(editedAssessment);

        academics.setAssessment(index, editedAssessment);
    }

    /**
     * Resets the existing data of this {@code SavedAcademics} with {@code newData}.
     */
    public void resetData(ReadOnlyAcademics newData) {
        requireNonNull(newData);

        setAcademics(newData.getAcademicsList());
    }

    public ObservableList<Assessment> getAllAssessments() {
        return academics.getAllAssessments();
    }

    public ObservableList<Assessment> getAllHomework() {
        return academics.getAllHomework();
    }

    public ObservableList<Assessment> getAllExams() {
        return academics.getAllExams();
    }

    @Override
    public ObservableList<Assessment> getAcademicsList() {
        return null;
    }

    //// util methods

    @Override
    public String toString() {
        return academics.asUnmodifiableObservableList().size() + " Assessments";
    }

    public ObservableList<Assessment> getSavedAcademics() {
        return academics.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SavedAcademics // instanceof handles nulls
                && academics.equals(((SavedAcademics) other).academics));
    }

    @Override
    public int hashCode() {
        return academics.hashCode();
    }
}
