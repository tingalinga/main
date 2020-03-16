package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK2;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.Remark2Command;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Remark2;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class Remark2CommandParser implements Parser<Remark2Command> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code Remark2Command} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public Remark2Command parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMARK2);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Remark2Command.MESSAGE_USAGE), ive);
        }

        String remark = argMultimap.getValue(PREFIX_REMARK2).orElse("");

        return new Remark2Command(index, new Remark2(remark));
    }
}