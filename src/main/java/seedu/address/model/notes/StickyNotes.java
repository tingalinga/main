package seedu.address.model.notes;

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
        return "StickyNotes{" +
                "student='" + getStudent() + '\'' +
                ", content='" + getContent() + '\'' +
                '}';
    }

    public static void main(String[] args) {
        StickyNotes s1 = new StickyNotes("Alex Yeoh", "Late for class today");
        System.out.println(s1);
    }
}
