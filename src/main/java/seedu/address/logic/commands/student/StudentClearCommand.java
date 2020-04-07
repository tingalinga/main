package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.student.TeaPet;

/**
 * Clears the address book.
 */
public class StudentClearCommand extends StudentCommand {

    public static final String MESSAGE_SUCCESS = "Student list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTeaPet(new TeaPet());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
