package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.academics.Assessment;

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