package seedu.address.logic.commands.event;

import static seedu.address.commons.util.EventUtil.formatIndexVEventPair;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.event.TypicalEvents.getTypicalEventHistory;
import static seedu.address.testutil.event.TypicalVEvents.VEVENT1;
import static seedu.address.testutil.event.TypicalVEvents.VEVENT2;
import static seedu.address.testutil.event.TypicalVEvents.VEVENT3;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Pair;
import org.junit.jupiter.api.Test;

import jfxtras.icalendarfx.components.VEvent;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

class EventIndexAllCommandTest {

    private Model model = new ModelManager();

    @Test
    public void execute_validIndexAll_success() {

        Model expectedModel = new ModelManager();
        expectedModel.setEventHistory(getTypicalEventHistory());


        String actualVEventOne = formatIndexVEventPair(new Pair<Index, VEvent>(Index.fromOneBased(1), VEVENT1));
        String actualVEventTwo = formatIndexVEventPair(new Pair<Index, VEvent>(Index.fromOneBased(2), VEVENT2));
        String actualVEventThree = formatIndexVEventPair(new Pair<Index, VEvent>(Index.fromOneBased(3), VEVENT3));
        List<String> list = new ArrayList<String>();
        list.add(actualVEventOne);
        list.add(actualVEventTwo);
        list.add(actualVEventThree);

        model.addVEvent(VEVENT1);
        model.addVEvent(VEVENT2);
        model.addVEvent(VEVENT3);

        StringBuilder resultStringBuilder = new StringBuilder();
        for (String indexVEventPairSummary : list) {
            resultStringBuilder.append(indexVEventPairSummary);
        }
        assertCommandSuccess(new EventIndexAllCommand(), model,
            new CommandResult(String.format(EventIndexAllCommand.MESSAGE_SUCCESS,
            resultStringBuilder.toString())), expectedModel);

    }
}
