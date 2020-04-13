package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.EventScheduleView;

/**
 * EventViewCommand represents the view command to view events in schedule in different modes.
 */
public class EventViewCommand extends EventCommand {

    public static final String MESSAGE_USAGE = "This command changes the view mode of your schedule.\n"
            + "Format: schedule view mode/SCHEDULE_MODE (daily,weekly) date/YYYY-MM-DD\n"
            + "Example: schedule view mode/weekly date/2020-04-05";
    public static final String MESSAGE_VIEW_SUCCESS = "Showing your %s schedule on reference date %s";

    private LocalDateTime targetViewDateTime;
    private EventScheduleView scheduleViewMode;

    public EventViewCommand() {

    }

    public void setViewMode(EventScheduleView scheduleViewMode) {
        requireNonNull(scheduleViewMode);

        this.scheduleViewMode = scheduleViewMode;
    }

    public void setTargetViewDate(LocalDateTime targetViewDateTime) {
        requireNonNull(targetViewDateTime);

        this.targetViewDateTime = targetViewDateTime;
    }

    /**
     * Executes this command which returns a commandResult for UI to act on
     * @param model {@code Model} which the command should operate on.
     * @return returns a commandResult of type SHOW_SCHEDULE
     * @throws CommandException for invalid view modes or invalid date format
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.setEventScheduleView(scheduleViewMode);
        model.setEventScheduleLocalDateTime(targetViewDateTime);
        return new CommandResult(generateSuccessMessage(model.getEventScheduleView(),
                model.getEventScheduleLocalDateTime()));
    }

    /**
     * Generates the success message viewing event schedule
     * @param eventScheduleViewMode the type of view mode the schedule is in
     * @param targetViewDate the target date the schedule is referencing
     * @return a success message string to be shown to the user
     */
    private String generateSuccessMessage(EventScheduleView eventScheduleViewMode, LocalDateTime targetViewDate) {
        return String.format(MESSAGE_VIEW_SUCCESS, eventScheduleViewMode.name().toLowerCase(),
                targetViewDate.toLocalDate().toString());
    }
}
