package seedu.address.testutil.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.EventUtil.isEqualEvent;

import jfxtras.icalendarfx.components.VEvent;
import seedu.address.testutil.ModelStub;


/**
 * Model stub which holds one vevent
 */
public class ModelStubWithVEvent extends ModelStub {
    private final VEvent vEvent;

    public ModelStubWithVEvent(VEvent vEvent) {
        requireNonNull(vEvent);
        this.vEvent = vEvent;
    }

    @Override
    public boolean hasVEvent(VEvent other) {
        requireNonNull(other);
        return isEqualEvent(this.vEvent, other);
    }
}
