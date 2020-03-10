package seedu.address.model.notes;

public abstract class Notes {
    private final String student;
    private final String content;

    public Notes(String student, String content) {
        this.student = student;
        this.content = content;
    }

    public String getStudent() {
        return student;
    }

    public String getContent() {
        return content;
    }

    public abstract Notes setStudent(String student);

    public abstract Notes setContent(String content);

}


