package seedu.address.logic.parser.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.notes.NotesDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class NotesDeleteCommandParserTest {

    @Test
    public void parse_success() throws ParseException {
        NotesDeleteCommandParser parser = new NotesDeleteCommandParser();
        Index index = Index.fromOneBased(4);
        assertEquals(new NotesDeleteCommand(index), parser.parse(" 4"));
    }

    @Test
    public void equals() {
        NotesDeleteCommandParser firstParser = new NotesDeleteCommandParser();
        NotesDeleteCommandParser secondParser = new NotesDeleteCommandParser();

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


