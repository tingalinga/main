package seedu.address.logic.parser.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.notes.NotesFilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.notes.NotesContainKeywordsPredicate;

public class NotesFilterCommandParserTest {

    @Test
    public void parseSuccess() throws ParseException {
        NotesFilterCommandParser parser = new NotesFilterCommandParser();
        String[] keywords = new String[] {"low", "high", "medium"};
        String arguments = " low high medium";
        assertEquals(parser.parse(arguments),
                new NotesFilterCommand(new NotesContainKeywordsPredicate(Arrays.asList(keywords))));
    }

    @Test
    public void equals() {
        NotesFilterCommandParser firstParser = new NotesFilterCommandParser();
        NotesFilterCommandParser secondParser = new NotesFilterCommandParser();

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
