package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;

public class NotesPanel extends UiPart<Region> {
    private static final String FXML = "NotesPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(NotesPanel.class);

    @FXML
    private ListView<Student> notesView;

    public NotesPanel(ObservableList<Student> studentList) {
        super(FXML);
        notesView.setItems(studentList);
        notesView.setCellFactory(view -> new StudentListViewCell());
    }

    class NotesViewCell extends ListCell<Student> {
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new NotesCard(student, getIndex() + 1).getRoot());
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
