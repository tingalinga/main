package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.academics.AcademicsCommand;
import seedu.address.logic.commands.admin.AdminCommand;
import seedu.address.logic.commands.event.EventCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.notes.NotesCommand;
import seedu.address.logic.commands.student.StudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.notes.NotesCommandParser;

/**
 * Parses user input.
 */
public class TeaPetParser {

    private static final Logger logger = LogsCenter.getLogger(TeaPetParser.class);

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException, CommandException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case StudentCommand.COMMAND_WORD:
            return new StudentCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case NotesCommand.COMMAND_WORD:
            return new NotesCommandParser().parse(arguments);

        case AcademicsCommand.COMMAND_WORD:
            return new AcademicsCommandParser().parse(arguments);

        case AdminCommand.COMMAND_WORD:
            return new AdminCommandParser().parse(arguments);

        case EventCommand.COMMAND_WORD:
            return new EventCommandParser().parse(arguments);

        default:
            logger.info("Unknown Input");
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
