package seedu.address.logic.commands.academics;

import seedu.address.logic.commands.Command;

/**
 * Adds an academic report to the address book.
 */
public abstract class AcademicsCommand extends Command {

    public static final String COMMAND_WORD = "academics";

    public static final String MESSAGE_USAGE = "The Academics tracks all your assessments and student submissions."
            + '\n' + "[HELP ON ACADEMICS COMMANDS]\n"
            + "add assessment: academics add desc/ASSESSMENT_DESCRIPTION type/TYPE date/DATE\n"
            + "edit assessment: academics edit INDEX [desc/ASSESSMENT_DESCRIPTION] [type/TYPE] [date/DATE]\n"
            + "delete assessment: academics delete INDEX\n"
            + "submit assessment: academics submit INDEX [stu/STUDENT_NAME]...\n"
            + "mark assessment: academics mark INDEX> [stu/STUDENT_NAME-SCORE]...\n"
            + "filter assessment BY TYPE: academics ASSESSMENT_TYPE (only Homework or Exam)\n"
            + "view academics report: academics report\n"
            + "export academics report: academics export\n"
            + "Type the following commands for more info!\n";
}
