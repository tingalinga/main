package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.DateTimeException;
import java.time.LocalDate;

import seedu.address.logic.commands.admin.AdminCommand;
import seedu.address.logic.commands.admin.AdminDisplayCommand;
import seedu.address.logic.commands.admin.AdminFetchCommand;
import seedu.address.logic.commands.admin.AdminSaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input argument and creates a new AdminCommand object
 */
public class AdminCommandParser implements Parser<AdminCommand> {
    /**
     * Parses the given {@code String} of argument in the context of the AdminCommand
     * and returns an AdminCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AdminCommand parse(String arg) throws ParseException, DateTimeException {
        String[] inputs = arg.split(" ");
        switch (inputs[1]) {
        case AdminCommand.ADMIN_DISPLAY:
            if (inputs.length > 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AdminDisplayCommand.MESSAGE_USAGE));
            } else {
                return new AdminDisplayCommand();
            }

        case AdminCommand.ADMIN_FETCH:
            if (inputs.length > 3) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AdminFetchCommand.MESSAGE_USAGE));
            } else {
                return new AdminFetchCommand(LocalDate.parse(inputs[2]));
            }

        case AdminCommand.ADMIN_SAVE:
            if (inputs.length > 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AdminSaveCommand.MESSAGE_USAGE));
            } else {
                return new AdminSaveCommand();
            }

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AdminCommand.MESSAGE_USAGE));
        }
    }
}
