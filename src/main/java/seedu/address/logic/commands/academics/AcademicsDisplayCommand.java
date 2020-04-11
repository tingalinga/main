package seedu.address.logic.commands.academics;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Displays an administrative version of the students list.
 */
public class AcademicsDisplayCommand extends AcademicsCommand {

    public static final String MESSAGE_HOMEWORK_SUCCESS = "Academics now displays all HOMEWORK assessments";
    public static final String MESSAGE_EXAM_SUCCESS = "Academics now displays all EXAM assessments";
    public static final String MESSAGE_REPORT_SUCCESS = "Academics now displays the report of each assessment.";

    public final String type;

    /**
     * Creates an AcademicsDisplayCommand.
     */
    public AcademicsDisplayCommand(String type) {
        this.type = type;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        switch (type) {
        case "":
            return new CommandResult(String.format(AcademicsCommand.MESSAGE_USAGE));
        case "homework":
            return new CommandResult(String.format(MESSAGE_HOMEWORK_SUCCESS));
        case "exam":
            return new CommandResult(String.format(MESSAGE_EXAM_SUCCESS));
        case "report":
            return new CommandResult(String.format(MESSAGE_REPORT_SUCCESS));
        default:
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AcademicsCommand.MESSAGE_USAGE));
        }
    }

    @Override
    public boolean equals(Object other) {
        return true;
    }
}
