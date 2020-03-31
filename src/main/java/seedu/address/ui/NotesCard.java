package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.notes.Notes;

/**
 * Represents a Controller for NotesCard fxml.
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
    @FXML
    private Label dateTime;

    /**
     * Constructor for NotesCard
     * @param note, with student's name, date added and content
     * @param displayedIndex, the Note's position in the ListView
     */
    public NotesCard(Notes note, int displayedIndex) {
        super(FXML);
        this.note = note;
        name.setText("Student: " + note.getStudent());
        noteId.setText("Note Index: #" + displayedIndex);
        content.setText("\n" + note.getContent());
        dateTime.setText("Added on: " + note.getDateTime());

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
