package seedu.address.logic.commands.studentdisplay;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Displays an administrative version of the students list.
 */
public class AdminStudentDisplayCommand extends Command {

    public static final String COMMAND_WORD = "admin";

    public static final String MESSAGE_SUCCESS = "The Student list now displays ADMIN details";

    /**
     * Creates an AdminStudentDisplayCommand.
     */
    public AdminStudentDisplayCommand() {

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
