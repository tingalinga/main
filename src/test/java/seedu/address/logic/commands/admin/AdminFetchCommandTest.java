package seedu.address.logic.commands.admin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAssessments.getTypicalAcademics;
import static seedu.address.testutil.TypicalDates.getTypicalAdmin;
import static seedu.address.testutil.TypicalNotes.getTypicalNotes;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.admin.DateContainsKeywordsPredicate;
import seedu.address.model.admin.exceptions.DateNotFoundException;

public class AdminFetchCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalAcademics(), getTypicalAdmin(),
            getTypicalNotes(), new UserPrefs(), getTypicalEventHistory());

    @Test
    public void equals() {
        DateContainsKeywordsPredicate firstPredicate =
                new DateContainsKeywordsPredicate(LocalDate.parse("2020-04-26"));
        DateContainsKeywordsPredicate secondPredicate =
                new DateContainsKeywordsPredicate(LocalDate.parse("1997-04-26"));

        AdminFetchCommand fetchFirstCommand = new AdminFetchCommand(firstPredicate);
        AdminFetchCommand fetchSecondCommand = new AdminFetchCommand(secondPredicate);

        // same object -> returns true
        assertTrue(fetchFirstCommand.equals(fetchFirstCommand));

        // same values -> returns true
        AdminFetchCommand fetchFirstCommandCopy = new AdminFetchCommand(firstPredicate);
        assertTrue(fetchFirstCommand.equals(fetchFirstCommandCopy));

        // different types -> returns false
        assertFalse(fetchFirstCommand.equals(1));

        // null -> returns false
        assertFalse(fetchFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(fetchFirstCommand.equals(fetchSecondCommand));
    }

    @Test
    public void execute_dateNotFound() {
        boolean thrown = false;
        try {
            DateContainsKeywordsPredicate predicate = new DateContainsKeywordsPredicate(LocalDate.parse("2020-04-26"));
            AdminFetchCommand command = new AdminFetchCommand(predicate);
            command.execute(model);
        } catch (DateNotFoundException snfe) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}
