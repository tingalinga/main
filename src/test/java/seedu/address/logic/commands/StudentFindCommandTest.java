package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.academics.TypicalAssessments.getTypicalAcademics;
import static seedu.address.testutil.admin.TypicalDates.getTypicalAdmin;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotesManager;
import static seedu.address.testutil.student.TypicalStudents.CARL;
import static seedu.address.testutil.student.TypicalStudents.ELLE;
import static seedu.address.testutil.student.TypicalStudents.FIONA;
import static seedu.address.testutil.student.TypicalStudents.getTypicalTeaPet;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.student.StudentFindCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.exceptions.StudentNotFoundException;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class StudentFindCommandTest {

    private Model model = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
            getTypicalNotesManager(), getTypicalEventHistory(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
            getTypicalNotesManager(), getTypicalEventHistory(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        StudentFindCommand findFirstCommand = new StudentFindCommand(firstPredicate);
        StudentFindCommand findSecondCommand = new StudentFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        StudentFindCommand findFirstCommandCopy = new StudentFindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noStudentFound() {
        boolean thrown = false;
        try {
            NameContainsKeywordsPredicate predicate = preparePredicate(" ");
            StudentFindCommand command = new StudentFindCommand(predicate);
            command.execute(model);
        } catch (StudentNotFoundException snfe) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void execute_multipleKeywords_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        StudentFindCommand command = new StudentFindCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
