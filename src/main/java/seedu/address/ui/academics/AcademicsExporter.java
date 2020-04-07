package seedu.address.ui.academics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;

import seedu.address.model.academics.Assessment;
import seedu.address.model.academics.Submission;
/**
 * Represents a AcademicsExporter which handles the UI aspects of the Notes feature.
 */
public class AcademicsExporter {
    private final ObservableList<Assessment> assessmentList;

    /**
     * Constructor of AcademicsExporter
     * @param assessmentList
     */
    public AcademicsExporter(ObservableList<Assessment> assessmentList) {
        this.assessmentList = assessmentList;
    }

    /**
     * Function which saves the assessments to a .csv file is user's current directory.
     */
    public void saveToCsv() {

        ArrayList<ArrayList<String>> rows = new ArrayList<>();

        int noOfStudents = assessmentList.get(0).getSubmissionTracker().size();

        // adding all students names first
        for (Submission submission : assessmentList.get(0).getSubmissionTracker()) {
            ArrayList<String> studentContent = new ArrayList<>();
            studentContent.add(submission.getStudentName());
            rows.add(studentContent);
        }

        // for each assessment, add all the students' scores in
        for (Assessment assessment : assessmentList) {
            List<Submission> tracker = assessment.getSubmissionTracker();
            for (int i = 0; i < noOfStudents; i++) {
                rows.get(i).add(tracker.get(i).getScore() + "");
            }
        }

        try {
            FileWriter csvWriter = new FileWriter("data/studentAcademics.csv");
            csvWriter.append("Student");
            for (Assessment assessment : assessmentList) {
                csvWriter.append(",");
                csvWriter.append(assessment.getDescription());
            }
            csvWriter.append("\n");

            for (ArrayList<String> rowData : rows) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }

            // last row of statistics
            csvWriter.append("Median Score");
            for (Assessment assessment : assessmentList) {
                csvWriter.append(",");
                csvWriter.append(assessment.medianScore() + "");
            }
            csvWriter.append("\n");

            csvWriter.append("Average Score");
            for (Assessment assessment : assessmentList) {
                csvWriter.append(",");
                csvWriter.append(assessment.averageScore() + "");
            }
            csvWriter.append("\n");

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



}
