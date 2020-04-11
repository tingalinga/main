package seedu.address.logic.commands.academics;

import seedu.address.logic.commands.Command;

/**
 * Adds an academic report to the address book.
 */
public abstract class AcademicsCommand extends Command {

    public static final String COMMAND_WORD = "academics";

    public static final String MESSAGE_USAGE = "The Academics tracks all your assessments and student submissions."
            + '\n' + "[HELP ON ACADEMICS COMMANDS]\n"
            + "1. Add assessment: academics add desc/ASSESSMENT_DESCRIPTION type/TYPE date/DATE\n"
            + "2. Edit assessment: academics edit INDEX [desc/ASSESSMENT_DESCRIPTION] [type/TYPE] [date/DATE]\n"
            + "3. Delete assessment: academics delete INDEX\n"
            + "4. Submit assessment: academics submit INDEX [stu/STUDENT_NAME]...\n"
            + "5. Mark assessment: academics mark INDEX [stu/STUDENT_NAME-SCORE]...\n"
            + "6. Filter assessment BY TYPE: academics ASSESSMENT_TYPE (only homework or exam)\n"
            + "7. View academics report: academics report\n"
            + "8. Export academics report: academics export\n"
            + "Type the following commands for more info!\n";
}
