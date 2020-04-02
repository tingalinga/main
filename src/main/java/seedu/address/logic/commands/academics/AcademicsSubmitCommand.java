package seedu.address.logic.commands.academics;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.academics.Assessment;
import seedu.address.model.student.Student;

/**
 * Adds an academic progress report to address book.
 */
public class AcademicsSubmitCommand extends AcademicsCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": creates a new assessment\n"
            + "Parameters:\n"
            + "submit [ASSESSMENT_INDEX] stu/[STUDENT_EXAM]...\n"
            + "Example: academics submit 1 stu/Simon Lam\n"
            + "Example: academics submit 1 stu/Simon Lam stu/Gerren Seow\n";

    public static final String MESSAGE_SUCCESS = "Academics submitted following submissions:\n";

    private Index index;
    private List<String> students;

    public AcademicsSubmitCommand(Index index, List<String> students) throws CommandException {
        requireNonNull(index);
        requireNonNull(students);
        this.index = index;
        this.students = students;
    }

    /**
     * Formats list of students.
     */
    public String formatStudents() {
        String formatted = "";
        Iterator<String> stringIterator = students.iterator();
        while (stringIterator.hasNext()) {
            formatted += stringIterator.next() + "\n";
        }
        return formatted;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Assessment> lastShownAssessments = model.getFilteredAcademicsList();
        if (index.getZeroBased() >= lastShownAssessments.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSESSMENT_DISPLAYED_INDEX);
        }
        Assessment assessment = lastShownAssessments.get(index.getZeroBased());

        List<Student> lastShownStudents = model.getFilteredStudentList();
        for (String stu : students) {
            if (!model.hasStudent(stu)) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_SUBMISSION);
            }
        }

        model.submitAssessment(assessment, students);
        return new CommandResult(MESSAGE_SUCCESS + formatStudents());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AcademicsSubmitCommand); // instanceof handles nulls
    }
}