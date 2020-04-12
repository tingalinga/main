package seedu.address.model.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.notes.TypicalNotes.NOTE1;
import static seedu.address.testutil.notes.TypicalNotes.NOTE2;
import static seedu.address.testutil.notes.TypicalNotes.NOTE3;
import static seedu.address.testutil.notes.TypicalNotes.NOTE4;
import static seedu.address.testutil.notes.TypicalNotes.NOTE5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.notes.exceptions.DuplicateNotesException;
import seedu.address.model.notes.exceptions.NotesUnavailableException;

public class UniqueNotesListTest {

    private final UniqueNotesList uniqueNotesList = new UniqueNotesList();

    @Test
    public void contains_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueNotesList.contains(null));
    }

    @Test
    public void contains_noteNotInList_returnsFalse() {
        assertFalse(uniqueNotesList.contains(NOTE1));
    }

    @Test
    public void contains_noteInList_returnsTrue() {
        uniqueNotesList.add(NOTE2);
        assertTrue(uniqueNotesList.contains(NOTE2));
    }

    @Test
    public void add_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueNotesList.add(null));
    }

    @Test
    public void add_duplicateNote_throwsDuplicateNoteException() {
        uniqueNotesList.add(NOTE3);
        assertThrows(DuplicateNotesException.class, () -> uniqueNotesList.add(NOTE3));
    }

    @Test
    public void setNote_nullTargetNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueNotesList.setNote(null, NOTE3));
    }

    @Test
    public void setNote_nullEditedNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueNotesList.setNote(NOTE3, null));
    }

    @Test
    public void setNote_targetNoteNotInList_throwsNoteUnavailableException() {
        assertThrows(NotesUnavailableException.class, () -> uniqueNotesList.setNote(NOTE4, NOTE1));
    }

    @Test
    public void setNote_editedNoteIsDuplicate_throwsDuplicateNotesException() {
        uniqueNotesList.add(NOTE4);
        assertThrows(DuplicateNotesException.class, () -> uniqueNotesList.setNote(NOTE4, NOTE4));
    }

    @Test
    public void setNote_editedNoteIsDifferentAndNotFoundInList_success() {
        uniqueNotesList.add(NOTE4);
        uniqueNotesList.setNote(NOTE4, NOTE5);
        UniqueNotesList anotherUniqueList = new UniqueNotesList();
        anotherUniqueList.add(NOTE5);
        assertEquals(uniqueNotesList, anotherUniqueList);
    }

    @Test
    public void remove_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueNotesList.remove(null));
    }

    @Test
    public void remove_noteDoesNotExist_throwsNoteUnavailableException() {
        assertThrows(NotesUnavailableException.class, () -> uniqueNotesList.remove(NOTE1));
    }

    @Test
    public void remove_existingNote_success() {
        uniqueNotesList.add(NOTE2);
        uniqueNotesList.remove(NOTE2);
        UniqueNotesList anotherUniqueList = new UniqueNotesList();
        assertEquals(uniqueNotesList, anotherUniqueList);
    }

    @Test
    public void setNotes_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueNotesList.setNotes((List<Notes>) null));
    }

    @Test
    public void setNotes_list_replacesOwnListWithProvidedList() {
        uniqueNotesList.add(NOTE3);
        List<Notes> noteList = Collections.singletonList(NOTE4);
        uniqueNotesList.setNotes(noteList);
        UniqueNotesList anotherUniqueList = new UniqueNotesList();
        anotherUniqueList.add(NOTE4);
        assertEquals(uniqueNotesList, anotherUniqueList);
    }

    @Test
    public void setNotes_listWithDuplicateDates_throwsDuplicateNotesException() {
        List<Notes> listWithDuplicateNotes = Arrays.asList(NOTE5, NOTE5);
        assertThrows(DuplicateNotesException.class, () -> uniqueNotesList.setNotes(listWithDuplicateNotes));
    }

    @Test
    public void asUnmodifiableObservaleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueNotesList.asUnmodifiableObservableList().remove(0));
    }


}
