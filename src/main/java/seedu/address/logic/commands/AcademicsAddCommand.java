package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.academics.Assessment;
import seedu.address.model.academics.Exam;
import seedu.address.model.academics.Homework;
import seedu.address.model.student.Student;

public abstract class AcademicsAddCommand extends AcademicsCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new assessment"
            + "Parameters:\n"
            + "name/[DESCRIPTION]\n"
            + "type/[TYPE: homework/exam]\n"
            + "date/[DATE]\n"
            + "Example: academics name/CS2103T assignment 1 type/homework date/2020-03-04\n";

    public static final String MESSAGE_SUCCESS = "Added academics:\n%1$s";

    private final Assessment toAdd;

    public AcademicsAddCommand(Assessment assessment) {
        requireNonNull(assessment);
        toAdd = assessment;
    }

    public AcademicsAddCommand(String description, String type, String date, ObservableList<Student> students) {
        if (type.equals("homework")) {
            toAdd = new Homework(description, date, students);
        } else {
            toAdd = new Exam(description, date, students);
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

//        model.addStudent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AcademicsAddCommand // instanceof handles nulls
                && toAdd.equals(((AcademicsAddCommand) other).toAdd));
    }
}