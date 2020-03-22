package seedu.address.ui;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.student.Student;
import seedu.address.model.student.notes.Notes;


public class NotesCard extends UiPart<Region> {

    private static final String FXML = "NotesCard.fxml";

    public final Student student;

    @FXML
    private Label name;
    @FXML
    private Label noteId;
    @FXML
    private Label content;

    public NotesCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        noteId.setText("#1");
//        ArrayList<Notes> notes = student.getNotes();
//        String output = "";
//        for(Notes n : notes) {
//            output += n.getContent() + '\n';
//        }
        content.setText("Temp");

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NotesCard)) {
            return false;
        }

        // state check
        NotesCard card = (NotesCard) other;
        //needs to edit
        return true;
    }

}
