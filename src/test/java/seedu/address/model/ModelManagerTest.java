package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSESSMENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssessments.CHINESE_HOMEWORK;
import static seedu.address.testutil.TypicalAssessments.SCIENCE_HOMEWORK;
import static seedu.address.testutil.TypicalDates.FEB_26_2020;
import static seedu.address.testutil.TypicalDates.JAN_26_2020;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.academics.Academics;
import seedu.address.model.academics.DescriptionContainsKeywordsPredicate;
import seedu.address.model.admin.Admin;
import seedu.address.model.event.EventHistory;
import seedu.address.model.notes.NotesManager;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.TeaPet;
import seedu.address.testutil.AcademicsBuilder;
import seedu.address.testutil.AdminBuilder;
import seedu.address.testutil.TeaPetBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TeaPet(), new TeaPet(modelManager.getTeaPet()));
        assertEquals(new Admin(), new Admin(modelManager.getAdmin()));
        assertEquals(new Academics(), new Academics(modelManager.getAcademics()));
        assertEquals(new NotesManager(), new NotesManager(modelManager.getNotesManager()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTeaPetFilePath(Paths.get("address/book/file/path"));
        userPrefs.setAcademicsFilePath(Paths.get("address/book/file/path"));
        userPrefs.setAdminFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTeaPetFilePath(Paths.get("new/address/book/file/path"));
        userPrefs.setAcademicsFilePath(Paths.get("new/address/book/file/path"));
        userPrefs.setAdminFilePath(Paths.get("address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setTeaPetFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTeaPetFilePath(null));
    }

    @Test
    public void setTeaPetFilePath_validPath_setsTeaPetFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setTeaPetFilePath(path);
        assertEquals(path, modelManager.getTeaPetFilePath());
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInTeaPet_returnsFalse() {
        assertFalse(modelManager.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInTeaPet_returnsTrue() {
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasStudent(ALICE));
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    @Test
    public void setAcademicsFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAcademicsFilePath(null));
    }

    @Test
    public void setAcademicsFilePath_validPath_setsAcademicsFilePath() {
        Path path = Paths.get("academics/file/path");
        modelManager.setAcademicsFilePath(path);
        assertEquals(path, modelManager.getAcademicsFilePath());
    }

    @Test
    public void hasAssessment_nullAssessment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasAssessment(null));
    }

    @Test
    public void hasAssessment_assessmentNotInAcademics_returnsFalse() {
        assertFalse(modelManager.hasAssessment(SCIENCE_HOMEWORK));
    }

    @Test
    public void hasAssessment_assessmentInAcademics_returnsTrue() {
        modelManager.addAssessment(SCIENCE_HOMEWORK);
        assertTrue(modelManager.hasAssessment(SCIENCE_HOMEWORK));
    }

    @Test
    public void getFilteredAcademicsList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> modelManager.getFilteredAcademicsList().remove(0));
    }

    @Test
    public void equals() {
        TeaPet teaPet = new TeaPetBuilder().withStudent(ALICE).withStudent(BENSON).build();
        TeaPet differentTeaPet = new TeaPet();
        Academics academics = new AcademicsBuilder().withAssessment(SCIENCE_HOMEWORK)
                .withAssessment(CHINESE_HOMEWORK).build();
        Academics differentAcademics = new Academics();
        Admin admin = new AdminBuilder().withDate(JAN_26_2020).withDate(FEB_26_2020).build();
        Admin differentAdmin = new Admin();
        NotesManager notesManager = new NotesManager();
        UserPrefs userPrefs = new UserPrefs();
        EventHistory eventHistory = new EventHistory();

        // same values -> returns true

        modelManager = new ModelManager(teaPet, academics, admin, notesManager, eventHistory, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(teaPet, academics, admin, notesManager, eventHistory,
                userPrefs);

        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different teaPet -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTeaPet, differentAcademics, differentAdmin,
                notesManager, eventHistory, userPrefs)));

        // different filteredList -> returns false
        String[] studentKeywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(studentKeywords)));
        String[] assessmentKeywords = SCIENCE_HOMEWORK.getDescription().split("\\s+");
        modelManager.updateFilteredAcademicsList(
                new DescriptionContainsKeywordsPredicate(Arrays.asList(assessmentKeywords)));
        assertFalse(modelManager.equals(new ModelManager(teaPet, academics, admin, notesManager, eventHistory,
                userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        modelManager.updateFilteredAcademicsList(PREDICATE_SHOW_ALL_ASSESSMENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTeaPetFilePath(Paths.get("differentFilePath"));
        differentUserPrefs.setAcademicsFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(teaPet, academics, admin, notesManager,
                eventHistory, differentUserPrefs)));
    }
}
