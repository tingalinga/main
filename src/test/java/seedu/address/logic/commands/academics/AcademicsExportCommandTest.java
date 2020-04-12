package seedu.address.logic.commands.academics;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
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

}
