package seedu.address.testutil.notes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.notes.NotesEditCommand.EditNotesDescriptor;

import seedu.address.model.notes.Notes;
import seedu.address.model.notes.NotesManager;
import seedu.address.testutil.notes.EditNotesDescriptorBuilder;

/**
 * Represents Typical Notes used for testing.
 */
public class TypicalNotes {

    /* This sections prepares data for testing of NotesEditDescriptor */
    public static final String VALID_STUDENT_KELVIN = "Kelvin Klein";
    public static final String VALID_CONTENT_KELVIN = "He is a good student";
    public static final String VALID_PRIORITY_KELVIN = "LOW";

    public static final String VALID_STUDENT_JANE = "Jane Klein";
    public static final String VALID_CONTENT_JANE = "She reported sick and left school";
    public static final String VALID_PRIORITY_JANE = "HIGH";

    public static final EditNotesDescriptor N_DESC_KELVIN;
    public static final EditNotesDescriptor N_DESC_JANE;

    static {
        N_DESC_KELVIN = new EditNotesDescriptorBuilder().withStudent(VALID_STUDENT_KELVIN)
                .withContent(VALID_CONTENT_KELVIN).withPriority(VALID_PRIORITY_KELVIN).build();
        N_DESC_JANE = new EditNotesDescriptorBuilder().withStudent(VALID_STUDENT_JANE)
                .withContent(VALID_CONTENT_JANE).withPriority(VALID_PRIORITY_JANE).build();
    }

    /* This section prepares data for testing of Notes Storage component */
    public static final Notes S_NOTE1 = new Notes("Kelvin Klein", "Good Student",
            "MEDIUM", "29/03/2020 22:40");
    public static final Notes S_NOTE2 = new Notes("Calvin Klein", "Good Student",
            "HIGH", "29/03/2020 22:40");

    /* This sections prepares data for testing of Notes */
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
