package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String HELP_MESSAGE =
            "Add: add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG] nok/NAME-RELATIONSHIP-PHONE-NUMBER temp/XX.X..."
            + "\n"
            + "e.g. add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague"
            + "nok/jon-father-87654321 temp/36.2" + "\n" + "\n"
            + "Clear : clear" + "\n" + "\n"
            + "Delete : delete INDEX" + "\n"
            + "e.g. delete 3" + "\n" + "\n"
            + "Edit : edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRcdESS] [t/TAG]..." + "\n"
            + "e.g. edit 2 n/James Lee e/jameslee@example.com" + "\n" + "\n"
            + "Find : find KEYWORD [MORE_KEYWORDS]" + "\n"
            + "e.g. find James Jake" + "\n" + "\n"
            + "List : list" + "\n" + "\n"
            + "Help : help" + "\n" + "\n"
            + "Schedule : schedule" + "\n" + "\n"
            + "Detailed student list : detailed" + "\n" + "\n"
            + "Default student list : default";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";


    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
