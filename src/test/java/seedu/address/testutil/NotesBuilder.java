package seedu.address.testutil;

import seedu.address.model.notes.Notes;

/**
 * A utility class to help with building Notes objects.
 */
public class NotesBuilder {

    public static final String DEFAULT_STUDENT = "Jane Khoo";
    public static final String DEFAULT_CONTENT = "She is a good student.";
    public static final String DEFUALT_PRIORITY = "LOW";
    public static final String DEFAULT_DATETIME = "29/03/2020 22:40";

    private final String student;
    private final String content;
    private final String priority;
    private final String dateTime;

    /**
     * Default Constructor
     */
    public NotesBuilder() {
        this.student = DEFAULT_STUDENT;
        this.content = DEFAULT_CONTENT;
        this.priority = DEFUALT_PRIORITY;
        this.dateTime = DEFAULT_DATETIME;
    }

    /**
     * Constructor which takes another Notes object as input.
     * @param noteToCopy
     */
    public NotesBuilder(Notes noteToCopy) {
        this.student = noteToCopy.getStudent();
        this.content = noteToCopy.getContent();
        this.priority = noteToCopy.getPriority();
        this.dateTime = noteToCopy.getDateTime();
    }

    /**
     * Builds a Notes object.
     */
    public Notes build() {
        return new Notes(student, content, priority, dateTime);
    }


}
