package seedu.address.model.notes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A class representing the Notes feature.
 */
public class Notes {

    public static final String MESSAGE_CONSTRAINTS =
            "Student's name should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String student;
    private final String content;
    private final String dateTime;

    /**
     * Notes constructor
     * @param student, representing the name of student.
     * @param content, representing the content to be stored in the note.
     */
    public Notes(String student, String content) {
        requireAllNonNull(student, content);
        checkArgument(isValidName(student), MESSAGE_CONSTRAINTS);
        this.student = student;
        this.content = content;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        this.dateTime = formatter.format(date).toString();
    }

    /**
     * Overloaded Notes constructor which is used when Json data is drawn from addressbook.json
     * This allows initial timestamp to be immutable
     * @param student
     * @param content
     * @param dateTime
     */
    public Notes(String student, String content, String dateTime) {
        requireAllNonNull(student, content, dateTime);
        checkArgument(isValidName(student), MESSAGE_CONSTRAINTS);
        this.student = student;
        this.content = content;
        this.dateTime = dateTime;
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
     * Getter of Note's timestamp
     * @return note's timestamp.
     */
    public String getDateTime() {
        return this.dateTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notes notes = (Notes) o;
        return Objects.equals(student, notes.student) &&
                Objects.equals(content, notes.content) &&
                Objects.equals(dateTime, notes.dateTime);
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
