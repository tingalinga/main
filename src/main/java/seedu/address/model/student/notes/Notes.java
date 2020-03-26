package seedu.address.model.student.notes;

/**
 * A class representing the Notes feature.
 */
public class Notes {
    private final String student;
    private final String content;

    public Notes(String student, String content) {
        this.student = student;
        this.content = content;
    }

    public String getStudent() {
        return this.student;
    }

    public String getContent() {
        return this.content;
    }

    public Notes setStudent(String newStudent) {
        return new Notes(newStudent, this.getContent());
    }

    public Notes setContent(String newContent) {
        return new Notes(this.getStudent(), newContent);
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
