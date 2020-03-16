package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Remark2Test {

    @Test
    public void equals() {
        Remark2 remark2 = new Remark2("Hello");

        // same object -> returns true
        assertTrue(remark2.equals(remark2));

        // same values -> returns true
        Remark remarkCopy = new Remark(remark2.value);
        assertTrue(remark2.equals(remarkCopy));

        // different types -> returns false
        assertFalse(remark2.equals(1));

        // null -> returns false
        assertFalse(remark2.equals(null));

        // different remark -> returns false
        Remark differentRemark = new Remark("Bye");
        assertFalse(remark2.equals(differentRemark));
    }
}