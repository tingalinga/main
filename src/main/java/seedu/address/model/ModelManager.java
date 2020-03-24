package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.academics.Assessment;
import seedu.address.model.academics.ReadOnlyAcademics;
import seedu.address.model.academics.SavedAcademics;
import seedu.address.model.student.Student;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final UserPrefs userPrefs;
    private final AddressBook addressBook;
    private final FilteredList<Student> filteredStudents;
    private final SavedAcademics savedAcademics;
    private final FilteredList<Assessment> filteredAssessments;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook,
                        ReadOnlyAcademics academics,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, academics, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.savedAcademics = new SavedAcademics(academics);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.addressBook.getStudentList());
        filteredAssessments = new FilteredList<>(this.savedAcademics.getAcademicsList());
    }

    public ModelManager() {
        this(new AddressBook(), new SavedAcademics(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    // ==================== AddressBook START ====================
    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return addressBook.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        addressBook.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        addressBook.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        addressBook.setStudent(target, editedStudent);
    }
    // ==================== AddressBook END ====================

    // ==================== Academics START ====================
    @Override
    public ReadOnlyAcademics getAcademics() {
        return savedAcademics;
    }

    @Override
    public void addAssessment(Assessment assessment) {
        savedAcademics.addAssessment(assessment);
        updateFilteredAcademicsList(PREDICATE_SHOW_ALL_ASSESSMENTS);
    }

    @Override
    public void updateFilteredAcademicsList(Predicate<Assessment> predicate) {
        requireNonNull(predicate);
        filteredAssessments.setPredicate(predicate);
    }

    @Override
    public ObservableList<Assessment> getAllAssessments() {
        return savedAcademics.getAllAssessments();
    }

    @Override
    public Assessment getAssessment(Index index) {
        return savedAcademics.getAssessment(index);
    }

    @Override
    public Assessment deleteAssessment(Index index) {
        return savedAcademics.deleteAssessment(index);
    }

    @Override
    public boolean hasAssessment(Assessment assessment) {
        requireNonNull(assessment);
        return savedAcademics.hasAssessment(assessment);
    }

    @Override
    public void setAssessment(Index index, Assessment assessment) {
        savedAcademics.setAssessment(index, assessment);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public Path getAcademicsFilePath() {
        return null;
    }

    @Override
    public void setAcademicsFilePath(Path addressBookFilePath) {

    }

    @Override
    public ObservableList<Assessment> getFilteredAcademicsList() {
        return filteredAssessments;
    }

    @Override
    public void setAcademics(ReadOnlyAddressBook addressBook) {

    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

}
