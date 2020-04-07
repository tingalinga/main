package seedu.address.logic.parser.notes;

import static java.util.Objects.requireNonNull;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_STUDENT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.notes.NotesEditCommand;
import seedu.address.logic.commands.notes.NotesEditCommand.EditNotesDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new NotesEditCommand object
 */
public class NotesEditCommandParser implements Parser<NotesEditCommand> {

    /**
     * Creates a NotesEditCommand after parsing of arguments.
     * @param args
     * @return NotesEditCommand
     * @throws ParseException
     */
    public NotesEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NOTES_STUDENT, PREFIX_NOTES_CONTENT, PREFIX_NOTES_PRIORITY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotesEditCommand.MESSAGE_USAGE), pe);
        }

        EditNotesDescriptor editNotesDescriptor = new EditNotesDescriptor();

        if (argMultimap.getValueOptional(PREFIX_NOTES_STUDENT).isPresent()) {
            editNotesDescriptor.setStudent(argMultimap.getValue(PREFIX_NOTES_STUDENT).get());
        }

        if (argMultimap.getValueOptional(PREFIX_NOTES_CONTENT).isPresent()) {
            editNotesDescriptor.setContent(argMultimap.getValue(PREFIX_NOTES_CONTENT).get());
        }

        if (argMultimap.getValueOptional(PREFIX_NOTES_PRIORITY).isPresent()) {
            editNotesDescriptor.setPriority(argMultimap.getValue(PREFIX_NOTES_PRIORITY).get());
        }

        if (!editNotesDescriptor.isAnyFieldEdited()) {
            throw new ParseException(NotesEditCommand.MESSAGE_NOT_EDITED);
        }

        return new NotesEditCommand(index, editNotesDescriptor);
    }
}
