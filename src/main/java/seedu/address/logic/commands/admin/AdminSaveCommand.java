package seedu.address.logic.commands.admin;

import static java.util.Objects.requireNonNull;

import java.time.format.TextStyle;

import java.util.Locale;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.admin.Date;

/**
 * Saves the current most updated administrative list as today's date.
 */
public class AdminSaveCommand extends AdminCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + ADMIN_SAVE
            + ": to save the most updated admin list as today's date.";
    public static final String MESSAGE_SUCCESS = "This admin list has been saved for ";
    private final Date thisDate;

    public AdminSaveCommand(Date date) {
        thisDate = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(String.format(MESSAGE_SUCCESS + this.toString()));
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
