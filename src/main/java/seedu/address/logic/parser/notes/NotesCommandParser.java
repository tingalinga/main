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
     * and returns respective feature commands.
     * @throws ParseException if the user input does not conform the expected format,
     * or entered an irrelevant feature prefix.
     */
    public NotesCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String preppedArgs = args.trim();

        //User enters 'notes' itself
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

    /**
     * Obtains the prefix of the corresponding notes feature command
     */
    private String getAction(String preppedArgs) {
        String action = preppedArgs.split(" ")[0];
        return action;
    }

    /**
     * Removes the feature prefix from the preppedArgument.
     * This new string will be passed by the respective feature command parsers.
     * @param preppedArgs
     * @throws ParseException if user did not pass in any arguments after the feature command.
     * Eg. 'notes add', 'notes edit'
     */
    private String getRemainingArguments (String preppedArgs) throws ParseException {
        //Splits the preppedArgs into 2 parts based on the first spacing encountered.
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof NotesCommandParser)) {
            return false;
        }

        return true;
    }


}
