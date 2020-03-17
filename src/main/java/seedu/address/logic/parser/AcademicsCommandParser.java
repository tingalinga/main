package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.stream.Stream;

import seedu.address.logic.commands.AcademicsAddCommand;
import seedu.address.logic.commands.AcademicsCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

public class AcademicsCommandParser implements Parser<AcademicsCommand> {

    public static final String HELP_MESSAGE = "Academics command has to include an action.\n"
            + AcademicsAddCommand.MESSAGE_USAGE;

    @Override
    public AcademicsCommand parse(String args) throws ParseException, CommandException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ADD, PREFIX_ASSESSMENT_DESCRIPTION,
                        PREFIX_ASSESSMENT_TYPE, PREFIX_ASSESSMENT_DATE);

        if (argMultimap.getValue(PREFIX_ADD).isPresent()) {
            return addCommand(argMultimap);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HELP_MESSAGE));
        }
    }

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

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}