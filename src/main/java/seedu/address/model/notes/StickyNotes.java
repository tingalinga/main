package seedu.address.model.notes;

public class StickyNotes {
    private final String student;
    private final String content;

    public StickyNotes(String student, String content) {
        this.student = student;
        this.content = content;
    }

    public String getStudent() {
        return student;
    }

    public String getContent() {
        return content;
    }

    public StickyNotes setStudent(String newStudent) {
        return new StickyNotes(newStudent, this.content);
    }

    public StickyNotes setContent(String newContent) {
        return new StickyNotes(this.student, newContent);
    }

    @Override
    public String toString() {
        return "StickyNotes{" +
                "student='" + student + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public static void main(String[] args) {
        StickyNotes s1 = new StickyNotes("Alex Yeoh", "Late for class today");
        System.out.println(s1);
    }
}
