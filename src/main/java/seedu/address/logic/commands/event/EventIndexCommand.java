package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.EventUtil.formatIndexVEventPair;
import static seedu.address.commons.util.EventUtil.vEventToEventMapper;

import java.time.LocalDateTime;
import java.util.List;

import jfxtras.icalendarfx.components.VEvent;
import jfxtras.icalendarfx.utilities.Pair;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.exceptions.VEventNotFoundException;

/**
 * EventIndexCommand represent the indexGet command to obtain the index of the event.
 */
public class EventIndexCommand extends EventCommand {

    public static final String MESSAGE_USAGE = "This command gets the index of the specified event.\n"
            + "Format: schedule indexGet/EVENT_DESCRIPTION\n"
            + "Example: schedule indexGet/cs2103t lecture";
    public static final String MESSAGE_NO_EVENT = "Currently no events in TeaPet.";
    public static final String MESSAGE_SUGGESTION_EVENT =
            "Could not find specified event. This is the closest event we can find based on what you've entered:\n"
                    +"%1$s";
    public static final String MESSAGE_SUCCESS = "Event found:\n"
                    +"%1$s";
    private final String eventName;

    /**
     * Get the index of event with the eventName.
     *
     * @param eventName name of event in schedule.
     */
    public EventIndexCommand(String eventName) {
        requireNonNull(eventName);

        this.eventName = eventName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Pair<Index, VEvent>> resultVEventIndexList = model.searchVEvents(eventName);
        if (resultVEventIndexList.isEmpty()) {
            try {
                Pair<Index, VEvent> suggestedEventPair = model.searchMostSimilarVEventName(eventName);
                VEvent vEventToIndex = suggestedEventPair.getValue();
                Event event = vEventToEventMapper(vEventToIndex);
                LocalDateTime ldt = event.getStartDateTime();
                model.setEventScheduleLocalDateTime(ldt);
                return new CommandResult(generateSuggestionMessage(suggestedEventPair));

            } catch (VEventNotFoundException ex) {
                throw new CommandException(MESSAGE_NO_EVENT, ex);
            }

        } else {
            VEvent vEventToIndex = resultVEventIndexList.get(0).getValue();
            Event event = vEventToEventMapper(vEventToIndex);
            LocalDateTime ldt = event.getStartDateTime();
            model.setEventScheduleLocalDateTime(ldt);
            return new CommandResult(generateResultMessage(resultVEventIndexList));
        }
    }


    /**
     * Generates a result VEvent List success message with String type. The success message shows the correctly matched
     * Index, VEvent, Start Datetime, End Datetime.
     *
     * @param pairVEventIndexList a list of pair of VEvents and their indexes which have the same eventName.
     */
    private String generateResultMessage(List<Pair<Index, VEvent>> pairVEventIndexList) {
        StringBuilder resultStringBuilder = new StringBuilder();
        for (Pair<Index, VEvent> indexVEventPair : pairVEventIndexList) {
            resultStringBuilder.append(formatIndexVEventPair(indexVEventPair));
        }
        return String.format(MESSAGE_SUCCESS, resultStringBuilder.toString());
    }

    /**
     * Generates a result VEvent suggestion message. The suggestion message shows the roughly matched
     * Index, VEvent, StartDatetime, End Datetime.
     *
     * @param resultVEventPair the index, vEvent pair that the user sees.
     */
    private String generateSuggestionMessage(Pair<Index, VEvent> resultVEventPair) {
        return String.format(MESSAGE_SUGGESTION_EVENT, formatIndexVEventPair(resultVEventPair));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventIndexCommand)) {
            return false;
        }

        // state check
        EventIndexCommand e = (EventIndexCommand) other;
        return eventName.equals(e.eventName);
    }
}
