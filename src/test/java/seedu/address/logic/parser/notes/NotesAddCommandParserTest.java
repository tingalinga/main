package seedu.address.logic.parser.notes;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_STUDENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.notes.NotesAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.notes.Notes;
import seedu.address.testutil.notes.NotesBuilder;

public class NotesAddCommandParserTest {

    private NotesAddCommandParser parser = new NotesAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Notes expectedNote = new NotesBuilder().build();

        assertParseSuccess(parser, " " + PREFIX_NOTES_STUDENT + expectedNote.getStudent()
                + " " + PREFIX_NOTES_CONTENT + expectedNote.getContent()
                + " " + PREFIX_NOTES_PRIORITY + expectedNote.getPriority(),
                new NotesAddCommand(expectedNote));
    }

    @Test
    public void parse_invalidFormat_throwsParseException() {
        NotesAddCommandParser parser = new NotesAddCommandParser();

        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                NotesAddCommand.MESSAGE_USAGE), () -> parser.parse("..."));
    }

    @Test
    public void parse_studentNameEmpty_throwsParseException() {
        NotesAddCommandParser parser = new NotesAddCommandParser();

        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                "Student name field is empty."), () -> parser.parse(" name/ cont/Temporary"
                + " pr/HIGH"));
    }

    @Test
    public void parse_contentEmpty_throwsParseException() {
        NotesAddCommandParser parser = new NotesAddCommandParser();

        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                "Content field is empty."), () -> parser.parse(" name/Kelvin Klein cont/ pr/HIGH"));
    }

    @Test
    public void parse_priorityEmpty_throwsParseException() {
        NotesAddCommandParser parser = new NotesAddCommandParser();

        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                "Priority field is empty."), () -> parser.parse(" name/Kelvin Klein cont/Temporary pr/"));
    }

    @Test
    public void parse_invalidPriority_throwsParseException() {
        NotesAddCommandParser parser = new NotesAddCommandParser();

        assertThrows(ParseException.class, MESSAGE_INVALID_PRIORITY, () ->
                parser.parse(" name/Kelvin Klein cont/Temporary pr/Lowmedium"));
    }



    @Test
    public void equals() {
        NotesAddCommandParser firstParser = new NotesAddCommandParser();
        NotesAddCommandParser secondParser = new NotesAddCommandParser();

        //same object -> return true
        assertTrue(firstParser.equals(firstParser));

        //same class -> return true
        assertTrue(firstParser.equals(secondParser));

        //different class -> return false
        assertFalse(firstParser.equals(new NotesEditCommandParser()));

        //different class -> return false
        assertFalse(firstParser.equals(null));
    }

}
