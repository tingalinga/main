package seedu.address.logic.parser.notes;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_STUDENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.notes.NotesAddCommand;
import seedu.address.model.notes.Notes;
import seedu.address.testutil.NotesBuilder;

public class NotesAddCommandParserTest {

    private NotesAddCommandParser parser = new NotesAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Notes expectedNote = new NotesBuilder().build();

        assertParseSuccess(parser, " " + PREFIX_NOTES_STUDENT + expectedNote.getStudent()
                + " " + PREFIX_NOTES_CONTENT + expectedNote.getContent()
                + " " + PREFIX_NOTES_PRIORITY + expectedNote.getPriority(),
                new NotesAddCommand(expectedNote.getStudent(), expectedNote.getContent(),
                        expectedNote.getPriority()));
    }
}
