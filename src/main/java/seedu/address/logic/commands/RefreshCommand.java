package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import seedu.address.model.Model;
import seedu.address.ui.StudentCard;

/**
 * Lists all students in the address book to the user.
 */
public class RefreshCommand extends Command {

    public static final String COMMAND_WORD = "refresh";

    public static final String MESSAGE_SUCCESS = "Refreshed students panel and updated all photos! \n " +
            "Please take note your file must be: \n 1. In png format \n 2. All lower case \n 3. No whitespace \n " +
            "For example, Student Name: Simon Lam, File Name: simonlam.png ";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
