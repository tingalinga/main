package seedu.address.logic.commands.admin;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.admin.Date;
import seedu.address.model.admin.DateContainsKeywordsPredicate;
import seedu.address.model.student.Student;

/**
 * Deletes the administrative details of the students list on a specific date.
 */
public class AdminDeleteCommand extends AdminCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + ADMIN_DELETE
            + "YYYY-MM-DD: to delete the class admin details at the specified date.";
    public static final String MESSAGE_SUCCESS = "Admin list has been deleted for %1$s";
    private final DateContainsKeywordsPredicate predicate;

    public AdminDeleteCommand(DateContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredDateList(predicate);
        LocalDate date = model.getFilteredDateList().get(0).getDate();
        String fullDate = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " "
                + date.getDayOfMonth() + " " + date.getYear();
        return new CommandResult(String.format(MESSAGE_SUCCESS, fullDate);
    }

    @Override
    public boolean equals(Object other) {
        return true;
    }

    @Override
    public String toString() {
        String fullDate = thisDate.getDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " "
                + thisDate.getDate().getDayOfMonth() + " " + thisDate.getDate().getYear();
        return fullDate;
    }
}
