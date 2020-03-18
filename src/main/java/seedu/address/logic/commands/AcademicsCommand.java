package seedu.address.logic.commands;

/**
 * Adds an academic report to the address book.
 */
public abstract class AcademicsCommand extends Command {

    public static final String COMMAND_WORD = "academics";
    public static final String MESSAGE_USAGE = "Commands: \n" +
            COMMAND_WORD + " add - adds new assessment.";
}
