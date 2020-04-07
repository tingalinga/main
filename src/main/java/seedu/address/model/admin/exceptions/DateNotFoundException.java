package seedu.address.model.admin.exceptions;

import seedu.address.logic.commands.admin.AdminFetchCommand;

/**
 * Signals that the operation is unable to find the specified date.
 */
public class DateNotFoundException extends RuntimeException {
    public DateNotFoundException() {
        super(AdminFetchCommand.MESSAGE_DATE_NOT_FOUND_ADMIN);
    }
}
