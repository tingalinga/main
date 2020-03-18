package seedu.address.model.academics;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

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

    public SavedAcademics(ReadOnlyAcademics toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public Academics getAcademics() {
        return academics;
    }

    public void setAcademics(List<Assessment> academics) {
        this.academics.setAssessments(academics);
    }

    /**
     * Resets the academic report to default.
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
