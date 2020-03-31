package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.RefreshCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.commands.academics.AcademicsCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.notes.NotesAddCommand;
import seedu.address.logic.commands.notes.NotesDeleteCommand;
import seedu.address.logic.commands.notes.NotesExportCommand;
import seedu.address.logic.commands.notes.NotesFilterCommand;
import seedu.address.logic.commands.studentdisplay.AdminStudentDisplayCommand;
import seedu.address.logic.commands.studentdisplay.DefaultStudentDisplayCommand;
import seedu.address.logic.commands.studentdisplay.DetailedStudentDisplayCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.notes.NotesAddCommandParser;
import seedu.address.logic.parser.notes.NotesDeleteCommandParser;
import seedu.address.logic.parser.notes.NotesFilterCommandParser;

/**
 * Parses user input.
 */
public class TeaPetParser {

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

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:

            return new DeleteCommandParser().parse(arguments);
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case RemarkCommand.COMMAND_WORD:
            return new RemarkCommandParser().parse(arguments);

        case RefreshCommand.COMMAND_WORD:
            return new RefreshCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case NotesAddCommand.COMMAND_WORD:
            return new NotesAddCommandParser().parse(arguments);

        case NotesDeleteCommand.COMMAND_WORD:
            return new NotesDeleteCommandParser().parse(arguments);

        case NotesExportCommand.COMMAND_WORD:
            return new NotesExportCommand();

        case NotesFilterCommand.COMMAND_WORD:
            return new NotesFilterCommandParser().parse(arguments);

        case AcademicsCommand.COMMAND_WORD:
            return new AcademicsCommandParser().parse(arguments);

        case DetailedStudentDisplayCommand.COMMAND_WORD:
            return new DetailedStudentDisplayCommand();

        case AdminStudentDisplayCommand.COMMAND_WORD:
            return new AdminStudentDisplayCommand();

        case DefaultStudentDisplayCommand.COMMAND_WORD:
            return new DefaultStudentDisplayCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
