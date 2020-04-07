package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_NOTES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Refreshes the student card
 */
public class StudentRefreshCommand extends StudentCommand {

    public static final String MESSAGE_SUCCESS = "Refreshed students panel and updated all photos! \n "
            + "Please take note your file must be: \n 1. In png format \n 2. All lower case \n 3. No whitespace \n "
            + "For example, Student Name: Simon Lam, File Name: simonlam.png ";

    public StudentRefreshCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredNotesList(PREDICATE_SHOW_ALL_NOTES);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

}
