package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.notes.Notes;
import seedu.address.model.student.Student;


/**
 * Controller class for NotesPanel fxml
 */
public class NotesPanel extends UiPart<Region> {
    private static final String FXML = "NotesPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(NotesPanel.class);

    @FXML
    private ListView<Notes> notesView;

    public NotesPanel(ObservableList<Student> studentList) {
        super(FXML);
        notesView.setItems(FXCollections.observableArrayList(getAllNotes(studentList)));
        notesView.setCellFactory(view -> new NotesViewCell());
    }

    public NotesPanel(ObservableList<Student> studentList, String result) {
        super(FXML);
        notesView.setItems(FXCollections.observableArrayList(getAllFilteredNotes(studentList, result)));
        notesView.setCellFactory(view -> new NotesViewCell());    }

    public ArrayList<Notes> getAllNotes(ObservableList<Student> studentList) {
        ArrayList<Notes> allNotes = new ArrayList<>();
        for (Student student : studentList) {
            allNotes.addAll(student.getNotes());
        }
        return allNotes;
    }

    public ArrayList<Notes> getAllFilteredNotes(ObservableList<Student> studentList, String result) {
        String preProcessedKeywords = result.split(":")[1].trim();
        String[] keywords = preProcessedKeywords.substring(1, preProcessedKeywords.length()-1).split(",");
        for (String keyword : keywords) {
            keyword = keyword.trim().toLowerCase();
        }

        ArrayList<Notes> allNotes = new ArrayList<>();
        for (Student student : studentList) {
            ArrayList<Notes> studentNotes = student.getNotes();
            for (Notes note : studentNotes) {
                for (String keyword : keywords) {
                    if (note.getContent().toLowerCase().contains(keyword)
                    || note.getDateTime().toLowerCase().contains(keyword)
                    || note.getStudent().toLowerCase().contains(keyword)) {
                        allNotes.add(note);
                        break;
                    }
                }
            }
        }
        return allNotes;
    }

    /**
     * Displays the note graphics
     */
    class NotesViewCell extends ListCell<Notes> {
        @Override
        protected void updateItem(Notes note, boolean empty) {
            super.updateItem(note, empty);

            if (empty || note == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new NotesCard(note, getIndex() + 1).getRoot());
            }
        }
    }


}
