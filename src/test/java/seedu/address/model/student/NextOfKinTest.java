package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class NextOfKinTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NextOfKin(null));
    }

    @Test
    public void constructor_invalidNok_throwsIllegalArgumentException() {
        String invalidNok = "";
        assertThrows(IllegalArgumentException.class, () -> new NextOfKin(invalidNok));
    }

    @Test
    public void isValidTNok() {
        // null temperatures
        assertThrows(NullPointerException.class, () -> NextOfKin.isValidNok(null));

        // invalid NOK
        assertFalse(NextOfKin.isValidNok("")); // empty string
        assertFalse(NextOfKin.isValidNok(" ")); // spaces only
        assertFalse(NextOfKin.isValidNok("nok")); // non-numeric
        assertFalse(NextOfKin.isValidNok("123")); //only numbers
        assertFalse(NextOfKin.isValidNok("jon-daddy-12345")); //wrong format

        // valid NOK
        assertTrue(NextOfKin.isValidNok("Insert NOK details here!")); // Insert comment allowed
        assertTrue(NextOfKin.isValidNok("jon-father-12345")); // correct format
        assertTrue(NextOfKin.isValidNok("mary-mother-523423")); // correct format
        assertTrue(NextOfKin.isValidNok("dan-brother-24252")); // correct format
        assertTrue(NextOfKin.isValidNok("jen-sister-12656")); // correct format
        assertTrue(NextOfKin.isValidNok("ahma-grandmother-745456")); // correct format
        assertTrue(NextOfKin.isValidNok("ahgong-grandfather-75634")); // correct format
    }

    @Test
    public void isSameNok() {
        NextOfKin nokOne = new NextOfKin("dawn-sister-63452");
        assertEquals(nokOne, new NextOfKin("dawn-sister-63452"));
        assertFalse(nokOne.equals(new NextOfKin("ahma-grandmother-745456")));
        assertEquals(nokOne.hashCode(), new NextOfKin("dawn-sister-63452").hashCode());
        assertFalse(nokOne.hashCode() == new NextOfKin("ahma-grandmother-745456").hashCode());
    }
}
