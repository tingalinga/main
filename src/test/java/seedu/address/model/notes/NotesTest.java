package seedu.address.model.notes;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

//Test for Notes.java
public class NotesTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Notes(null, null, null));
        assertThrows(NullPointerException.class, () -> new Notes(null, null,
                null, null));
    }

    @Test
    public void constructor_invalidNotes_throwsIllegalArgumentException() {
        String student = "";
        String content = "Test Content";
        String priority = "LOW";
        assertThrows(IllegalArgumentException.class, () -> new Notes(student, content, priority));
        assertThrows(IllegalArgumentException.class, () -> new Notes(student, "", priority));
        assertThrows(IllegalArgumentException.class, () -> new Notes(student, "", ""));
    }

    @Test
    public void isValidName() {
        assertThrows(NullPointerException.class, () -> Notes.isValidName(null));
        assertFalse(Notes.isValidName("James.Wong."));
        assertFalse(Notes.isValidName("Alice!!"));
        assertFalse(Notes.isValidName("Phua Lai Wee #455"));
        assertFalse(Notes.isValidName("Jane_Koh"));
        assertFalse(Notes.isValidName("Mr Chan C. W."));
        assertFalse(Notes.isValidName(" "));
        assertFalse(Notes.isValidName(""));

        assertTrue(Notes.isValidName("James Wong"));
        assertTrue(Notes.isValidName("Alice"));
        assertTrue(Notes.isValidName("Phua Lai Wee"));
        assertTrue(Notes.isValidName("Jane Koh"));
        assertTrue(Notes.isValidName("Mr Chan Chen Woo"));
    }

    @Test
    public void isSameNote() {
        assertFalse(new Notes("James Wong", "He is an excellent student", "MEDIUM")
                .isSameNote(new Notes("James Wong", "He is an excellent student", "LOW")));

        assertFalse(new Notes("James Wong", "He is an excellent student!", "MEDIUM")
                .isSameNote(new Notes("James Wong", "He is an excellent student.", "MEDIUM")));

        assertFalse(new Notes("James Wong Wei Kang", "He is an excellent student", "MEDIUM")
                .isSameNote(new Notes("James Wong", "He is an excellent student", "MEDIUM")));

        assertFalse(new Notes("James Wong", "He is an excellent student",
                "MEDIUM", "29/03/2020 22:40")
                .isSameNote(new Notes("James Wong", "He is an excellent student",
                "LOW", "29/03/2020 22:40")));

        assertTrue(new Notes("Jane Khoo", "She is an excellent student", "MEDIUM")
                .isSameNote(new Notes("Jane Khoo", "She is an excellent student", "MEDIUM")));

        assertTrue(new Notes("Jane Khoo", "She is an excellent student", "LOW")
                .isSameNote(new Notes("Jane Khoo", "She is an excellent student", "LOW")));

        assertTrue(new Notes("Jane Khoo", "She was rude towards her form teacher today.", "HIGH")
                .isSameNote(new Notes("Jane Khoo", "She was rude towards her form teacher today.",
                "HIGH")));

        assertTrue(new Notes("Jane Khoo", "She is an excellent student",
                "MEDIUM", "29/03/2020 22:40")
                .isSameNote(new Notes("Jane Khoo", "She is an excellent student",
                "MEDIUM", "29/03/2020 22:40")));

        //Different timestamps but similar in every other field.
        assertTrue(new Notes("Jane Khoo", "She is an excellent student",
                "MEDIUM", "30/03/2020 10:40")
                .isSameNote(new Notes("Jane Khoo", "She is an excellent student",
                "MEDIUM", "29/03/2020 22:40")));

    }

    @Test
    public void equals() {
        assertNotEquals(new Notes("James Wong", "He is an excellent student", "MEDIUM"),
                new Notes("James Wong", "He is an excellent student", "LOW"));

        assertNotEquals(new Notes("James Wong", "He is an excellent student!", "MEDIUM"),
                new Notes("James Wong", "He is an excellent student.", "MEDIUM"));

        assertNotEquals(new Notes("James Wong Wei Kang", "He is an excellent student",
                "MEDIUM"),
                new Notes("James Wong", "He is an excellent student", "MEDIUM"));

        assertNotEquals(new Notes("James Wong", "He is an excellent student",
                "MEDIUM", "29/03/2020 22:40"),
                new Notes("James Wong", "He is an excellent student",
                "LOW", "29/03/2020 22:40"));

        assertEquals(new Notes("Jane Khoo", "She is an excellent student", "MEDIUM"),
                new Notes("Jane Khoo", "She is an excellent student", "MEDIUM"));

        assertEquals(new Notes("Jane Khoo", "She is an excellent student", "LOW"),
                new Notes("Jane Khoo", "She is an excellent student", "LOW"));

        assertEquals(new Notes("Jane Khoo", "She was rude towards her form teacher today.",
                "HIGH"), new Notes("Jane Khoo", "She was rude towards her form teacher today.",
                "HIGH"));

        assertEquals(new Notes("Jane Khoo", "She is an excellent student",
                "MEDIUM", "29/03/2020 22:40"),
                new Notes("Jane Khoo", "She is an excellent student",
                "MEDIUM", "29/03/2020 22:40"));

        //Different timestamps but similar in every other field.
        assertEquals(new Notes("Jane Khoo", "She is an excellent student",
                "MEDIUM", "30/03/2020 10:40"),
                new Notes("Jane Khoo", "She is an excellent student",
                "MEDIUM", "29/03/2020 22:40"));

    }

}
