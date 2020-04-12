package seedu.address.logic.parser.notes;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.notes.NotesDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

public class NotesDeleteCommandParserTest {

    @Test
    public void parse_success() throws ParseException {
        NotesDeleteCommandParser parser = new NotesDeleteCommandParser();
        Index index = Index.fromOneBased(4);
        assertEquals(new NotesDeleteCommand(index), parser.parse(" 4"));
    }

    @Test
    public void parse_failure() throws ParseException {
        NotesDeleteCommandParser parser = new NotesDeleteCommandParser();
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, "Index provided must be greater than zero."),
                () -> parser.parse(" -1"));
    }


    @Test
    public void equals() {
        NotesDeleteCommandParser firstParser = new NotesDeleteCommandParser();
        NotesDeleteCommandParser secondParser = new NotesDeleteCommandParser();

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


