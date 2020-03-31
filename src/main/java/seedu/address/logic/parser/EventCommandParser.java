package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EVENT_DATETIME_RANGE;
import static seedu.address.commons.util.EventUtil.makeUniqueIdentifier;
import static seedu.address.commons.util.EventUtil.validateDateTime;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GET_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VIEW_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VIEW_MODE;
import static seedu.address.logic.parser.ParserUtil.parseColorCode;
import static seedu.address.logic.parser.ParserUtil.parseEventName;
import static seedu.address.logic.parser.ParserUtil.parseLocalDateTime;
import static seedu.address.logic.parser.ParserUtil.parseRecurrenceType;

import jfxtras.icalendarfx.components.VEvent;
import jfxtras.icalendarfx.properties.component.descriptive.Categories;
import jfxtras.icalendarfx.properties.component.recurrence.RecurrenceRule;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.EventAddCommand;
import seedu.address.logic.commands.event.EventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class EventCommandParser implements Parser<EventCommand> {
    public static final Logger logger = LogsCenter.getLogger(EventCommandParser.class);

    public static boolean arePrefixesPresent(ArgumentMultimap argMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argMultimap.getValue(prefix).isPresent());
    }

    public EventCommand parse (String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_EVENT_NAME,
                PREFIX_START_DATETIME,
                PREFIX_END_DATETIME,
                PREFIX_VIEW,
                PREFIX_COLOR,
                PREFIX_RECUR,
                PREFIX_GET_INDEX,
                PREFIX_VIEW_MODE,
                PREFIX_VIEW_DATE);
        boolean isEdit = false;
        Index index = Index.fromZeroBased(0);
        try {
            String preamble = argMultimap.getPreamble();

            if (!preamble.isBlank()) {
                index = ParserUtil.parseIndex(preamble);
                isEdit = true;
            }
        } catch (ParseException e) {
            logger.info("Parser unable to parse preamble index.");
            throw new ParseException("LOL");
        }
//        if (argMultimap.getValue(PREFIX_VIEW).isPresent()) {
//            return viewCommand(argMultimap);
//        } else if (argMultimap.getValue(PREFIX_DELETE).isPresent()) {
//            return deleteCommand(argMultimap);
//        } else if (argMultimap.getValue(PREFIX_GET_INDEX).isPresent()) {
//            return indexOfCommand(argMultimap);
//        } else if (isEdit) {
//            return editCommand(index, argMultimap);
//        } else {
            return addCommand(argMultimap);
        //}
    }

    public EventAddCommand addCommand(ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_EVENT_NAME, PREFIX_START_DATETIME, PREFIX_END_DATETIME, 
                PREFIX_RECUR, PREFIX_COLOR) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventAddCommand.MESSAGE_USAGE));
        }
        String eventName = argMultimap.getValue(PREFIX_EVENT_NAME).orElse("").trim();
        String startDateTimeString = argMultimap.getValue(PREFIX_START_DATETIME).orElse("").trim();
        String endDateTimeString = argMultimap.getValue(PREFIX_END_DATETIME).orElse("").trim();
        String recurType = argMultimap.getValue(PREFIX_RECUR).orElse("").trim();
        String colorNumber = argMultimap.getValue(PREFIX_COLOR).orElse("").trim();
        if (eventName.isBlank() || startDateTimeString.isBlank() || endDateTimeString.isBlank() || recurType.isBlank()
                || colorNumber.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventAddCommand.MESSAGE_USAGE));
        }
        eventName = parseEventName(eventName);
        LocalDateTime startDateTime = parseLocalDateTime(startDateTimeString);
        LocalDateTime endDateTime = parseLocalDateTime(endDateTimeString);
        RecurrenceRule recurrenceRule = parseRecurrenceType(recurType);
        List<Categories> colorCategory = parseColorCode(colorNumber);
        String uniqueIdentifier = makeUniqueIdentifier(eventName, startDateTimeString, endDateTimeString);
        if (!validateDateTime(startDateTime, endDateTime)) {
            throw new ParseException(MESSAGE_INVALID_EVENT_DATETIME_RANGE);
        }
        VEvent vEvent = new VEvent().withSummary(eventName).withDateTimeStart(startDateTime)
                .withDateTimeEnd(endDateTime).withRecurrenceRule(recurrenceRule).withCategories(colorCategory)
                .withUniqueIdentifier(uniqueIdentifier);

        return new EventAddCommand(vEvent);
    }

}
