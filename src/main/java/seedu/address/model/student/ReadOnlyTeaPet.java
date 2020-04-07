package seedu.address.model.student;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;

/**
 * Unmodifiable view of a tea ept
 */
public interface ReadOnlyTeaPet {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

}
