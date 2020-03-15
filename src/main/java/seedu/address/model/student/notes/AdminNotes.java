package seedu.address.model.student.notes;

/**
 * A class representing the AdminNotes feature.
 */
public class AdminNotes extends Notes {

    public AdminNotes(String student, String content) {
        super(student, content);
    }

    public AdminNotes setStudent(String newStudent) {
        return new AdminNotes(newStudent, this.getContent());
    }

    public AdminNotes setContent(String newContent) {
        return new AdminNotes(this.getStudent(), newContent);
    }

    @Override
    public String toString() {
        return "[AdminNotes]"
                + " Student:'" + getStudent() + '\''
                + ", Content: '" + getContent() + '\'';
    }

    /**
     * Driver function to test the functionality of StickyNotes
     * @param args
     */
    public static void main(String[] args) {
        AdminNotes s1 = new AdminNotes("Alex Yeoh", "Late for class today");
        System.out.println(s1);
    }
}
