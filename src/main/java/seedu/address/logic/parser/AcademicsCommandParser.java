package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBMIT;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.academics.AcademicsAddCommand;
import seedu.address.logic.commands.academics.AcademicsCommand;
import seedu.address.logic.commands.academics.AcademicsDeleteCommand;
import seedu.address.logic.commands.academics.AcademicsDisplayCommand;
import seedu.address.logic.commands.academics.AcademicsDisplayExamCommand;
import seedu.address.logic.commands.academics.AcademicsDisplayHomeworkCommand;
import seedu.address.logic.commands.academics.AcademicsDisplayReportCommand;
import seedu.address.logic.commands.academics.AcademicsEditCommand;
import seedu.address.logic.commands.academics.AcademicsMarkCommand;
import seedu.address.logic.commands.academics.AcademicsSubmitCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the given {@code String} of arguments in the context of the AcademicsCommand
 * and returns an AcademicsCommand object for execution.
 *
 * @throws ParseException if the user input does not conform the expected format
 */
public class AcademicsCommandParser implements Parser<AcademicsCommand> {

    public static final String HELP_MESSAGE = "Academics command has to include an action.\n"
            + AcademicsCommand.MESSAGE_USAGE;

    @Override
    public AcademicsCommand parse(String args) throws ParseException, CommandException {
        requireNonNull(args);

        if (args.equals("")) {
            return academicsDisplayCommand();
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ADD, PREFIX_DELETE, PREFIX_EDIT, PREFIX_HOMEWORK, PREFIX_EXAM,
                        PREFIX_REPORT, PREFIX_SUBMIT, PREFIX_MARK, PREFIX_STUDENT, PREFIX_ASSESSMENT_DESCRIPTION,
                        PREFIX_ASSESSMENT_TYPE, PREFIX_ASSESSMENT_DATE);

        if (argMultimap.getValue(PREFIX_ADD).isPresent()) {
            return addCommand(argMultimap);
        } else if (argMultimap.getValue(PREFIX_DELETE).isPresent()) {
            return deleteCommand(argMultimap);
        } else if (argMultimap.getValue(PREFIX_EDIT).isPresent()) {
            return editCommand(argMultimap);
        } else if (argMultimap.getValue(PREFIX_SUBMIT).isPresent()) {
            return submitCommand(argMultimap);
        } else if (argMultimap.getValue(PREFIX_MARK).isPresent()) {
            return markCommand(argMultimap);
        } else if (argMultimap.getValue(PREFIX_HOMEWORK).isPresent()) {
            return academicsDisplayHomeworkCommand();
        } else if (argMultimap.getValue(PREFIX_EXAM).isPresent()) {
            return academicsDisplayExamCommand();
        } else if (argMultimap.getValue(PREFIX_REPORT).isPresent()) {
            return academicsDisplayReportCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HELP_MESSAGE));
        }
    }

    /**
     * Checks the format of the date string given.
     */
    private void checkValidDate(String date) throws ParseException {
        String[] split = date.split("-");
        if (split.length < 3 || split[0].length() < 4 || split[1].length() < 2 || split[2].length() < 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_DATE_FORMAT, HELP_MESSAGE));
        }
        if (Integer.parseInt(split[1]) == 1 || Integer.parseInt(split[1]) ==  3
                || Integer.parseInt(split[1]) ==  5 || Integer.parseInt(split[1]) == 7
                || Integer.parseInt(split[1]) == 8 || Integer.parseInt(split[1]) == 10
                || Integer.parseInt(split[1]) == 12) {
            if (Integer.parseInt(split[2]) < 0 || Integer.parseInt(split[2]) > 31) {
                throw new ParseException(String.format(MESSAGE_INVALID_DATE_FORMAT, HELP_MESSAGE));
            }
        }
        if (Integer.parseInt(split[1]) == 4 || Integer.parseInt(split[1]) ==  6
                || Integer.parseInt(split[1]) ==  9 || Integer.parseInt(split[1]) == 11) {
            if (Integer.parseInt(split[2]) < 0 || Integer.parseInt(split[2]) > 30) {
                throw new ParseException(String.format(MESSAGE_INVALID_DATE_FORMAT, HELP_MESSAGE));
            }
        }
        if (Integer.parseInt(split[1]) == 2) {
            if (Integer.parseInt(split[2]) < 0 || Integer.parseInt(split[2]) > 28) {
                throw new ParseException(String.format(MESSAGE_INVALID_DATE_FORMAT, HELP_MESSAGE));
            }
        }
    }

    /**
     * Returns a AcademicsAddCommand object for execution.
     * {@code ArgumentMultimap}.
     */
    private AcademicsAddCommand addCommand(ArgumentMultimap argMultimap) throws ParseException, CommandException {
        if (!arePrefixesPresent(argMultimap, PREFIX_ASSESSMENT_DESCRIPTION, PREFIX_ASSESSMENT_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AcademicsAddCommand.MESSAGE_USAGE));
        }

        String description = argMultimap.getValue(PREFIX_ASSESSMENT_DESCRIPTION).get();
        String type = argMultimap.getValue(PREFIX_ASSESSMENT_TYPE).get();
        String date = argMultimap.getValue(PREFIX_ASSESSMENT_DATE).get();
        checkValidDate(date);

        return new AcademicsAddCommand(description, type, date);
    }

    /**
     * Returns a AcademicsDeleteCommand object for execution.
     * {@code ArgumentMultimap}.
     */
    private AcademicsDeleteCommand deleteCommand(ArgumentMultimap argMultimap) throws ParseException, CommandException {
        try {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble(PREFIX_DELETE.getPrefix()));
            return new AcademicsDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AcademicsDeleteCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns a AcademicsEditCommand object for execution.
     * {@code ArgumentMultimap}.
     */
    private AcademicsEditCommand editCommand(ArgumentMultimap argMultimap) throws ParseException, CommandException {
        requireNonNull(argMultimap);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble("edit"));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AcademicsEditCommand.MESSAGE_USAGE), pe);
        }

        AcademicsEditCommand.EditAssessmentDescriptor editAssessmentDescriptor =
                new AcademicsEditCommand.EditAssessmentDescriptor();
        if (argMultimap.getValueOptional(PREFIX_ASSESSMENT_DESCRIPTION).isPresent()) {
            editAssessmentDescriptor.setDescription(argMultimap.getValue(PREFIX_ASSESSMENT_DESCRIPTION).get());
        }
        if (argMultimap.getValueOptional(PREFIX_ASSESSMENT_TYPE).isPresent()) {
            editAssessmentDescriptor.setType(argMultimap.getValue(PREFIX_ASSESSMENT_TYPE).get());
        }
        if (argMultimap.getValueOptional(PREFIX_ASSESSMENT_DATE).isPresent()) {
            checkValidDate(argMultimap.getValue(PREFIX_ASSESSMENT_DATE).get());
            editAssessmentDescriptor.setDate(argMultimap.getValue(PREFIX_ASSESSMENT_DATE).get());
        }

        if (!editAssessmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(AcademicsEditCommand.MESSAGE_NOT_EDITED);
        }

        return new AcademicsEditCommand(index, editAssessmentDescriptor);
    }

    /**
     * Returns a AcademicsSubmitCommand object for execution.
     * {@code ArgumentMultimap}.
     */
    private AcademicsSubmitCommand submitCommand(ArgumentMultimap argMultimap) throws ParseException, CommandException {
        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT)
                || argMultimap.getPreamble(PREFIX_SUBMIT.getPrefix()).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AcademicsSubmitCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble(PREFIX_SUBMIT.getPrefix()));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AcademicsSubmitCommand.MESSAGE_USAGE), pe);
        }
        List<String> students = argMultimap.getAllValues(PREFIX_STUDENT);

        return new AcademicsSubmitCommand(index, students);
    }

    /**
     * Returns a AcademicsMarkCommand object for execution.
     * {@code ArgumentMultimap}.
     */
    private AcademicsMarkCommand markCommand(ArgumentMultimap argMultimap) throws ParseException, CommandException {
        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT)
                || argMultimap.getPreamble(PREFIX_MARK.getPrefix()).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AcademicsMarkCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble(PREFIX_MARK.getPrefix()));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AcademicsMarkCommand.MESSAGE_USAGE), pe);
        }
        List<String> students = argMultimap.getAllValues(PREFIX_STUDENT);

        return new AcademicsMarkCommand(index, students);
    }

    /**
     * Returns a AcademicsDisplayHomeworkCommand object for execution.
     * {@code ArgumentMultimap}.
     */
    private AcademicsDisplayHomeworkCommand academicsDisplayHomeworkCommand() throws ParseException, CommandException {
        return new AcademicsDisplayHomeworkCommand();
    }

    /**
     * Returns a AcademicsDisplayExamCommand object for execution.
     * {@code ArgumentMultimap}.
     */
    private AcademicsDisplayExamCommand academicsDisplayExamCommand() throws ParseException, CommandException {
        return new AcademicsDisplayExamCommand();
    }

    /**
     * Returns a AcademicsDisplayReportCommand object for execution.
     * {@code ArgumentMultimap}.
     */
    private AcademicsDisplayReportCommand academicsDisplayReportCommand() throws ParseException,
            CommandException {
        return new AcademicsDisplayReportCommand();
    }

    /**
     * Returns a AcademicsDisplayCommand object for execution.
     * {@code ArgumentMultimap}.
     */
    private AcademicsDisplayCommand academicsDisplayCommand() throws ParseException, CommandException {
        return new AcademicsDisplayCommand();
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
