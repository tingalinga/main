package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import jfxtras.icalendarfx.VCalendar;
import jfxtras.icalendarfx.components.VEvent;
import jfxtras.internal.scene.control.skin.agenda.AgendaWeekSkin;
import jfxtras.scene.control.agenda.icalendar.ICalendarAgenda;
import seedu.address.commons.core.LogsCenter;

import javax.swing.plaf.synth.Region;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.logging.Logger;

public class SchedulePanel extends UiPart<Region> {
    private static final String FXML = "SchedulePanel.fxml";
    private static final Locale UK = Locale.UK;
    private ICalendarAgenda agenda;
    private VCalendar vCalendar;
    private final Logger logger = LogsCenter.getLogger(SchedulePanel.class);

    @FXML
    private BorderPane borderPane;

    public SchedulePanel(ObservableList<VEvent> list) {
        super(FXML);
        this.vCalendar = new VCalendar();
        vCalendar.setVEvents(list);
        this.agenda = new ICalendarAgenda();
        init(this.agenda);
        borderPane.setCenter(agenda);
        borderPane.setMaxWidth(Double.MAX_VALUE);
    }

    public void init(ICalendarAgenda agenda) {
        agenda.setLocale(UK);
        AgendaWeekSkin weekSkin = new AgendaWeekSkin(this.agenda);
        agenda.setSkin(weekSkin);
        setSettings(this.agenda);
    }

    /**
     * Configures settings for icalendar
     */
    public void setSettings(ICalendarAgenda agenda) {
        agenda.setAllowResize(false);
        agenda.setAllowDragging(false);
        agenda.setActionCallback(null);
        agenda.setOnMouseClicked(null);
        agenda.setOnMousePressed(null);
        agenda.setOnTouchPressed(null);
        agenda.setEditAppointmentCallback(null);
        agenda.setAppointmentChangedCallback(null);
        agenda.setSelectedOneAppointmentCallback(null);

    }

    /**
     * Updates scheduler
     */
    public void update() {
        this.agenda.updateAppointments();
    }

    /**
     * Changes panel to show time interval
     * @param localDateTime
     */
    public void setDisplayedDateTime(LocalDateTime localDateTime) {
        this.agenda.setDisplayedLocalDateTime(localDateTime);
    }



}
