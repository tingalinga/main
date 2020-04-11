package seedu.address.logic.parser.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
