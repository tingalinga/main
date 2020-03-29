package seedu.address.logic.commands.admin;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FETCH;

import java.time.LocalDate;
import java.time.format.TextStyle;

import java.util.Locale;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Displays the administrative details of the students list on a specific date.
 */
public class AdminFetchCommand extends AdminCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_FETCH
            + " YYYY-MM-DD to display the class admin details.";
    public static final String MESSAGE_SUCCESS = "The Student list now displays the class admin list for ";
    private final LocalDate thisDate;

    public AdminFetchCommand(LocalDate date) {
        requireNonNull(date);
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
