package seedu.address.logic.commands.admin;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Displays the last updated administrative version of the students list.
 */
public class AdminDisplayCommand extends AdminCommand {

    public static final String MESSAGE_SUCCESS = "The Student list now displays last updated ADMIN details.\n"
                                                  + "[HELP ON ADMIN COMMANDS]\n"
                                                  + "1. Save date: admin save\n"
                                                  + "2. Show dates: admin dates\n"
                                                  + "3. Delete date: admin delete YYYY-MM-DD\n"
                                                  + "4. Fetch date: admin fetch YYYY-MM-DD";


    /**
     * Creates an AdminDisplayCommand.
     */
    public AdminDisplayCommand() {

    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) { //dummy method here
        return other == this // short circuit if same object
                || (other instanceof AdminDisplayCommand); // instanceof handles nulls
    }
}
