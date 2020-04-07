package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.getTypicalTeaPet;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.ReadOnlyTeaPet;
import seedu.address.model.student.Student;
import seedu.address.model.student.TeaPet;
import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.testutil.StudentBuilder;

public class TeaPetTest {

    private final TeaPet teaPet = new TeaPet();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), teaPet.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> teaPet.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTeaPet_replacesData() {
        TeaPet newData = getTypicalTeaPet();
        teaPet.resetData(newData);
        assertEquals(newData, teaPet);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        TeaPetStub newData = new TeaPetStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> teaPet.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> teaPet.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInTeaPet_returnsFalse() {
        assertFalse(teaPet.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInTeaPet_returnsTrue() {
        teaPet.addStudent(ALICE);
        assertTrue(teaPet.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInTeaPet_returnsTrue() {
        teaPet.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(teaPet.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> teaPet.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyTeaPet whose students list can violate interface constraints.
     */
    private static class TeaPetStub implements ReadOnlyTeaPet {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        TeaPetStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }
    }

}
