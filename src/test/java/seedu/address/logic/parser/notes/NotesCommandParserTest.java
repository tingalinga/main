package seedu.address.logic.parser.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.notes.NotesCommand;
import seedu.address.logic.commands.notes.NotesExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class NotesCommandParserTest {

    public static final String INVALID_COMMAND_SUPPLIED = "addition";
    public static final String VALID_COMMAND_SUPPLIED = "export";

    @Test
    public void invalidRequestSupplied_throwsParseException() {
        NotesCommandParser parser = new NotesCommandParser();
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, "Notes does not have function "
                + "requested. Enter 'notes' for help.");
        assertParseFailure(parser, INVALID_COMMAND_SUPPLIED, expectedMessage);
    }

    @Test
    public void parseSuccess() throws ParseException {
        NotesCommandParser parser = new NotesCommandParser();
        NotesCommand expected = new NotesExportCommand();
        assertEquals(parser.parse(VALID_COMMAND_SUPPLIED), expected);
    }

    @Test
    public void equals() {
        NotesCommandParser firstParser = new NotesCommandParser();
        NotesCommandParser secondParser = new NotesCommandParser();

        //same object -> return true
        assertTrue(firstParser.equals(secondParser));

        //same class -> return true
        assertTrue(firstParser.equals(secondParser));

        //different class -> return false
        assertFalse(firstParser.equals(new NotesAddCommandParser()));

        //different class -> return false
        assertFalse(firstParser.equals(null));
    }
}
