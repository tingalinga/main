package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.event.TypicalVEvents.VEVENT1;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.event.exceptions.DuplicateVEventException;




public class EventHistoryTest {
    private final EventHistory eventHistory = new EventHistory();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), eventHistory.getEvents());
    }

    @Test
    public void contains_nullVEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventHistory.contains(null));
    }

    @Test
    public void contains_vEventAbsentFromEventHistory_assertFalse() {
        assertFalse(eventHistory.contains(VEVENT1));
    }

    @Test
    public void contains_vEventPresentFromEventHistory_assertTrue() {
        eventHistory.addVEvent(VEVENT1);
        assertTrue(eventHistory.contains(VEVENT1));
    }

    @Test
    public void add_nullVEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventHistory.addVEvent(null));
    }

    @Test
    public void add_duplicateVEvent_throwsDuplicateVEventException() {
        eventHistory.addVEvent(VEVENT1);
        assertThrows(DuplicateVEventException.class, () -> eventHistory.addVEvent(VEVENT1));
    }

    @Test
    public void delete_nullVEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventHistory.deleteVEvent(null));
    }

    @Test
    public void delete_existingVEvent_removesVEvent() {
        eventHistory.addVEvent(VEVENT1);
        eventHistory.deleteVEvent(Index.fromZeroBased(0));
        EventHistory unchanged = new EventHistory();
        assertEquals(unchanged, eventHistory);
    }

}
