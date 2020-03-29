package seedu.address.logic.commands.admin;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Displays the last updated administrative version of the students list.
 */
public class AdminCommand extends Command {

    public static final String COMMAND_WORD = "admin";

    public static final String MESSAGE_SUCCESS = "The Student list now displays the last updated ADMIN details";

    /**
     * Creates an AdminCommand.
     */
    public AdminCommand() {

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
