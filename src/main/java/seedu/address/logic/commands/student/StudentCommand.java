package seedu.address.logic.commands.student;

import seedu.address.logic.commands.Command;

/**
 * Handles commands dealing with students in the tea pet.
 */
public abstract class StudentCommand extends Command {

    public static final String COMMAND_WORD = "student";
    public static final String MESSAGE_USAGE = "TeaPet stores all your students' details and important information."
            + '\n' + "[HELP ON STUDENT COMMANDS]\n"
            + "1. " + COMMAND_WORD + " add - adds new student.\n"
            + "2. " + COMMAND_WORD + " edit - edits current student.\n"
            + "3. " + COMMAND_WORD + " delete - deletes student.\n"
            + "4. " + COMMAND_WORD + " find - finds student.\n"
            + "Type the following commands for more info!";
}
