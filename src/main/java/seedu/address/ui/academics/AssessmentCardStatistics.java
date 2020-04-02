package seedu.address.ui.academics;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.academics.Assessment;
import seedu.address.model.academics.Exam;
import seedu.address.model.academics.Homework;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays minimal information of a {@code Assessment}.
 */
public class AssessmentCardStatistics extends UiPart<Region> {

    private static final String FXML = "AssessmentCardStatistics.fxml";
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Assessment assessment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label type;
    @FXML
    private Label submissionTracker;
    @FXML
    private Label median;
    @FXML
    private Label average;

    /**
     * Constructor to create the assessment card controller.
     */
    public AssessmentCardStatistics(Assessment assessment, int displayedIndex) {
        super(FXML);
        this.assessment = assessment;
        id.setText(displayedIndex + ". ");
        description.setText(assessment.getDescription());
        if (assessment instanceof Homework) {
            type.setText("Assessment:  Homework");
        } else if (assessment instanceof Exam) {
            type.setText("Assessment:  Exam");
        }
        submissionTracker.setText("Submissions: " + assessment.noOfSubmittedStudents()
                + " / " + assessment.getSubmissionTracker().size());
        median.setText("Median Score: " + assessment.medianScore());
        average.setText("Average Score: " + assessment.averageScore());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssessmentCardStatistics)) {
            return false;
        }

        // state check
        AssessmentCardStatistics card = (AssessmentCardStatistics) other;
        return id.getText().equals(card.id.getText())
                && assessment.equals(card.assessment);
    }
}
