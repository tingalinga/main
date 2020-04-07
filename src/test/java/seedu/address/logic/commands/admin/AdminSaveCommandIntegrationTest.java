package seedu.address.logic.commands.admin;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertRuntimeFailure;
import static seedu.address.testutil.TypicalAssessments.getTypicalAcademics;
import static seedu.address.testutil.TypicalDates.getTypicalAdmin;
import static seedu.address.testutil.TypicalNotes.getTypicalNotes;
import static seedu.address.testutil.TypicalStudents.getTypicalTeaPet;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;

import java.time.format.TextStyle;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.admin.Date;
import seedu.address.testutil.DateBuilder;

public class AdminSaveCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTeaPet(), getTypicalAcademics(), getTypicalAdmin(), getTypicalNotes(),
                getTypicalEventHistory(), new UserPrefs());
    }

    @Test
    public void execute_newDate_success() {

        Date validDate = new DateBuilder().build();

        Model expectedModel = new ModelManager(model.getTeaPet(), model.getAcademics(), model.getAdmin(),
                model.getNotesManager(), model.getEventHistory(), new UserPrefs());

        expectedModel.addDate(validDate);
        String fullDate = validDate.getDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " "
                + validDate.getDate().getDayOfMonth() + " " + validDate.getDate().getYear();

        assertCommandSuccess(new AdminSaveCommand(validDate.getDate()), model,
                String.format(AdminSaveCommand.MESSAGE_SUCCESS, fullDate), expectedModel);
    }

    @Test
    public void execute_duplicateDate_throwsCommandException() {
        Date dateInList = model.getAdmin().getDateList().get(0);
        assertRuntimeFailure(new AdminSaveCommand(dateInList.getDate()), model,
                AdminSaveCommand.MESSAGE_DUPLICATE_DATE_ADMIN);
    }
}
