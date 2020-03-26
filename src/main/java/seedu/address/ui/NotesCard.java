package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.notes.Notes;

/**
 * Controller for NotesCard fxml
 */
public class NotesCard extends UiPart<Region> {

    private static final String FXML = "NotesCard.fxml";

    public final Notes note;

    @FXML
    private Label name;
    @FXML
    private Label noteId;
    @FXML
    private Label content;

    public NotesCard(Notes note) {
        super(FXML);
        this.note = note;
        name.setText(note.getStudent());
        noteId.setText("#1");
        content.setText(note.getContent());

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
