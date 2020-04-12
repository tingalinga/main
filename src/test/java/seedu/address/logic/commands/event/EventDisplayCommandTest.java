package seedu.address.logic.commands.event;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.event.EventDisplayCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Test for EventDisplayCommand
 */
public class EventDisplayCommandTest {

    @Test
    public void execute_validCommand_success() {
        Model model = new ModelManager();
        assertCommandSuccess(new EventDisplayCommand(), model,
                new CommandResult(MESSAGE_SUCCESS), model);
    }

}