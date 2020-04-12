package seedu.address.logic.commands.event;

import static seedu.address.commons.util.EventUtil.formatIndexVEventPair;

import java.util.List;

import jfxtras.icalendarfx.components.VEvent;

import org.apache.commons.math3.util.Pair;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * EventIndexAllCommand represent the indexAll command to obtain all indexes of events in the schedule.
 */
public class EventIndexAllCommand extends EventCommand {

    public static final String MESSAGE_USAGE = "This command gets all indexes of events in the scheduler.\n"
            + "Format: schedule indexAll\n"
            + "Example: schedule indexAll";
    public static final String MESSAGE_NO_EVENT = "Currently no events in TeaPet.";
    public static final String MESSAGE_SUCCESS = "These are all the events in your scheduler:\n"
            + "%1$s";

    /**
     * Get all of the event summaries in the scheduler
     */
    public EventIndexAllCommand() {

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Pair<Index, VEvent>> resultVEventIndexList = model.getAllVEventsWithIndex();
        if (resultVEventIndexList.isEmpty()) {
            return new CommandResult(MESSAGE_NO_EVENT);
        } else {
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


}

