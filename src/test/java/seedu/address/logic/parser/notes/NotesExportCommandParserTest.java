package seedu.address.logic.parser.notes;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NotesExportCommandParserTest {

    @Test
    public void equals() {
        NotesExportCommandParser firstParser = new NotesExportCommandParser();
        NotesExportCommandParser secondParser = new NotesExportCommandParser();

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
