package seedu.address.ui;

import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;
import seedu.address.model.student.notes.Notes;

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

    public ArrayList<Notes> getAllNotes(ObservableList<Student> studentList) {
        ArrayList<Notes> allNotes = new ArrayList<>();
        for (Student student : studentList) {
            allNotes.addAll(student.getNotes());
        }
        return allNotes;
    }

    class NotesViewCell extends ListCell<Notes> {
        @Override
        protected void updateItem(Notes note, boolean empty) {
            super.updateItem(note, empty);

            if (empty || note == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new NotesCard(note).getRoot());
            }
        }
    }

    class StudentListViewCell extends ListCell<Student> {
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StudentCard(student, getIndex() + 1).getRoot());
            }
        }
    }
}
