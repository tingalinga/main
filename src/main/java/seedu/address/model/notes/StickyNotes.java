package seedu.address.model.notes;

/**
 * A class representing the StickyNotes feature.
 */
public class StickyNotes extends Notes {

    public StickyNotes(String student, String content) {
        super(student, content);
    }

    public StickyNotes setStudent(String newStudent) {
        return new StickyNotes(newStudent, this.getContent());
    }

    public StickyNotes setContent(String newContent) {
        return new StickyNotes(this.getStudent(), newContent);
    }

    @Override
    public String toString() {
        return "[StickyNotes]"
                + " Student:'" + getStudent() + '\''
                + ", Content: '" + getContent() + '\'';
    }

    /**
     * Driver function to test the functionality of StickyNotes
     * @param args
     */
    public static void main(String[] args) {
        StickyNotes s1 = new StickyNotes("Alex Yeoh", "Late for class today");
        System.out.println(s1);
    }
}
