package seedu.address.logic.commands.admin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssessments.getTypicalAcademics;
import static seedu.address.testutil.TypicalDates.getTypicalAdmin;
import static seedu.address.testutil.TypicalNotes.getTypicalNotesManager;
import static seedu.address.testutil.TypicalStudents.getTypicalTeaPet;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.admin.Date;
import seedu.address.model.admin.DateContainsKeywordsPredicate;
import seedu.address.model.admin.exceptions.DateNotFoundException;

public class AdminDeleteCommandTest {

    private Model model = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
            getTypicalNotesManager(), getTypicalEventHistory(), new UserPrefs());

    @Test
    public void execute_validDateUnfilteredList_success() {
        Date dateToDelete = model.getFilteredDateList().get(0);
        AdminDeleteCommand deleteCommand = new AdminDeleteCommand(
                new DateContainsKeywordsPredicate(LocalDate.parse("2020-01-26")));

        String fullDate = dateToDelete.getDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " "
                + dateToDelete.getDate().getDayOfMonth() + " " + dateToDelete.getDate().getYear();
        String expectedMessage = String.format(deleteCommand.MESSAGE_SUCCESS, fullDate);

        ModelManager expectedModel = new ModelManager(model.getTeaPet(), model.getAcademics(), model.getAdmin(),
                model.getNotesManager(), model.getEventHistory(), new UserPrefs());
        expectedModel.deleteDate(dateToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_dateNotInAdmin_throwsDateNotFoundException() {
        DateContainsKeywordsPredicate predicate = new DateContainsKeywordsPredicate(LocalDate.parse("1997-04-26"));
        boolean thrown = false;
        try {
            AdminDeleteCommand deleteCommand = new AdminDeleteCommand(predicate);
            deleteCommand.execute(model);
        } catch (DateNotFoundException dnfe) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void equals() {
        AdminDeleteCommand deleteFirstCommand = new AdminDeleteCommand(new DateContainsKeywordsPredicate(
                LocalDate.parse("2020-01-26")));
        AdminDeleteCommand deleteSecondCommand = new AdminDeleteCommand(new DateContainsKeywordsPredicate(
                LocalDate.parse("2020-02-12")));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        AdminDeleteCommand deleteFirstCommandCopy = new AdminDeleteCommand(new DateContainsKeywordsPredicate(
                LocalDate.parse("2020-01-26")));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
