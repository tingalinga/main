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
import seedu.address.model.notes.Notes;
import seedu.address.model.student.Student;


/**
 * Represents a Controller class for NotesPanel fxml
 */
public class NotesPanel extends UiPart<Region> {
    private static final String FXML = "NotesPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(NotesPanel.class);

    @FXML
    private ListView<Notes> notesView;

    /**
     * Constuctor of NotesPanel, with an input of list of students.
     * @param studentList
     */
    public NotesPanel(ObservableList<Student> studentList) {
        super(FXML);
        notesView.setItems(FXCollections.observableArrayList(getAllNotes(studentList)));
        notesView.setCellFactory(view -> new NotesViewCell());
    }

    /**
     * Overloaded Constructor, with input of list of students as well as CommandResult's string content.
     * @param studentList
     * @param result
     */
    public NotesPanel(ObservableList<Student> studentList, String result) {
        super(FXML);
        notesView.setItems(FXCollections.observableArrayList(getAllFilteredNotes(studentList, result)));
        notesView.setCellFactory(view -> new NotesViewCell());
    }

    /**
     * Method which obtains all the Notes from individual students.
     * @param studentList
     * @return ArrayList of Notes
     */
    public ArrayList<Notes> getAllNotes(ObservableList<Student> studentList) {
        ArrayList<Notes> allNotes = new ArrayList<>();
        for (Student student : studentList) {
            allNotes.addAll(student.getNotes());
        }
        return allNotes;
    }

    /**
     * Method which obtains all the Notes from individual students, which contain specific keywords
     * requested by the User.
     * @param studentList
     * @param result
     * @return ArrayList of Notes
     */
    public ArrayList<Notes> getAllFilteredNotes(ObservableList<Student> studentList, String result) {
        // Process to obtain an array representation of all keywords.
        String preProcessedKeywords = result.split(":")[1].trim();
        String[] keywords = preProcessedKeywords.substring(1, preProcessedKeywords.length() - 1).split(",");
        for (int i = 0; i < keywords.length; i++) {
            keywords[i] = keywords[i].trim().toLowerCase();
        }

        // Obtains the ArrayList containing all notes currently stored
        ArrayList<Notes> allNotes = new ArrayList<>();
        for (Student student : studentList) {
            allNotes.addAll(student.getNotes());
        }

        // Filters the list of notes according to the presence of keywords.
        ArrayList<Notes> filteredNotes = new ArrayList<>();
        for (Notes note : allNotes) {
            for (String keyword : keywords) {
                if (note.getContent().toLowerCase().contains(keyword)
                        || note.getDateTime().toLowerCase().contains(keyword)
                        || note.getStudent().toLowerCase().contains(keyword)
                        && !filteredNotes.contains(note)) {
                    filteredNotes.add(note);
                    break;
                }
            }
        }

        return filteredNotes;
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
