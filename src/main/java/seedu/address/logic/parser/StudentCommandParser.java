package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEFAULT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAILED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REFRESH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEMPERATURE;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.student.DefaultStudentDisplayCommand;
import seedu.address.logic.commands.student.DetailedStudentDisplayCommand;
import seedu.address.logic.commands.student.StudentAddCommand;
import seedu.address.logic.commands.student.StudentCommand;
import seedu.address.logic.commands.student.StudentDeleteCommand;
import seedu.address.logic.commands.student.StudentEditCommand;
import seedu.address.logic.commands.student.StudentEditCommand.EditStudentDescriptor;
import seedu.address.logic.commands.student.StudentFindCommand;
import seedu.address.logic.commands.student.StudentRefreshCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Address;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.NextOfKin;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.Temperature;
import seedu.address.model.tag.Tag;

/**
 * Parses the given {@code String} of arguments in the context of the StudentCommand
 * and returns an StudentCommand object for execution.
 *
 * @throws ParseException if the user input does not conform the expected format
 */
public class StudentCommandParser implements Parser<StudentCommand> {

    public static final String HELP_MESSAGE = "Student command has to include an action.\n"
            + StudentCommand.MESSAGE_USAGE;

    @Override
    public StudentCommand parse(String args) throws ParseException, CommandException {
        requireNonNull(args);

        if (args.equals("")) {
            return defaultDisplayCommand();
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ADD, PREFIX_EDIT, PREFIX_DELETE, PREFIX_FIND, PREFIX_REFRESH,
                        PREFIX_DEFAULT, PREFIX_DETAILED, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_TAG, PREFIX_NOK, PREFIX_TEMPERATURE, PREFIX_ATTENDANCE);

        if (argMultimap.getValue(PREFIX_ADD).isPresent()) {
            return addCommand(argMultimap);
        } else if (argMultimap.getValue(PREFIX_DELETE).isPresent()) {
            return deleteCommand(argMultimap);
        } else if (argMultimap.getValue(PREFIX_EDIT).isPresent()) {
            return editCommand(argMultimap);
        } else if (argMultimap.getValue(PREFIX_FIND).isPresent()) {
            return findCommand(argMultimap);
        } else if (argMultimap.getValue(PREFIX_REFRESH).isPresent()) {
            return refreshCommand();
        } else if (argMultimap.getValue(PREFIX_DEFAULT).isPresent()) {
            return defaultDisplayCommand();
        } else if (argMultimap.getValue(PREFIX_DETAILED).isPresent()) {
            return detailedDisplayCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HELP_MESSAGE));
        }
    }

    /**
     * Returns a StudentAddCommand object for execution.
     * {@code ArgumentMultimap}.
     */
    private StudentAddCommand addCommand(ArgumentMultimap argMultimap) throws ParseException, CommandException {
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StudentAddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        NextOfKin nok = ParserUtil.parseNok(argMultimap.getValue(PREFIX_NOK).get());
        Temperature temperature = ParserUtil.parseTemperature(argMultimap.getValue(PREFIX_TEMPERATURE).get());
        Attendance attendance = ParserUtil.parseAttendance(argMultimap.getValue(PREFIX_ATTENDANCE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Student student = new Student(name, phone, email, address, temperature, attendance, nok,
                tagList);
        return new StudentAddCommand(student);
    }

    /**
     * Returns a StudentDeleteCommand object for execution.
     * {@code ArgumentMultimap}.
     */
    private StudentDeleteCommand deleteCommand(ArgumentMultimap argMultimap) throws ParseException, CommandException {
        try {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble(PREFIX_DELETE.getPrefix()));
            return new StudentDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, StudentDeleteCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns a StudentEditCommand object for execution.
     * {@code ArgumentMultimap}.
     */
    private StudentEditCommand editCommand(ArgumentMultimap argMultimap) throws ParseException, CommandException {
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble(PREFIX_EDIT.getPrefix()));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    StudentEditCommand.MESSAGE_USAGE), pe);
        }

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        if (argMultimap.getValueOptional(PREFIX_NAME).isPresent()) {
            editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValueOptional(PREFIX_PHONE).isPresent()) {
            editStudentDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValueOptional(PREFIX_EMAIL).isPresent()) {
            editStudentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValueOptional(PREFIX_ADDRESS).isPresent()) {
            editStudentDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValueOptional(PREFIX_TEMPERATURE).isPresent()) {
            editStudentDescriptor.setTemperature(ParserUtil.parseTemperature(argMultimap.getValue(PREFIX_TEMPERATURE)
                    .get()));
        }
        if (argMultimap.getValueOptional(PREFIX_ATTENDANCE).isPresent()) {
            editStudentDescriptor.setAttendance(ParserUtil.parseAttendance(argMultimap.getValue(PREFIX_ATTENDANCE)
                    .get()));
        }
        if (argMultimap.getValueOptional(PREFIX_NOK).isPresent()) {
            editStudentDescriptor.setNok(ParserUtil.parseNok(argMultimap.getValue(PREFIX_NOK)
                    .get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editStudentDescriptor::setTags);

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(StudentEditCommand.MESSAGE_NOT_EDITED);
        }

        return new StudentEditCommand(index, editStudentDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Returns a StudentFindCommand object for execution.
     * {@code ArgumentMultimap}.
     */
    private StudentFindCommand findCommand(ArgumentMultimap argMultimap) throws ParseException, CommandException {
        String trimmedArgs = argMultimap.getPreamble(PREFIX_FIND.getPrefix()).trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, StudentFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new StudentFindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

    /**
     * Returns a StudentRefreshCommand object for execution.
     * {@code ArgumentMultimap}.
     */
    private StudentRefreshCommand refreshCommand() throws ParseException, CommandException {
        return new StudentRefreshCommand();
    }

    /**
     * Returns a DefaultStudentDisplayCommand object for execution.
     * {@code ArgumentMultimap}.
     */
    private DefaultStudentDisplayCommand defaultDisplayCommand() throws ParseException, CommandException {
        return new DefaultStudentDisplayCommand();
    }

    /**
     * Returns a DetailedStudentDisplayCommand object for execution.
     * {@code ArgumentMultimap}.
     */
    private DetailedStudentDisplayCommand detailedDisplayCommand() throws ParseException, CommandException {
        return new DetailedStudentDisplayCommand();
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
