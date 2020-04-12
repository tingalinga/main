package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_MATH_ASSIGNMENT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_SCIENCE_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_MATH_ASSIGNMENT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_SCIENCE_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ASSESSMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_MATH_ASSIGNMENT;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_SCIENCE_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_MATH_ASSIGNMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SCIENCE_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MATH_ASSIGNMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SCIENCE_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_MATH_ASSIGNMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_SCIENCE_EXAM;
import static seedu.address.logic.parser.AcademicsCommandParser.HELP_MESSAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSESSMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ASSESSMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ASSESSMENT;
import static seedu.address.testutil.academics.TypicalAssessments.MATH_HOMEWORK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.academics.AcademicsAddCommand;
import seedu.address.logic.commands.academics.AcademicsDeleteCommand;
import seedu.address.logic.commands.academics.AcademicsEditCommand;
import seedu.address.logic.commands.academics.AcademicsEditCommand.EditAssessmentDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.academics.Assessment;
import seedu.address.testutil.academics.AssessmentBuilder;
import seedu.address.testutil.academics.EditAssessmentDescriptorBuilder;

public class AcademicsCommandParserTest {

    private AcademicsCommandParser parser = new AcademicsCommandParser();

    // ==================== ADD COMMAND START ====================
    @Test
    public void parse_allFieldsPresent_success() throws CommandException {
        Assessment expectedAssessment = new AssessmentBuilder(MATH_HOMEWORK).build();

        // whitespace only preamble
        assertParseSuccess(parser, "academics add" + DESCRIPTION_MATH_ASSIGNMENT + TYPE_MATH_ASSIGNMENT
                        + DATE_MATH_ASSIGNMENT,
                new AcademicsAddCommand(expectedAssessment));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, "academics add" + DESCRIPTION_SCIENCE_EXAM + DESCRIPTION_MATH_ASSIGNMENT
                        + TYPE_MATH_ASSIGNMENT + DATE_MATH_ASSIGNMENT,
                new AcademicsAddCommand(expectedAssessment));

        // multiple types - last type accepted
        assertParseSuccess(parser, "academics add" + DESCRIPTION_MATH_ASSIGNMENT + TYPE_SCIENCE_EXAM
                        + TYPE_MATH_ASSIGNMENT + DATE_MATH_ASSIGNMENT,
                new AcademicsAddCommand(expectedAssessment));

        // multiple dates - last date accepted
        assertParseSuccess(parser, "academics add" + DESCRIPTION_MATH_ASSIGNMENT + TYPE_MATH_ASSIGNMENT
                        + DATE_SCIENCE_EXAM + DATE_MATH_ASSIGNMENT,
                new AcademicsAddCommand(expectedAssessment));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AcademicsAddCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, "academics add" + TYPE_MATH_ASSIGNMENT + DATE_MATH_ASSIGNMENT,
                expectedMessage);

        // missing type prefix
        assertParseFailure(parser, "academics add" + DESCRIPTION_MATH_ASSIGNMENT + DATE_MATH_ASSIGNMENT,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser, "academics add" + DESCRIPTION_MATH_ASSIGNMENT + TYPE_MATH_ASSIGNMENT,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, "academics add" + VALID_DESCRIPTION_MATH_ASSIGNMENT
                + VALID_TYPE_MATH_ASSIGNMENT + VALID_DATE_MATH_ASSIGNMENT, expectedMessage);
    }


    @Test
    public void parse_addCommand_invalidValue_failure() {
        // invalid description
        assertParseFailure(parser, "academics add" + INVALID_ASSESSMENT_DESC + TYPE_MATH_ASSIGNMENT
                + DATE_MATH_ASSIGNMENT, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AcademicsAddCommand.MESSAGE_USAGE));

        // invalid type
        assertParseFailure(parser, "academics add" + DESCRIPTION_MATH_ASSIGNMENT + INVALID_TYPE_DESC
                + DATE_MATH_ASSIGNMENT, Messages.MESSAGE_INVALID_ASSESSMENT_TYPE);

        // invalid date
        assertParseFailure(parser, "academics add" + DESCRIPTION_MATH_ASSIGNMENT + TYPE_MATH_ASSIGNMENT
                + INVALID_DATE_DESC, String.format(MESSAGE_INVALID_DATE_FORMAT, HELP_MESSAGE));

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, "academics add" + INVALID_ASSESSMENT_DESC + INVALID_TYPE_DESC
                        + DATE_MATH_ASSIGNMENT, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AcademicsAddCommand.MESSAGE_USAGE));
    }

    // ==================== ADD COMMAND END ====================

    // ==================== DELETE COMMAND START ====================
    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "academics delete 1", new AcademicsDeleteCommand(INDEX_FIRST_ASSESSMENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "academics delete a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AcademicsDeleteCommand.MESSAGE_USAGE));
    }
    // ==================== DELETE COMMAND END ====================

    // ==================== EDIT COMMAND START ====================
    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, "academics edit " + VALID_DESCRIPTION_MATH_ASSIGNMENT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AcademicsEditCommand.MESSAGE_USAGE));

        // no field specified
        assertParseFailure(parser, "academics edit 1", AcademicsEditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "academics edit",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AcademicsEditCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "academics edit -5" + DESCRIPTION_MATH_ASSIGNMENT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AcademicsEditCommand.MESSAGE_USAGE));

        // zero index
        assertParseFailure(parser, "academics edit 0" + DESCRIPTION_MATH_ASSIGNMENT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AcademicsEditCommand.MESSAGE_USAGE));

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "academics edit 1 some random string",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AcademicsEditCommand.MESSAGE_USAGE));

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "academics edit 1 i/ string",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AcademicsEditCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_editCommand_invalidValue_failure() {
        assertParseFailure(parser, "academics edit 1" + INVALID_ASSESSMENT_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AcademicsEditCommand.MESSAGE_USAGE)); // invalid description
        assertParseFailure(parser, "academics edit 1" + INVALID_TYPE_DESC,
                Messages.MESSAGE_INVALID_ASSESSMENT_TYPE); // invalid type
        assertParseFailure(parser, "academics edit 1" + INVALID_DATE_DESC,
                String.format(MESSAGE_INVALID_DATE_FORMAT, HELP_MESSAGE)); // invalid date

        // invalid type followed by valid date
        assertParseFailure(parser, "academics edit 1" + INVALID_TYPE_DESC + DATE_MATH_ASSIGNMENT,
                Messages.MESSAGE_INVALID_ASSESSMENT_TYPE);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "academics edit 1" + DATE_MATH_ASSIGNMENT + INVALID_DATE_DESC,
                String.format(MESSAGE_INVALID_DATE_FORMAT, HELP_MESSAGE));

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "academics edit 1" + INVALID_ASSESSMENT_DESC + INVALID_TYPE_DESC
                + INVALID_DATE_DESC, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AcademicsEditCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ASSESSMENT;
        String userInput = "academics edit " + targetIndex.getOneBased() + DESCRIPTION_MATH_ASSIGNMENT
                + TYPE_MATH_ASSIGNMENT + DATE_MATH_ASSIGNMENT;

        AcademicsEditCommand.EditAssessmentDescriptor descriptor = new EditAssessmentDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_MATH_ASSIGNMENT).withType(VALID_TYPE_MATH_ASSIGNMENT)
                .withDate(VALID_DATE_MATH_ASSIGNMENT).build();
        AcademicsEditCommand expectedCommand = new AcademicsEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ASSESSMENT;

        String userInput = "academics edit " + targetIndex.getOneBased() + TYPE_MATH_ASSIGNMENT + DATE_MATH_ASSIGNMENT;

        EditAssessmentDescriptor descriptor = new EditAssessmentDescriptorBuilder().withType(VALID_TYPE_MATH_ASSIGNMENT)
                .withDate(VALID_DATE_MATH_ASSIGNMENT).build();
        AcademicsEditCommand expectedCommand = new AcademicsEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // description
        Index targetIndex = INDEX_THIRD_ASSESSMENT;
        String userInput = "academics edit " + targetIndex.getOneBased() + DESCRIPTION_MATH_ASSIGNMENT;
        EditAssessmentDescriptor descriptor = new EditAssessmentDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_MATH_ASSIGNMENT).build();
        AcademicsEditCommand expectedCommand = new AcademicsEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // type
        userInput = "academics edit " + targetIndex.getOneBased() + TYPE_MATH_ASSIGNMENT;
        descriptor = new EditAssessmentDescriptorBuilder().withType(VALID_TYPE_MATH_ASSIGNMENT).build();
        expectedCommand = new AcademicsEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = "academics edit " + targetIndex.getOneBased() + DATE_MATH_ASSIGNMENT;
        descriptor = new EditAssessmentDescriptorBuilder().withDate(VALID_DATE_MATH_ASSIGNMENT).build();
        expectedCommand = new AcademicsEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ASSESSMENT;
        String userInput = "academics edit " + targetIndex.getOneBased() + DESCRIPTION_MATH_ASSIGNMENT
                + TYPE_MATH_ASSIGNMENT + DATE_MATH_ASSIGNMENT + DESCRIPTION_SCIENCE_EXAM + TYPE_SCIENCE_EXAM
                + DATE_SCIENCE_EXAM;

        EditAssessmentDescriptor descriptor = new EditAssessmentDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_SCIENCE_EXAM)
                .withType(VALID_TYPE_SCIENCE_EXAM).withDate(VALID_DATE_SCIENCE_EXAM)
                .build();
        AcademicsEditCommand expectedCommand = new AcademicsEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_ASSESSMENT;
        String userInput = "academics edit " + targetIndex.getOneBased() + INVALID_DATE_DESC + DATE_SCIENCE_EXAM;
        EditAssessmentDescriptor descriptor = new EditAssessmentDescriptorBuilder()
                .withDate(VALID_DATE_SCIENCE_EXAM).build();
        AcademicsEditCommand expectedCommand = new AcademicsEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = "academics edit " + targetIndex.getOneBased() + DESCRIPTION_SCIENCE_EXAM + INVALID_TYPE_DESC
                + TYPE_SCIENCE_EXAM;
        descriptor = new EditAssessmentDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_SCIENCE_EXAM).withType(VALID_TYPE_SCIENCE_EXAM).build();
        expectedCommand = new AcademicsEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
    // ==================== EDIT COMMAND END ====================
}
