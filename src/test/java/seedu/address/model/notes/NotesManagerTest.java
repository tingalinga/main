package seedu.address.model.notes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.model.notes.exceptions.DuplicateNotesException;
import seedu.address.testutil.NotesBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalNotes.NOTE1;
import static seedu.address.testutil.TypicalNotes.getTypicalNotesManager;

public class NotesManagerTest {

    private final NotesManager notesManager = new NotesManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), notesManager.getNotesList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> notesManager.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyNotes_replacesData() {
        NotesManager newData = getTypicalNotesManager();
        notesManager.resetData(newData);
        assertEquals(notesManager, newData);
    }

    @Test
    public void resetData_withDuplicateNotes_throwsDuplicateNotesException() {
        Notes temp = new NotesBuilder(NOTE1).build();
        List<Notes> newNotes = Arrays.asList(NOTE1, temp);
        NotesManagerTest.NotesManagerStub newData = new NotesManagerTest.NotesManagerStub(newNotes);
        assertThrows(DuplicateNotesException.class, () -> notesManager.resetData(newData));
    }

    @Test
    public void hasNote_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> notesManager.hasNote(null));
    }

    @Test
    public void hasNote_NoteNotInNotesManager_returnsFalse() {
        assertFalse(notesManager.hasNote(NOTE1));
    }

    @Test
    public void hasNoteInNotesManager_success() {
        notesManager.addNote(NOTE1);
        assertTrue(notesManager.hasNote(NOTE1));
    }

    @Test
    public void hasNote_sameNote_success() {
        notesManager.addNote(NOTE1);
        Notes other = new NotesBuilder(NOTE1).build();
        assertTrue(notesManager.hasNote(other));
    }

    @Test
    public void getNotesList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> notesManager.getNotesList().remove(0));
    }

    /**
     * A stub ReadOnlyNotes which notes list can violate interface constraints.
     */
    private static class NotesManagerStub implements ReadOnlyNotes {
        private final ObservableList<Notes> notes = FXCollections.observableArrayList();

        NotesManagerStub(Collection<Notes> notes) {
            this.notes.setAll(notes);
        }

        @Override
        public ObservableList<Notes> getNotesList() {
            return notes;
        }
    }


}
