package seedu.address.logic.parser.notes;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_STUDENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.notes.NotesEditCommand;
import seedu.address.logic.commands.notes.NotesEditCommand.EditNotesDescriptor;
import seedu.address.model.notes.Notes;
import seedu.address.testutil.notes.NotesBuilder;

public class NotesEditCommandParserTest {

    private NotesEditCommandParser parser = new NotesEditCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Notes expectedNote = new NotesBuilder().build();
        EditNotesDescriptor desc = new EditNotesDescriptor();
        desc.setStudent(expectedNote.getStudent());
        desc.setContent(expectedNote.getContent());
        desc.setPriority(expectedNote.getPriority());
        int first = 1;

        assertParseSuccess(parser, " " + first + " " + PREFIX_NOTES_STUDENT
                + expectedNote.getStudent()
                + " " + PREFIX_NOTES_CONTENT + expectedNote.getContent()
                + " " + PREFIX_NOTES_PRIORITY + expectedNote.getPriority(),
                new NotesEditCommand(Index.fromOneBased(first), desc));
    }

    @Test
    public void equals() {
        NotesEditCommandParser firstParser = new NotesEditCommandParser();
        NotesEditCommandParser secondParser = new NotesEditCommandParser();

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
