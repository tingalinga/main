package seedu.address.ui;

import java.io.File;
import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;

/**
 * An UI component that displays minimal information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Student student;

    @FXML
    private ImageView image;

    @FXML
    private Circle circle;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label notes;

    /**
     * Constructor to create the student card controller.
     * Important to note the format of the image, [namelowercasenospace].png
     * eg. Name is Simon Lam, image name is simonlam.png. 1
     * 1. All lower case
     * 2. No whitespaces
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        phone.setText("Mobile:  " + student.getPhone().value);
        email.setText("Email:   " + student.getEmail().value);
        notes.setText("No. of Notes:    " + student.getNotes().size());
        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        try {
            String path = "images/" + student.getName().toString().toLowerCase().replaceAll("\\s+", "") + ".png";
            File file = new File(path);
            if (!file.exists()) {
                Image defaultImage = new Image("images/default_person.png");
                circle.setStroke(Color.SEAGREEN);
                circle.setFill(new ImagePattern(defaultImage));
                circle.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
            } else {
                Image newImage = new Image(file.toURI().toString());
                circle.setStroke(Color.SEAGREEN);
                circle.setFill(new ImagePattern(newImage));
                circle.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentCard)) {
            return false;
        }

        // state check
        StudentCard card = (StudentCard) other;
        return id.getText().equals(card.id.getText())
                && student.equals(card.student);
    }
}
