package seedu.address.model.admin.exceptions;

import seedu.address.logic.commands.admin.AdminSaveCommand;

/**
 * Signals that the operation will result in duplicate Dates (Dates are considered duplicates if they
 * have the same date).
 */
public class DuplicateDateException extends RuntimeException {
    public DuplicateDateException() {
        super(AdminSaveCommand.MESSAGE_DUPLICATE_DATE_ADMIN);
    }
}
