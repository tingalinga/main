package seedu.address.logic.parser.notes;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_STUDENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.notes.NotesAddCommand;
import seedu.address.model.notes.Notes;
import seedu.address.testutil.NotesBuilder;

public class NotesEditCommandParserTest {

    private NotesEditCommandParser parser = new NotesEditCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Notes expectedNote = new NotesBuilder().build();

    }

}
