package seedu.address.logic.commands.student;

import seedu.address.logic.commands.Command;

/**
 * Handles commands dealing with students in the tea pet.
 */
public abstract class StudentCommand extends Command {

    public static final String COMMAND_WORD = "student";
    public static final String MESSAGE_USAGE = "Commands: \n"
            + COMMAND_WORD + " add - adds new student.\n"
            + COMMAND_WORD + " edit - edits current student.\n"
            + COMMAND_WORD + " delete - deletes student.\n"
            + COMMAND_WORD + " find - finds student.\n"
            + COMMAND_WORD + " refresh - refreshes student view.\n"
            + "Type the following commands for more info!";
}
