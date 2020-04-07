package seedu.address.logic.parser.notes;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_PRIORITY;
import static seedu.address.model.notes.Notes.PRIORITY_HIGH;
import static seedu.address.model.notes.Notes.PRIORITY_LOW;
import static seedu.address.model.notes.Notes.PRIORITY_MEDIUM;

import java.util.stream.Stream;

import seedu.address.logic.commands.notes.NotesAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Representing a Parser for NotesCommand
 */
public class NotesAddCommandParser implements Parser<NotesAddCommand> {

    /**
     * Parsing of input arguments
     * @param args
     * @return a NotesCommand
     * @throws ParseException user input is in invalid format.
     */
    public NotesAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NOTES_STUDENT, PREFIX_NOTES_CONTENT, PREFIX_NOTES_PRIORITY);
        if (!arePrefixesPresent(argMultimap, PREFIX_NOTES_STUDENT)
                || !arePrefixesPresent(argMultimap, PREFIX_NOTES_CONTENT)
                || !arePrefixesPresent(argMultimap, PREFIX_NOTES_PRIORITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotesAddCommand.MESSAGE_USAGE));
        }

        String name = argMultimap.getValue(PREFIX_NOTES_STUDENT).get();
        String content = argMultimap.getValue(PREFIX_NOTES_CONTENT).get();
        String priority = argMultimap.getValue(PREFIX_NOTES_PRIORITY).get();

        if (!priority.toUpperCase().equals(PRIORITY_HIGH) && !priority.toUpperCase().equals(PRIORITY_MEDIUM)
                && !priority.toUpperCase().equals(PRIORITY_LOW)) {
            throw new ParseException(MESSAGE_INVALID_PRIORITY);
        }

        return new NotesAddCommand(name, content, priority);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


}

