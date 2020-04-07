package seedu.address.logic.parser.notes;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.notes.NotesCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser for NotesComamnd
 */
public class NotesCommandParser implements Parser<NotesCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the NotesCommand
     * and returns a NotesCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public NotesCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String preppedArgs = args.trim();
        System.out.println(preppedArgs);

        if (preppedArgs.equals("")) {
            return new NotesCommand();
        }

        switch(getAction(preppedArgs)) {
        case NotesCommand.NOTES_ADD:
            return new NotesAddCommandParser().parse(getRemainingArguments(preppedArgs));
        case NotesCommand.NOTES_EDIT:
            return new NotesEditCommandParser().parse(getRemainingArguments(preppedArgs));
        case NotesCommand.NOTES_DELETE:
            return new NotesDeleteCommandParser().parse(getRemainingArguments(preppedArgs));
        case NotesCommand.NOTES_FILTER:
            return new NotesFilterCommandParser().parse(getRemainingArguments(preppedArgs));
        case NotesCommand.NOTES_EXPORT:
            return new NotesExportCommandParser().parse(getRemainingArguments(preppedArgs));
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "Notes does not have function "
                    + "requested. Enter 'notes' for help."));
        }

    }

    private String getAction(String preppedArgs) {
        String action = preppedArgs.split(" ")[0];
        return action;
    }

    private String getRemainingArguments (String preppedArgs) throws ParseException {
        String[] parts = preppedArgs.split(" ", 2);
        if (parts.length == 1 && parts[0].equals("export")) {
            return "";
        } else if (parts.length == 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "No arguments were supplied. "
                    + "Enter 'notes' for help."));
        } else {
            return " " + preppedArgs.split(" ", 2)[1];
        }
    }

}
