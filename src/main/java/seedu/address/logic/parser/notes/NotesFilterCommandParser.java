package seedu.address.logic.parser.notes;

import seedu.address.logic.commands.notes.NotesFilterCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.Arrays;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class NotesFilterCommandParser implements Parser<NotesFilterCommand> {

    public NotesFilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if(trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    NotesFilterCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");

        return new NotesFilterCommand(Arrays.asList(keywords));
    }
}
