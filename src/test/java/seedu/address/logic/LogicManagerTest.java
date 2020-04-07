package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TEMPERATURE_DESC_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.student.StudentAddCommand;
import seedu.address.logic.commands.student.StudentRefreshCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.academics.ReadOnlyAcademics;
import seedu.address.model.admin.ReadOnlyAdmin;
import seedu.address.model.student.ReadOnlyTeaPet;
import seedu.address.model.student.Student;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.academics.JsonAcademicsStorage;
import seedu.address.storage.admin.JsonAdminStorage;
import seedu.address.storage.event.JsonEventStorage;
import seedu.address.storage.notes.JsonNotesManagerStorage;
import seedu.address.storage.teapet.JsonTeaPetStorage;
import seedu.address.testutil.StudentBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonTeaPetStorage teaPetStorage =
                new JsonTeaPetStorage(temporaryFolder.resolve("teaPet.json"));
        JsonAdminStorage adminStorage =
                new JsonAdminStorage(temporaryFolder.resolve("admin.json"));
        JsonAcademicsStorage academicsStorage =
                new JsonAcademicsStorage(temporaryFolder.resolve("academics.json"));
        JsonNotesManagerStorage notesManagerStorage =
                new JsonNotesManagerStorage(temporaryFolder.resolve("notes.json"));
        JsonEventStorage eventHistory =
                new JsonEventStorage(temporaryFolder.resolve("event.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(teaPetStorage, adminStorage, academicsStorage,
                userPrefsStorage, eventHistory, notesManagerStorage);

        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "student delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = StudentRefreshCommand.COMMAND_WORD + " refresh";
        assertCommandSuccess(listCommand, StudentRefreshCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonTeaPetIoExceptionThrowingStub and JsonAcademicsIoExceptionThrowingStub
        JsonTeaPetStorage teaPetStorage =
                new JsonTeaPetIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionTeaPet.json"));
        JsonAdminStorage adminStorage =
                new JsonAdminIoExceptionThrowingStub(temporaryFolder.resolve("ioException.json"));
        JsonAcademicsStorage academicsStorage =
                new JsonAcademicsIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAcademics.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        JsonNotesManagerStorage notesManagerStorage =
                new JsonNotesManagerStorage(temporaryFolder.resolve("notes.json"));
        JsonEventStorage eventHistory =
                new JsonEventStorage(temporaryFolder.resolve("event.json"));

        StorageManager storage = new StorageManager(teaPetStorage, adminStorage, academicsStorage,
                userPrefsStorage, eventHistory, notesManagerStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = StudentAddCommand.COMMAND_WORD + " add" + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + TEMPERATURE_DESC_AMY;
        Student expectedStudent = new StudentBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addStudent(expectedStudent);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredStudentList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getTeaPet(), model.getAcademics(), model.getAdmin(),
                model.getNotesManager(), model.getEventHistory(), new UserPrefs());

        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonTeaPetIoExceptionThrowingStub extends JsonTeaPetStorage {
        private JsonTeaPetIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveTeaPet(ReadOnlyTeaPet teaPet, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonAdminIoExceptionThrowingStub extends JsonAdminStorage {
        private JsonAdminIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveAdmin(ReadOnlyAdmin admin, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonAcademicsIoExceptionThrowingStub extends JsonAcademicsStorage {
        private JsonAcademicsIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveAcademics(ReadOnlyAcademics academics, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
