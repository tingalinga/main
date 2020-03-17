package seedu.address.model.academics;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAcademics {

    /**
     * Returns an unmodifiable view of the assessment list.
     * This list will not contain any duplicate assessment.
     */
    ObservableList<Assessment> getAcademicsList();

}