package seedu.address.ui.event;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import jfxtras.icalendarfx.VCalendar;

import seedu.address.model.event.ReadOnlyVEvents;

/**
 * Represents an EventExporter which exports events in schedule into a ICS file type.
 */
public class EventExporter {
    private static final String EXPORT_DIRECTORY_PATH = "data/mySchedule.ics";
    private final ReadOnlyVEvents vEvents;

    /**
     * Constructor of EventExporter
     * @param vEvents current events in the schedule
     */
    public EventExporter(ReadOnlyVEvents vEvents) {
        this.vEvents = vEvents;
    }

    /**
     * Exports current schedule into a ics file type. This will replace any existing .ics files that has the
     * same absolute file path in data/mySchedule.ics
     */

    public void saveToIcs() {
        try {
            FileWriter fw = new FileWriter(EXPORT_DIRECTORY_PATH);
            BufferedWriter bw = new BufferedWriter(fw);

            String fileBody = generateIcsFileContent(vEvents);

            bw.write(fileBody);
            bw.close();

            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to generate ICS File content given ReadOnlyVEvents. Method is backed by VCalendar from jfxtras library.
     * @param vEventToBeExported ReadOnlyVEvents to be exported
     * @return String representation of the content to be exported.
     */
    private String generateIcsFileContent(ReadOnlyVEvents vEventToBeExported) {
        VCalendar vCalendar = new VCalendar();
        vCalendar.setVEvents(vEventToBeExported.getVEvents());
        String fileBody = vCalendar.toString().replaceAll("\n", "\r\n");
        return fileBody;
    }
}
