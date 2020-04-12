package seedu.address.logic.parser.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.notes.NotesExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class NotesExportCommandParserTest {

    @Test
    public void parseSuccess() throws ParseException {
        NotesExportCommandParser parser = new NotesExportCommandParser();
        assertEquals(parser.parse(""), new NotesExportCommand());
    }

    @Test
    public void parseFailure() throws ParseException {
        NotesExportCommandParser parser = new NotesExportCommandParser();
        assertThrows(ParseException.class, "Notes Export command does not accept any arguments.",
                () -> parser.parse(" -1"));
    }

    @Test
    public void equals() {
        NotesExportCommandParser firstParser = new NotesExportCommandParser();
        NotesExportCommandParser secondParser = new NotesExportCommandParser();

        //same object -> return true
        assertTrue(firstParser.equals(firstParser));

        //same class -> return true
        assertTrue(firstParser.equals(secondParser));

        //different class -> return false
        assertFalse(firstParser.equals(new NotesAddCommandParser()));

        //different class -> return false
        assertFalse(firstParser.equals(null));
    }
}
