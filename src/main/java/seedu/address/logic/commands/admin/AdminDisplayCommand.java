package seedu.address.logic.commands.admin;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Displays the last updated administrative version of the students list.
 */
public class AdminDisplayCommand extends AdminCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + ADMIN_DISPLAY
            + ": to display the last updated class admin details.";
    public static final String MESSAGE_SUCCESS = "The Student list now displays ADMIN details";

    /**
     * Creates an AdminDisplayCommand.
     */
    public AdminDisplayCommand() {

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return true;
    }
}
