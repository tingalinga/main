package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import seedu.address.logic.commands.admin.AdminCommand;
import seedu.address.logic.commands.admin.AdminDeleteCommand;
import seedu.address.logic.commands.admin.AdminDisplayCommand;
import seedu.address.logic.commands.admin.AdminFetchCommand;
import seedu.address.logic.commands.admin.AdminSaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.admin.DateContainsKeywordsPredicate;

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
    public AdminCommand parse(String arg) throws ParseException, DateTimeParseException {
        arg.trim();
        String[] inputs = arg.split(" ");
        if (inputs.length < 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AdminCommand.MESSAGE_USAGE));
        }
        switch (inputs[1]) {
        case AdminCommand.ADMIN_DISPLAY:
            if (inputs.length > 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AdminDisplayCommand.MESSAGE_USAGE));
            } else {
                return new AdminDisplayCommand();
            }

        case AdminCommand.ADMIN_FETCH:
            if (inputs.length > 3 || inputs.length == 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AdminFetchCommand.MESSAGE_USAGE));
            } else {
                return new AdminFetchCommand(new DateContainsKeywordsPredicate(LocalDate.parse(inputs[2])));
            }

        case AdminCommand.ADMIN_SAVE:
            if (inputs.length > 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AdminSaveCommand.MESSAGE_USAGE));
            } else {
                return new AdminSaveCommand(LocalDate.now());
            }

        case AdminCommand.ADMIN_DELETE:
            if (inputs.length > 3 || inputs.length == 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AdminDeleteCommand.MESSAGE_USAGE));
            } else {
                return new AdminDeleteCommand(new DateContainsKeywordsPredicate(LocalDate.parse(inputs[2])));
            }

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AdminCommand.MESSAGE_USAGE));
        }
    }
}
