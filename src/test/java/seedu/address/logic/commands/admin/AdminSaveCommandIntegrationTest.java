package seedu.address.logic.commands.admin;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssessments.getTypicalAcademics;
import static seedu.address.testutil.TypicalDates.getTypicalAdmin;
import static seedu.address.testutil.TypicalNotes.getTypicalNotes;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.admin.Date;
import seedu.address.model.student.Student;
import seedu.address.testutil.DateBuilder;
import seedu.address.testutil.StudentBuilder;

public class AdminSaveCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalAcademics(), getTypicalAdmin(), getTypicalNotes(),
                new UserPrefs(), getTypicalEventHistory());
    }

    @Test
    public void execute_newDate_success() {
        Date validDate = new DateBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getAcademics(), model.getAdmin(),
                model.getNotesManager(), new UserPrefs(), getTypicalEventHistory());

        expectedModel.addDate(validDate);

        assertCommandSuccess(new AdminSaveCommand(validDate.getDate()), model,
                String.format(AdminSaveCommand.MESSAGE_SUCCESS, validDate), expectedModel);
    }

    @Test
    public void execute_duplicateDate_throwsCommandException() {
        Date dateInList = model.getAdmin().getDateList().get(0);
        assertCommandFailure(new AdminSaveCommand(dateInList.getDate()), model,
                AdminSaveCommand.MESSAGE_DUPLICATE_DATE_ADMIN);
    }
}
