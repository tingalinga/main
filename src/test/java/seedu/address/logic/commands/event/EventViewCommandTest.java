package seedu.address.logic.commands.event;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.event.EventViewCommand.MESSAGE_VIEW_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.event.EventScheduleView;

/**
 * Test for EventViewCommand.
 */
public class EventViewCommandTest {
    private static final EventScheduleView VALID_EVENT_SCHEDULE_VIEW = EventScheduleView.DAILY;
    private static final LocalDateTime VALID_TARGET_VIEW_DATE_TIME = LocalDateTime.parse("2020-04-11T03:00");

    private Model model = new ModelManager();

    @Test
    public void execute_validDateTimeAndViewMode_success() {
        Model expectedModel = new ModelManager();
        expectedModel.setEventScheduleLocalDateTime(VALID_TARGET_VIEW_DATE_TIME);
        expectedModel.setEventScheduleView(VALID_EVENT_SCHEDULE_VIEW);

        EventViewCommand eventViewCommand = new EventViewCommand();
        eventViewCommand.setViewMode(VALID_EVENT_SCHEDULE_VIEW);
        eventViewCommand.setTargetViewDate(VALID_TARGET_VIEW_DATE_TIME);

        String expectedResult = String.format(MESSAGE_VIEW_SUCCESS,
                VALID_EVENT_SCHEDULE_VIEW.name().toLowerCase(),
                VALID_TARGET_VIEW_DATE_TIME.toLocalDate().toString());

        assertCommandSuccess(eventViewCommand, model,
                new CommandResult(expectedResult), expectedModel);
    }

    @Test
    public void setTargetViewDate_nullTargetViewDateTime_nullPointerExceptionThrown() {
        EventViewCommand eventViewCommand = new EventViewCommand();
        assertThrows(NullPointerException.class, () -> eventViewCommand.setTargetViewDate(null));
    }

    @Test
    public void setDesiredViewMode_nullEventScheduleView_nullPointerExceptionThrown() {
        EventViewCommand eventViewCommand = new EventViewCommand();
        assertThrows(NullPointerException.class, () -> eventViewCommand.setViewMode(null));
    }
}