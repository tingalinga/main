package seedu.address.ui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import seedu.address.model.notes.Notes;
import seedu.address.model.student.Student;

/**
 * NotesManager class which handles the Notes feature's UI
 */
public class NotesManager {
    private final ObservableList<Student> studentList;

    /**
     * Constructor of NotesManager
     * @param studentList
     */
    public NotesManager(ObservableList<Student> studentList) {
        this.studentList = studentList;
    }

    /**
     * Function which saves the notes to a .txt file is user's current directory.
     */
    public void saveToTxt() {
        String filePath = "/Users/gerrenseow/Documents/Gerren/MODULES/Y2S2/CS2103T/Team_Project/main/"
                + "data/studentNotes.txt";
        ArrayList<Notes> allNotes = new ArrayList<>();
        for (Student student : studentList) {
            allNotes.addAll(student.getNotes());
        }
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            String toBeSaved = "";
            toBeSaved += "Hello Teacher, here are the notes you have saved in TeaPet! :) \n";
            toBeSaved += "_______________________________________________________________ \n";
            for (Notes note : allNotes) {
                toBeSaved += "Student: " + note.getStudent() + "\n";
                toBeSaved += "Added on: " + note.getDateTime() + "\n";
                toBeSaved += "Content: " + note.getContent() + "\n";
                toBeSaved += "\n_______________________________________________________________ \n";
            }

            writer.write(toBeSaved);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }



    }


}
