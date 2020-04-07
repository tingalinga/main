package seedu.address.logic.parser.notes;

import static java.util.Objects.requireNonNull;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_STUDENT;
import static seedu.address.model.notes.Notes.*;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.notes.NotesEditCommand;
import seedu.address.logic.commands.notes.NotesEditCommand.EditNotesDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Representing a Parser for NotesEditCommand
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

        //Obtaining input from Notes_Student prefix
        if (argMultimap.getValue(PREFIX_NOTES_STUDENT).isPresent()) {
            String name = argMultimap.getValue(PREFIX_NOTES_STUDENT).get();

            if (name.trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "Student name field is empty."));
            }
            editNotesDescriptor.setStudent(name);
        }

        //Obtaining input from Notes_Content prefix
        if (argMultimap.getValue(PREFIX_NOTES_CONTENT).isPresent()) {
            String content = argMultimap.getValue(PREFIX_NOTES_CONTENT).get();

            if(content.trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "Content field is empty."));
            }
            editNotesDescriptor.setContent(content);
        }

        //Obtaining input from Notes_Priority prefix
        if (argMultimap.getValue(PREFIX_NOTES_PRIORITY).isPresent()) {
            String priority = argMultimap.getValue(PREFIX_NOTES_PRIORITY).get();

            if(priority.trim().equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "Priority field is empty."));
            }
            //Checking for validity of input priority.
            if (!priority.toUpperCase().equals(PRIORITY_HIGH) && !priority.toUpperCase().equals(PRIORITY_MEDIUM)
                    && !priority.toUpperCase().equals(PRIORITY_LOW)) {
                throw new ParseException(MESSAGE_INVALID_PRIORITY);
            }
            editNotesDescriptor.setPriority(priority);
        }

        if (!editNotesDescriptor.isAnyFieldEdited()) {
            throw new ParseException(NotesEditCommand.MESSAGE_NOT_EDITED);
        }

        return new NotesEditCommand(index, editNotesDescriptor);
    }
}
