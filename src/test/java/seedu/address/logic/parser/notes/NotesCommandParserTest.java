package seedu.address.logic.parser.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.notes.NotesAddCommand;
import seedu.address.logic.commands.notes.NotesCommand;
import seedu.address.logic.commands.notes.NotesDeleteCommand;
import seedu.address.logic.commands.notes.NotesEditCommand;
import seedu.address.logic.commands.notes.NotesExportCommand;
import seedu.address.logic.commands.notes.NotesFilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.notes.Notes;
import seedu.address.model.notes.NotesContainKeywordsPredicate;
import seedu.address.testutil.EditNotesDescriptorBuilder;

public class NotesCommandParserTest {

    public static final String INVALID_COMMAND_SUPPLIED = "addition";
    public static final String VALID_NOTES_COMMAND_SUPPLIED = "";
    public static final String VALID_ADD_COMMAND_SUPPLIED = "add name/Kelvin Klein c/Good Student pr/LOW";
    public static final String VALID_EDIT_COMMAND_SUPPLIED = "edit 1 name/Kelvin Klein c/Good Student pr/LOW";
    public static final String VALID_DELETE_COMMAND_SUPPLIED = "delete 1";
    public static final String VALID_FILTER_COMMAND_SUPPLIED = "filter low high medium";
    public static final String VALID_EXPORT_COMMAND_SUPPLIED = "export";

    @Test
    public void invalidRequestSupplied_throwsParseException() {
        NotesCommandParser parser = new NotesCommandParser();
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, "Notes does not have function "
                + "requested. Enter 'notes' for help.");
        assertParseFailure(parser, INVALID_COMMAND_SUPPLIED, expectedMessage);
    }

    @Test
    public void parseSuccessNotesCommand() throws ParseException {
        NotesCommandParser parser = new NotesCommandParser();
        NotesCommand expected = new NotesCommand();
        assertEquals(parser.parse(VALID_NOTES_COMMAND_SUPPLIED), expected);
    }


    @Test
    public void parseSuccessNotesAddCommand() throws ParseException {
        NotesCommandParser parser = new NotesCommandParser();
        NotesAddCommand expected = new NotesAddCommand(new Notes("Kelvin Klein", "Good Student", "LOW"));
        assertEquals(parser.parse(VALID_ADD_COMMAND_SUPPLIED), expected);
    }

    @Test
    public void parseSuccessNotesEditCommand() throws ParseException {
        NotesCommandParser parser = new NotesCommandParser();
        NotesEditCommand expected = new NotesEditCommand(Index.fromOneBased(1),
                new EditNotesDescriptorBuilder()
                .withStudent("Kelvin Klein")
                .withContent("Good Student")
                .withPriority("LOW")
                .build());
        assertEquals(parser.parse(VALID_EDIT_COMMAND_SUPPLIED), expected);
    }

    @Test
    public void parseSuccessNotesDeleteCommand() throws ParseException {
        NotesCommandParser parser = new NotesCommandParser();
        NotesDeleteCommand expected = new NotesDeleteCommand(Index.fromOneBased(1));
        assertEquals(parser.parse(VALID_DELETE_COMMAND_SUPPLIED), expected);
    }

    @Test
    public void parseSuccessNotesFilterCommand() throws ParseException {
        NotesCommandParser parser = new NotesCommandParser();
        NotesFilterCommand expected = new NotesFilterCommand(
                new NotesContainKeywordsPredicate(Arrays.asList("low", "high", "medium")));
        assertEquals(parser.parse(VALID_FILTER_COMMAND_SUPPLIED), expected);
    }

    @Test
    public void parseSuccessNotesExportCommand() throws ParseException {
        NotesCommandParser parser = new NotesCommandParser();
        NotesCommand expected = new NotesExportCommand();
        assertEquals(parser.parse(VALID_EXPORT_COMMAND_SUPPLIED), expected);
    }

    @Test
    public void equals() {
        NotesCommandParser firstParser = new NotesCommandParser();
        NotesCommandParser secondParser = new NotesCommandParser();

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
