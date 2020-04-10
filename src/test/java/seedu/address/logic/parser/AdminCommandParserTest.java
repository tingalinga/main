package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.admin.AdminDatesCommand;
import seedu.address.logic.commands.admin.AdminDeleteCommand;
import seedu.address.logic.commands.admin.AdminDisplayCommand;
import seedu.address.logic.commands.admin.AdminFetchCommand;
import seedu.address.logic.commands.admin.AdminSaveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.admin.DateContainsKeywordsPredicate;

public class AdminCommandParserTest {

    private final AdminCommandParser parser = new AdminCommandParser();

    @Test
    public void parseCommand_fetch() throws Exception {
        assertTrue(parser.parse(AdminFetchCommand.COMMAND_WORD + " fetch 2020-04-04") instanceof AdminFetchCommand);
        assertTrue(parser.parse(AdminFetchCommand.COMMAND_WORD + " fetch 1997-04-26") instanceof AdminFetchCommand);
        assertThrows(ParseException.class, () -> parser.parse(AdminFetchCommand.COMMAND_WORD + " fetch"));
        assertThrows(ParseException.class, () -> parser.parse(AdminFetchCommand.COMMAND_WORD
                + " fetch 2020-04-04 dummy"));
        assertThrows(ParseException.class, () -> parser.parse(AdminFetchCommand.COMMAND_WORD
                + " fetch 2020-24-04"));
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DateContainsKeywordsPredicate predicate = new DateContainsKeywordsPredicate(LocalDate.parse("2020-04-04"));
        AdminDeleteCommand command = (AdminDeleteCommand) parser.parse(
                AdminDeleteCommand.COMMAND_WORD + " delete " + "2020-04-04");
        assertEquals(new AdminDeleteCommand(predicate), command);
        assertThrows(ParseException.class, () -> parser.parse(AdminDeleteCommand.COMMAND_WORD + " delete"));
        assertThrows(ParseException.class, () -> parser.parse(AdminDeleteCommand.COMMAND_WORD
                + " delete 2020-04-04 dummy"));
        assertThrows(ParseException.class, () -> parser.parse(AdminFetchCommand.COMMAND_WORD
                + " delete 2020-24-04"));
    }

    @Test
    public void parseCommand_dates() throws Exception {
        assertTrue(parser.parse(AdminDatesCommand.COMMAND_WORD + " dates") instanceof AdminDatesCommand);
        assertTrue(parser.parse(AdminFetchCommand.COMMAND_WORD + " dates    ") instanceof AdminDatesCommand);
        assertThrows(ParseException.class, () -> parser.parse(AdminSaveCommand.COMMAND_WORD + " dates dummy"));
    }

    @Test
    public void parseCommand_display() throws Exception {
        assertTrue(parser.parse(AdminDisplayCommand.COMMAND_WORD) instanceof AdminDisplayCommand);
        assertTrue(parser.parse(AdminDisplayCommand.COMMAND_WORD + "   ") instanceof AdminDisplayCommand);
    }

    @Test
    public void parseCommand_save() throws Exception {
        assertTrue(parser.parse(AdminSaveCommand.COMMAND_WORD + " save") instanceof AdminSaveCommand);
        assertTrue(parser.parse(AdminSaveCommand.COMMAND_WORD + " save    ") instanceof AdminSaveCommand);
        assertThrows(ParseException.class, () -> parser.parse(AdminSaveCommand.COMMAND_WORD + " save dummy"));
    }
}
