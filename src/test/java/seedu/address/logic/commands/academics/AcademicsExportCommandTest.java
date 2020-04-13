package seedu.address.logic.commands.academics;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Test for AcademicsExportCommand
 */
public class AcademicsExportCommandTest {

    @Test
    public void execute_validCommand_success() {
        Model model = new ModelManager();
        assertCommandSuccess(new AcademicsExportCommand(), model,
                new CommandResult(AcademicsExportCommand.MESSAGE_SUCCESS), model);
    }

    @Test
    public void equals() throws CommandException {
        AcademicsExportCommand exportFirstCommand = new AcademicsExportCommand();
        AcademicsExportCommand exportSecondCommand = new AcademicsExportCommand();

        // same object -> returns true
        assertTrue(exportFirstCommand.equals(exportFirstCommand));

        // same values -> returns true
        AcademicsExportCommand exportFirstCommandCopy = new AcademicsExportCommand();
        assertTrue(exportFirstCommand.equals(exportFirstCommandCopy));

        // different types -> returns false
        assertFalse(exportFirstCommand.equals(1));

        // null -> returns false
        assertFalse(exportFirstCommand.equals(null));

        // null -> returns false
        assertFalse(exportFirstCommand.equals(new AcademicsDisplayCommand("")));

        // different export command -> returns true
        assertTrue(exportFirstCommand.equals(exportSecondCommand));
    }

}
