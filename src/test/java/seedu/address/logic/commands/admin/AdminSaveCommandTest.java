package seedu.address.logic.commands.admin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertRuntimeFailure;
import static seedu.address.testutil.TypicalAssessments.getTypicalAcademics;
import static seedu.address.testutil.TypicalDates.getTypicalAdmin;
import static seedu.address.testutil.TypicalNotes.getTypicalNotesManager;
import static seedu.address.testutil.TypicalStudents.getTypicalTeaPet;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.admin.Date;
import seedu.address.testutil.DateBuilder;

public class AdminSaveCommandTest {

    private Model model = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(),
            getTypicalNotesManager(), getTypicalEventHistory(), new UserPrefs());

    @Test
    public void equals() {
        Date jan262020 = new DateBuilder().withDate(LocalDate.parse("2020-01-26")).build();
        Date feb262020 = new DateBuilder().withDate(LocalDate.parse("2020-02-26")).build();
        AdminSaveCommand addJan262020 = new AdminSaveCommand(jan262020.getDate());
        AdminSaveCommand addFeb262020 = new AdminSaveCommand(feb262020.getDate());

        // same object -> returns true
        assertTrue(jan262020.equals(jan262020));

        // same values -> returns true
        AdminSaveCommand adminSaveCommandJan262020Copy = new AdminSaveCommand(jan262020.getDate());
        assertTrue(adminSaveCommandJan262020Copy.equals(addJan262020));

        // different types -> returns false
        assertFalse(jan262020.equals(1));

        // null -> returns false
        assertFalse(jan262020.equals(null));

        // different student -> returns false
        assertFalse(jan262020.equals(feb262020));
    }

    @Test
    public void execute_invalidDateProvided() {
        boolean thrown = false;
        try {
            AdminSaveCommand adminSaveCommand = new AdminSaveCommand(LocalDate.parse("2020-24-12"));
        } catch (DateTimeException dte) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void execute_multipleDatesFound() {
        Date dateInList = model.getAdmin().getDateList().get(0);
        assertRuntimeFailure(new AdminSaveCommand(dateInList.getDate()), model,
                AdminSaveCommand.MESSAGE_DUPLICATE_DATE_ADMIN);
    }
}
