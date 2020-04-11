package seedu.address.logic.commands.academics;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBMIT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSESSMENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.academics.Assessment;

/**
 * Adds an academic progress report to address book.
 */
public class AcademicsSubmitCommand extends AcademicsCommand {

    public static final String MESSAGE_USAGE = "This command submits students' work for the assessment identified. \n"
            + "Format: " + COMMAND_WORD + " "
            + PREFIX_SUBMIT + " ASSESSMENT INDEX (must be a positive integer) "
            + PREFIX_STUDENT + "STUDENT NAME ...\n"
            + "Example: academics submit 1 stu/Simon Lam\n"
            + "Example: academics submit 1 stu/Simon Lam stu/Gerren Seow\n";

    public static final String MESSAGE_SUCCESS = "Academics submitted following submissions:\n";

    private Index index;
    private List<String> students;

    public AcademicsSubmitCommand(Index index, List<String> students) throws CommandException {
        requireAllNonNull(index, students);
        this.index = index;
        this.students = students;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Assessment> lastShownAssessments = model.getFilteredAcademicsList();
        if (index.getZeroBased() >= lastShownAssessments.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSESSMENT_DISPLAYED_INDEX);
        }
        Assessment assessment = lastShownAssessments.get(index.getZeroBased());

        for (String stu : students) {
            if (!model.hasStudentName(stu)) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_SUBMISSION);
            }
            if (model.hasStudentSubmitted(assessment, stu)) {
                throw new CommandException(Messages.MESSAGE_STUDENT_HAS_ALREADY_SUBMITTED);
            }
        }

        model.submitAssessment(assessment, students);
        return new CommandResult(MESSAGE_SUCCESS + String.join("\n", students));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AcademicsSubmitCommand); // instanceof handles nulls
    }
}
