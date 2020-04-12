package seedu.address.logic.commands.admin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.academics.TypicalAssessments.getTypicalAcademics;
import static seedu.address.testutil.admin.TypicalDates.getTypicalAdmin;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;
import static seedu.address.testutil.notes.TypicalNotes.getTypicalNotesManager;
import static seedu.address.testutil.student.TypicalStudents.getTypicalTeaPet;

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

public class AdminFetchCommandTest {

    private Model model = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
            getTypicalNotesManager(), getTypicalEventHistory(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
            getTypicalNotesManager(), getTypicalEventHistory(), new UserPrefs());

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
    public void execute_dateNotInAdmin_throwsDateNotFoundException() {
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

    @Test
    public void execute_dateInAdmin_success() {
        Date date = model.getFilteredDateList().get(0);
        String fullDate = date.getDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " "
                + date.getDate().getDayOfMonth() + " " + date.getDate().getYear();
        String expectedMessage = String.format(AdminFetchCommand.MESSAGE_SUCCESS, fullDate);
        DateContainsKeywordsPredicate predicate = new DateContainsKeywordsPredicate(LocalDate.parse("2020-02-26"));
        model.updateFilteredDateList(predicate);
        AdminFetchCommand command = new AdminFetchCommand(predicate);
        expectedModel.updateFilteredDateList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
