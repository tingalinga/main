package seedu.address.logic.commands.academics;

import seedu.address.logic.commands.Command;

/**
 * Adds an academic report to the address book.
 */
public abstract class AcademicsCommand extends Command {

    public static final String COMMAND_WORD = "academics";

    public static final String MESSAGE_USAGE = "The Academics tracks all your assessments and student submissions." + '\n'
            + "[HELP ON ACADEMICS COMMANDS]\n"
            + "ADD ASSESSMENT: academics add desc/<Assessment Description> type/<Type> date/<Date>\n"
            + "EDIT ASSESSMENT: academics edit <Index> [desc/<Assessment Description>] [type/<Type>] [date/<Date>]\n"
            + "DELETE ASSESSMENT: academics delete <Index>\n"
            + "SUBMIT ASSESSMENT: academics submit <Index> [stu/<Student Name>]\n"
            + "MARK ASSESSMENT: academics mark <Index> [stu/<Student Name>-<Score>]\n"
            + "FILTER ASSESSMENT BY TYPE: academics <Type> (only Homework or Exam)\n"
            + "VIEW ACADEMIC REPORT: academics report\n"
            + "EXPORT ACADEMICS REPORT: academics export\n"
            + "Type the following commands for more info!\n";
}
