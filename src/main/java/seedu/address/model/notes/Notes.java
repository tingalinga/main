package seedu.address.model.notes;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * A class representing the Notes feature.
 */
public class Notes {

    public static final String MESSAGE_CONSTRAINTS =
            "Student's name should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String student;
    private final String content;

    /**
     * Notes constructor
     * @param student, representing the name of student.
     * @param content, representing the content to be stored in the note.
     */
    public Notes(String student, String content) {
        requireNonNull(student, content);
        checkArgument(isValidName(student), MESSAGE_CONSTRAINTS);
        this.student = student;
        this.content = content;
    }

    /**
     * Getter of String student
     * @return the student's name.
     */
    public String getStudent() {
        return this.student;
    }

    /**
     * Getter of String content
     * @return the note's content.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Setter of String student
     * @param newStudent
     * @return a new Notes object with updated student.
     */
    public Notes setStudent(String newStudent) {
        return new Notes(newStudent, this.getContent());
    }

    /**
     * Setter of String content
     * @param newContent
     * @return a new Notes object with updated note content.
     */
    public Notes setContent(String newContent) {
        return new Notes(this.getStudent(), newContent);
    }

    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "[Notes]"
                + " Student:'" + getStudent() + '\''
                + ", Content: '" + getContent() + '\'';
    }

    /**
     * Driver function to test the functionality of StickyNotes
     * @param args
     */
    public static void main(String[] args) {
        Notes s1 = new Notes("Alex Yeoh", "Late for class today");
        System.out.println(s1);
    }
}
