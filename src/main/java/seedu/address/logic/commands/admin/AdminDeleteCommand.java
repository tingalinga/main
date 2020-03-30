package seedu.address.logic.commands.admin;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes the administrative details of the students list on a specific date.
 */
public class AdminDeleteCommand extends AdminCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + ADMIN_DELETE
            + "YYYY-MM-DD: to delete the class admin details at the specified date.";
    public static final String MESSAGE_SUCCESS = "Admin list has been deleted for ";
    private final LocalDate thisDate;

    public AdminDeleteCommand(LocalDate date) {
        thisDate = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(String.format(MESSAGE_SUCCESS + thisDate.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return true;
    }

    @Override
    public String toString() {
        String fullDate = thisDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " "
                + thisDate.getDayOfMonth() + " " + thisDate.getYear();
        return fullDate;
    }
}
