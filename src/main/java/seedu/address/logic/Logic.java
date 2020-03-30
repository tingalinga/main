package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.academics.Assessment;
import seedu.address.model.academics.ReadOnlyAcademics;
import seedu.address.model.admin.Date;
import seedu.address.model.admin.ReadOnlyAdmin;
import seedu.address.model.student.Student;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    // ==================== ADDRESS BOOK START ====================
    /**
     * Returns the AddressBook.
     *
     * @see Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();
    // ==================== ADDRESS BOOK END ====================

    // ==================== ACADEMICS START ====================
    /**
     * Returns the Academics.
     *
     * @see Model#getAcademics()
     */
    ReadOnlyAcademics getAcademics();

    /** Returns an unmodifiable view of the filtered list of assessments */
    ObservableList<Assessment> getFilteredAcademicsList();

    /**
     * Returns the user prefs' academics file path.
     */
    Path getAcademicsFilePath();
    // ==================== ACADEMICS END ====================

    // ==================== ADMIN START ====================
    /**
     * Returns the Admin page.
     *
     * @see Model#getAdmin()
     */
    ReadOnlyAdmin getAdmin();

    /** Returns an unmodifiable view of the filtered list of admin list */
    ObservableList<Date> getFilteredDateList();

    /**
     * Returns the user prefs' admin file path.
     */
    Path getAdminFilePath();
    // ==================== ADMIN END ====================

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
