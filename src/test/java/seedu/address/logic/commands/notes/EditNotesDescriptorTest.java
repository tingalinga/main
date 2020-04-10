package seedu.address.logic.commands.notes;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalNotes.N_DESC_JANE;
import static seedu.address.testutil.TypicalNotes.N_DESC_KELVIN;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.notes.NotesEditCommand.EditNotesDescriptor;
import seedu.address.testutil.EditNotesDescriptorBuilder;

public class EditNotesDescriptorTest {

    @Test
    public void equals() {
        EditNotesDescriptor descriptorWithSameValues = new EditNotesDescriptor(N_DESC_KELVIN);

        // same values -> returns true
        assertTrue(N_DESC_KELVIN.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(N_DESC_KELVIN.equals(N_DESC_KELVIN));

        // null -> returns false
        assertFalse(N_DESC_KELVIN.equals(null));

        // different types -> returns false
        assertFalse(N_DESC_KELVIN.equals(9));

        // different values -> returns false
        assertFalse(N_DESC_KELVIN.equals(N_DESC_JANE));

        // different name -> returns false
        EditNotesDescriptor editedKelvinName = new EditNotesDescriptorBuilder(N_DESC_KELVIN).withStudent("KEV").build();
        assertFalse(N_DESC_KELVIN.equals(editedKelvinName));

        // different content -> returns false
        EditNotesDescriptor editedKelvinContent = new EditNotesDescriptorBuilder(N_DESC_KELVIN)
                .withContent("Fake Content").build();
        assertFalse(N_DESC_KELVIN.equals(editedKelvinContent));

        // different priority -> returns false
        EditNotesDescriptor editedKelvinPriority = new EditNotesDescriptorBuilder(N_DESC_KELVIN).withPriority("MEDIUM")
                .build();
        assertFalse(N_DESC_KELVIN.equals(editedKelvinPriority));

    }
}
