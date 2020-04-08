package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.notes.Notes;
import seedu.address.model.notes.NotesManager;

/**
 * Represents Typical Notes used for testing.
 */
public class TypicalNotes {

    public static final Notes NOTE1 = new Notes("Simon Lam", "Good Student",
            "LOW", "29/03/2020 22:40");
    public static final Notes NOTE2 = new Notes("Gerren Seow", "Good Student",
            "LOW", "29/03/2020 22:40");
    public static final Notes NOTE3 = new Notes("Lee Hui Ting", "Bad Student",
            "LOW", "29/03/2020 22:40");
    public static final Notes NOTE4 = new Notes("Gary Syndromes", "Average Student",
            "LOW", "29/03/2020 22:40");
    public static final Notes NOTE5 = new Notes("Freddy Zhang", "Good Student",
            "LOW", "29/03/2020 22:40");


    public static List<Notes> getTypicalNotes() {
        return new ArrayList<>(Arrays.asList(NOTE1, NOTE2, NOTE3, NOTE4, NOTE5));
    }

    public static NotesManager getTypicalNotesManager() {
        NotesManager notesManager = new NotesManager();
        for (Notes note : getTypicalNotes()) {
            notesManager.addNote(note);
        }
        return notesManager;
    }
}
