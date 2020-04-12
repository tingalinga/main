package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INDEX_INVALID_EVENT_NAME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALL_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GET_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VIEW_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VIEW_MODE;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.event.EventAddCommand;
import seedu.address.logic.commands.event.EventDeleteCommand;
import seedu.address.logic.commands.event.EventEditCommand;
import seedu.address.logic.commands.event.EventExportCommand;
import seedu.address.logic.commands.event.EventIndexAllCommand;
import seedu.address.logic.commands.event.EventIndexCommand;
import seedu.address.logic.commands.event.EventViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class EventCommandParserTest {
    public static final String VALID_EVENT_NAME = "test event";
    public static final String VALID_COLOR_STRING = "2";
    public static final String VALID_START_DATE_TIME_STRING = "2020-04-11T03:00";
    public static final String VALID_END_DATE_TIME_STRING = "2020-04-11T04:00";
    public static final String VALID_RECUR_TYPE_STRING = "daily";
    public static final String VALID_VIEW_MODE = "weekly";
    public static final String VALID_TARGET_DATE = "2020-01-01";

    public static final String VALID_COMMAND_DEFAULT = " " + PREFIX_ADD + " " + PREFIX_EVENT_NAME
            + VALID_EVENT_NAME + " " + PREFIX_START_DATETIME + VALID_START_DATE_TIME_STRING + " "
            + PREFIX_END_DATETIME + VALID_END_DATE_TIME_STRING + " "
            + PREFIX_RECUR + VALID_RECUR_TYPE_STRING + " " + PREFIX_COLOR + VALID_COLOR_STRING;

    public static final String INVALID_ADD_MISSING_EVENT_NAME_PREFIX = " " + PREFIX_ADD + " "
            + PREFIX_START_DATETIME + VALID_START_DATE_TIME_STRING + " "
            + PREFIX_END_DATETIME + VALID_END_DATE_TIME_STRING + " "
            + PREFIX_RECUR + VALID_RECUR_TYPE_STRING + " " + PREFIX_COLOR + VALID_COLOR_STRING;

    public static final String INVALID_ADD_MISSING_EVENT_DESCRIPTION = " " + PREFIX_ADD + " " + PREFIX_EVENT_NAME
            + " " + PREFIX_START_DATETIME + VALID_START_DATE_TIME_STRING + " "
            + PREFIX_END_DATETIME + VALID_END_DATE_TIME_STRING + " "
            + PREFIX_RECUR + VALID_RECUR_TYPE_STRING + " " + PREFIX_COLOR + VALID_COLOR_STRING;

    public static final String VALID_FULL_EDIT_COMMAND = " " + PREFIX_EDIT + " " + "1" + " " + PREFIX_EVENT_NAME
            + VALID_EVENT_NAME + " " + PREFIX_START_DATETIME + VALID_START_DATE_TIME_STRING + " "
            + PREFIX_END_DATETIME + VALID_END_DATE_TIME_STRING + " "
            + PREFIX_RECUR + VALID_RECUR_TYPE_STRING + " " + PREFIX_COLOR + VALID_COLOR_STRING;

    public static final String INVALID_VIEW_NO_PARAM_COMMAND = " " + PREFIX_VIEW;
    public static final String VALID_VIEW_MODE_FULL = " " + PREFIX_VIEW + " " + PREFIX_VIEW_MODE + " "
            + VALID_VIEW_MODE + " " + PREFIX_VIEW_DATE + " " + VALID_TARGET_DATE;
    public static final String VALID_EXPORT_COMMAND = " " + PREFIX_EXPORT;
    public static final String VALID_INDEX_ALL_COMMAND = " " + PREFIX_ALL_INDEX;
    public static final String VALID_DELETE = " " + PREFIX_DELETE + "1";
    public static final String INVALID_DELETE = " " + PREFIX_DELETE + "one";
    public static final String INVALID_DELETE_ZERO_INDEX = " " + PREFIX_DELETE + "0";
    public static final String INVALID_DELETE_NEGATIVE_INDEX = " " + PREFIX_DELETE + "-1";
    public static final String INVALID_DELETE_NO_INDEX = " " + PREFIX_DELETE;
    public static final String VALID_INDEX_GET = " " + PREFIX_GET_INDEX + "test event";
    public static final String INVALID_INDEX_OF = " " + PREFIX_GET_INDEX;


    private final EventCommandParser parser = new EventCommandParser();

    /**
     * Test parsing a valid EventAddCommand
     * @throws Exception
     */
    @Test
    public void parseCommand_validAddCommand_success() throws Exception {
        Command command = parser.parse(VALID_COMMAND_DEFAULT);
        assertTrue(command instanceof EventAddCommand);
    }

    /**
     * Test parsing EventAddCommand with missing event name prefix
     */
    @Test
    public void parseCommand_invalidAddMissingEventNamePrefix_exceptionThrown() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventAddCommand.MESSAGE_USAGE), () ->
                        parser.parse(INVALID_ADD_MISSING_EVENT_NAME_PREFIX));
    }

    /**
     * Test parsing a EventAddCommand with missing event name
     */
    @Test
    public void parseCommand_invalidAddMissingEventName_exceptionThrown() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EventAddCommand.MESSAGE_USAGE), () -> parser.parse(INVALID_ADD_MISSING_EVENT_DESCRIPTION));
    }

    /**
     * Test parsing a valid EventEditCommand
     * @throws Exception
     */
    @Test
    public void parseCommand_validEditCommand_success() throws Exception {
        Command command = parser.parse(VALID_FULL_EDIT_COMMAND);
        assertTrue(command instanceof EventEditCommand);
    }

    /**
     * Test valid EventViewCommand with no parameters
     * @throws Exception
     */
    @Test
    public void parseCommand_invalidViewNoParameter_throwsException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EventViewCommand.MESSAGE_USAGE), () -> parser.parse(INVALID_VIEW_NO_PARAM_COMMAND));
    }

    /**
     * Test valid EventViewCommand with mode and date parameter
     * @throws Exception
     */
    @Test
    public void parseCommand_validViewCommandWithValidModeAndValidDate_success() throws Exception {
        Command command = parser.parse(VALID_VIEW_MODE_FULL);
        assertTrue(command instanceof EventViewCommand);
    }


    /**
     * Test valid EventExportCommand
     * @throws Exception
     */
    @Test
    public void parseCommand_validExportCommand_success() throws Exception {
        Command command = parser.parse(VALID_EXPORT_COMMAND);
        assertTrue(command instanceof EventExportCommand);
    }

    /**
     * Test valid EventIndexAllCommand
     * @throws Exception
     */
    @Test
    public void parseCommand_validIndexAllCommand_success() throws Exception {
        Command command = parser.parse(VALID_INDEX_ALL_COMMAND);
        assertTrue(command instanceof EventIndexAllCommand);
    }

    /**
     * Test a valid EventDeleteCommand
     * @throws Exception
     */
    @Test
    public void parseCommand_validDeleteCommand_success() throws Exception {
        Command command = parser.parse(VALID_DELETE);
        assertTrue(command instanceof EventDeleteCommand);
    }

    /**
     * Test a invalid EventDeleteCommand
     */
    @Test
    public void parseCommand_invalidDelete_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventDeleteCommand.MESSAGE_USAGE), () ->
                        parser.parse(INVALID_DELETE));
    }

    /**
     * Test invalid EventDeleteCommand by passing zero as index
     */
    @Test
    public void parseCommand_invalidDeleteZeroIndex_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventDeleteCommand.MESSAGE_USAGE), () ->
                        parser.parse(INVALID_DELETE_ZERO_INDEX));
    }

    /**
     * Test invalid EventDeleteCommand by passing negative index value
     */
    @Test
    public void parseCommand_invalidDeleteNegativeIndex_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventDeleteCommand.MESSAGE_USAGE), () ->
                        parser.parse(INVALID_DELETE_NEGATIVE_INDEX));
    }

    /**
     * Test invalid EventDeleteCommand by not passing a index
     */
    @Test
    public void parseCommand_invalidDeleteNoIndex_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventDeleteCommand.MESSAGE_USAGE), () ->
                        parser.parse(INVALID_DELETE_NO_INDEX));
    }

    /**
     * Test a valid EventIndexCommand
     * @throws Exception
     */
    @Test
    public void parseCommand_validIndexGet_success() throws Exception {
        Command command = parser.parse(VALID_INDEX_GET);
        assertTrue(command instanceof EventIndexCommand);
    }

    /**
     * Test a invalid event index command with empty argument
     * @throws Exception
     */
    @Test
    public void parseCommand_invalidIndexOf_throwsParseException() {
        assertThrows(ParseException.class,
                MESSAGE_INDEX_INVALID_EVENT_NAME, () ->
                        parser.parse(INVALID_INDEX_OF));
    }
}