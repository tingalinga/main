package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.stream.Stream;

import seedu.address.logic.commands.academics.AcademicsAddCommand;
import seedu.address.logic.commands.academics.AcademicsCommand;
import seedu.address.logic.commands.academics.display.AcademicsDisplayCommand;
import seedu.address.logic.commands.academics.display.AcademicsDisplayExamCommand;
import seedu.address.logic.commands.academics.display.AcademicsDisplayHomeworkCommand;
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
                ArgumentTokenizer.tokenize(args, PREFIX_ADD, PREFIX_HOMEWORK, PREFIX_EXAM,
                        PREFIX_ASSESSMENT_DESCRIPTION,
                        PREFIX_ASSESSMENT_TYPE, PREFIX_ASSESSMENT_DATE);

        if (argMultimap.getValue(PREFIX_ADD).isPresent()) {
            return addCommand(argMultimap);
        } else if (argMultimap.getValue(PREFIX_HOMEWORK).isPresent()) {
            return academicsDisplayHomeworkCommand();
        } else if (argMultimap.getValue(PREFIX_EXAM).isPresent()) {
            return academicsDisplayExamCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HELP_MESSAGE));
        }
    }

    /**
     * Adds the given assessment details to academic report.
     * {@code ArgumentMultimap}.
     */
    private AcademicsAddCommand addCommand(ArgumentMultimap argMultimap) throws ParseException, CommandException {
        if (!arePrefixesPresent(argMultimap, PREFIX_ASSESSMENT_DESCRIPTION, PREFIX_ASSESSMENT_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AcademicsAddCommand.MESSAGE_USAGE));
        }

        String description = argMultimap.getValue(PREFIX_ASSESSMENT_DESCRIPTION).get();
        String type = argMultimap.getValue(PREFIX_ASSESSMENT_TYPE).get();
        String date = argMultimap.getValue(PREFIX_ASSESSMENT_DATE).get();

        return new AcademicsAddCommand(description, type, date);
    }

    /**
     * Displays academics list of homework assessments.
     * {@code ArgumentMultimap}.
     */
    private AcademicsDisplayHomeworkCommand academicsDisplayHomeworkCommand() throws ParseException, CommandException {
        return new AcademicsDisplayHomeworkCommand();
    }

    /**
     * Displays academics list of exam assessments.
     * {@code ArgumentMultimap}.
     */
    private AcademicsDisplayExamCommand academicsDisplayExamCommand() throws ParseException, CommandException {
        return new AcademicsDisplayExamCommand();
    }

    /**
     * Displays academics list of assessments.
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
