package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.Remark2Command.MESSAGE_ARGUMENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.Remark2CommandParser;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Remark2;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class Remark2CommandTest {

    private static final String REMARK_STUB = "Some remark";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Remark2CommandParser parser = new Remark2CommandParser();
    private final String nonEmptyRemark = "Some remark.";

    @Test
    public void execute() {
        final Remark2 remark2 = new Remark2("Some remark");


        assertCommandFailure(new Remark2Command(INDEX_FIRST_STUDENT, remark2), model,
                String.format(MESSAGE_ARGUMENTS, INDEX_FIRST_STUDENT.getOneBased(), remark2));
    }

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Student firstPerson = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedPerson = new StudentBuilder(firstPerson).withRemark2(REMARK_STUB).build();

        Remark2Command remark2Command = new Remark2Command(INDEX_FIRST_STUDENT, new Remark2(editedPerson.getRemark2().value));

        String expectedMessage = String.format(Remark2Command.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstPerson, editedPerson);

        assertCommandSuccess(remark2Command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteRemarkUnfilteredList_success() {
        Student firstPerson = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedPerson = new StudentBuilder(firstPerson).withRemark2("").build();

        Remark2Command remark2Command = new Remark2Command(INDEX_FIRST_STUDENT,
                new Remark2(editedPerson.getRemark2().toString()));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstPerson, editedPerson);

        assertCommandSuccess(remark2Command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student firstPerson = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedPerson = new StudentBuilder(model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased()))
                .withRemark(REMARK_STUB).build();

        Remark2Command remark2Command = new Remark2Command(INDEX_FIRST_STUDENT, new Remark2(editedPerson.getRemark2().value));

        String expectedMessage = String.format(Remark2Command.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstPerson, editedPerson);

        assertCommandSuccess(remark2Command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        Remark2Command remark2Command = new Remark2Command(outOfBoundIndex, new Remark2(VALID_REMARK_BOB));

        assertCommandFailure(remark2Command, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        Remark2Command remark2Command = new Remark2Command(outOfBoundIndex, new Remark2(VALID_REMARK_BOB));
        assertCommandFailure(remark2Command, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void parse_indexSpecified_success() {
        // have remark
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK + nonEmptyRemark;
        Remark2Command expectedCommand = new Remark2Command(INDEX_FIRST_STUDENT, new Remark2(nonEmptyRemark));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no remark
        userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK;
        expectedCommand = new Remark2Command(INDEX_FIRST_STUDENT, new Remark2(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, RemarkCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, RemarkCommand.COMMAND_WORD + " " + nonEmptyRemark, expectedMessage);
    }

    @Test
    public void equals() {
        final Remark2Command standardCommand = new Remark2Command(INDEX_FIRST_STUDENT, new Remark2(VALID_REMARK_AMY));

        // same values -> returns true
        Remark2Command commandWithSameValues = new Remark2Command(INDEX_FIRST_STUDENT, new Remark2(VALID_REMARK_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new Remark2Command(INDEX_SECOND_STUDENT, new Remark2(VALID_REMARK_AMY))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new Remark2Command(INDEX_FIRST_STUDENT, new Remark2(VALID_REMARK_BOB))));
    }
}