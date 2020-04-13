package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.academics.AcademicsCommand;
import seedu.address.logic.commands.academics.AcademicsDeleteCommand;
import seedu.address.logic.commands.academics.AcademicsDisplayCommand;
import seedu.address.logic.commands.academics.AcademicsExportCommand;
import seedu.address.logic.commands.admin.AdminCommand;
import seedu.address.logic.commands.admin.AdminFetchCommand;
import seedu.address.logic.commands.admin.AdminSaveCommand;
import seedu.address.logic.commands.event.EventCommand;
import seedu.address.logic.commands.event.EventDeleteCommand;
import seedu.address.logic.commands.event.EventExportCommand;
import seedu.address.logic.commands.notes.NotesCommand;
import seedu.address.logic.commands.notes.NotesDeleteCommand;
import seedu.address.logic.commands.notes.NotesExportCommand;
import seedu.address.logic.commands.student.StudentAddCommand;
import seedu.address.logic.commands.student.StudentClearCommand;
import seedu.address.logic.commands.student.StudentCommand;
import seedu.address.logic.commands.student.StudentDeleteCommand;
import seedu.address.logic.commands.student.StudentEditCommand;
import seedu.address.logic.commands.student.StudentFindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.testutil.student.EditStudentDescriptorBuilder;
import seedu.address.testutil.student.StudentBuilder;
import seedu.address.testutil.student.StudentUtil;

public class TeaPetParserTest {

    private final TeaPetParser parser = new TeaPetParser();

    @Test
    public void parseCommand_add() throws Exception {
        Student student = new StudentBuilder().build();
        StudentAddCommand command = (StudentAddCommand) parser.parseCommand(StudentUtil.getAddCommand(student));
        assertEquals(new StudentAddCommand(student), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(StudentClearCommand.COMMAND_WORD + " clear") instanceof StudentClearCommand);
        assertTrue(parser.parseCommand(StudentClearCommand.COMMAND_WORD + " clear 3") instanceof StudentClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        StudentDeleteCommand command = (StudentDeleteCommand) parser.parseCommand(
                StudentDeleteCommand.COMMAND_WORD + " delete " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new StudentDeleteCommand(INDEX_FIRST_STUDENT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        StudentEditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        System.out.println(StudentEditCommand.COMMAND_WORD + " edit "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        StudentEditCommand command = (StudentEditCommand) parser.parseCommand(StudentEditCommand.COMMAND_WORD + " edit "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new StudentEditCommand(INDEX_FIRST_STUDENT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        StudentFindCommand command = (StudentFindCommand) parser.parseCommand(
                StudentFindCommand.COMMAND_WORD + " find " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new StudentFindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_student() throws Exception {
        assertTrue(parser.parseCommand(StudentCommand.COMMAND_WORD) instanceof StudentCommand);
        assertTrue(parser.parseCommand(StudentCommand.COMMAND_WORD + " detailed") instanceof StudentCommand);
        assertTrue(parser.parseCommand(StudentCommand.COMMAND_WORD + " find Freddy") instanceof StudentCommand);
    }

    @Test
    public void parseCommand_academics() throws Exception {
        assertTrue(parser.parseCommand(AcademicsCommand.COMMAND_WORD) instanceof AcademicsCommand);
        assertTrue(parser.parseCommand(AcademicsCommand.COMMAND_WORD + " delete 1") instanceof AcademicsCommand);
        assertTrue(parser.parseCommand(AcademicsCommand.COMMAND_WORD + " exam") instanceof AcademicsCommand);
        assertTrue(parser.parseCommand(AcademicsCommand.COMMAND_WORD + " homework") instanceof AcademicsCommand);
        assertTrue(parser.parseCommand(AcademicsCommand.COMMAND_WORD + " report") instanceof AcademicsCommand);
        assertTrue(parser.parseCommand(AcademicsCommand.COMMAND_WORD + " export") instanceof AcademicsCommand);

        assertTrue(parser.parseCommand(AcademicsCommand.COMMAND_WORD + " delete 1") instanceof AcademicsDeleteCommand);
        assertTrue(parser.parseCommand(AcademicsCommand.COMMAND_WORD + " exam") instanceof AcademicsDisplayCommand);
        assertTrue(parser.parseCommand(AcademicsCommand.COMMAND_WORD + " homework") instanceof AcademicsDisplayCommand);
        assertTrue(parser.parseCommand(AcademicsCommand.COMMAND_WORD + " report") instanceof AcademicsDisplayCommand);
        assertTrue(parser.parseCommand(AcademicsCommand.COMMAND_WORD + " export") instanceof AcademicsExportCommand);
    }

    @Test
    public void parseCommand_admin() throws Exception {
        assertTrue(parser.parseCommand(AdminCommand.COMMAND_WORD) instanceof AdminCommand);
        assertTrue(parser.parseCommand(AdminCommand.COMMAND_WORD + " save") instanceof AdminCommand);
        assertTrue(parser.parseCommand(AdminCommand.COMMAND_WORD + " fetch 2020-03-04") instanceof AdminCommand);

        assertTrue(parser.parseCommand(AdminCommand.COMMAND_WORD + " save") instanceof AdminSaveCommand);
        assertTrue(parser.parseCommand(AdminCommand.COMMAND_WORD + " fetch 2020-03-04") instanceof AdminFetchCommand);
    }

    @Test
    public void parseCommand_notes() throws Exception {
        assertTrue(parser.parseCommand(NotesCommand.COMMAND_WORD) instanceof NotesCommand);
        assertTrue(parser.parseCommand(NotesCommand.COMMAND_WORD + " delete 1") instanceof NotesCommand);
        assertTrue(parser.parseCommand(NotesCommand.COMMAND_WORD + " export") instanceof NotesCommand);

        assertTrue(parser.parseCommand(NotesCommand.COMMAND_WORD + " delete 1") instanceof NotesDeleteCommand);
        assertTrue(parser.parseCommand(NotesCommand.COMMAND_WORD + " export") instanceof NotesExportCommand);
    }

    @Test
    public void parseCommand_events() throws Exception {
        assertTrue(parser.parseCommand(EventCommand.COMMAND_WORD) instanceof EventCommand);
        assertTrue(parser.parseCommand(EventCommand.COMMAND_WORD + " delete 1") instanceof EventCommand);
        assertTrue(parser.parseCommand(EventCommand.COMMAND_WORD + " export") instanceof EventCommand);

        assertTrue(parser.parseCommand(EventCommand.COMMAND_WORD + " delete 1") instanceof EventDeleteCommand);
        assertTrue(parser.parseCommand(EventCommand.COMMAND_WORD + " export") instanceof EventExportCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
