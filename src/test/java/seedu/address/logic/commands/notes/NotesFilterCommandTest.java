package seedu.address.logic.commands.notes;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.academics.TypicalAssessments.getTypicalAcademics;
import static seedu.address.testutil.admin.TypicalDates.getTypicalAdmin;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotesManager;
import static seedu.address.testutil.student.TypicalStudents.getTypicalTeaPet;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.notes.NotesContainKeywordsPredicate;

public class NotesFilterCommandTest {

    private Model model = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
            getTypicalNotesManager(), getTypicalEventHistory(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
            getTypicalNotesManager(), getTypicalEventHistory(), new UserPrefs());


    @Test
    public void equals() {
        NotesContainKeywordsPredicate firstPredicate =
                new NotesContainKeywordsPredicate(Collections.singletonList("first"));
        NotesContainKeywordsPredicate secondPredicate =
                new NotesContainKeywordsPredicate(Collections.singletonList("second"));

        NotesFilterCommand filterFirstCommand = new NotesFilterCommand(firstPredicate);
        NotesFilterCommand filterSecondCommand = new NotesFilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        NotesFilterCommand filterFirstCommandCopy = new NotesFilterCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different note -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

}
