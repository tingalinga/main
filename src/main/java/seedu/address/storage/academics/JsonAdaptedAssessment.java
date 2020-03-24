package seedu.address.storage.academics;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.academics.Assessment;
import seedu.address.model.academics.Exam;
import seedu.address.model.academics.Homework;
import seedu.address.model.academics.Submission;

/**
 * Jackson-friendly version of {@link Assessment}.
 */
class JsonAdaptedAssessment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "question %s field is missing!";

    private final String description;
    private final List<JsonAdaptedSubmission> submissionTracker = new ArrayList<>();
    private final String type;
    private String deadline = null;
    private String examDate = null;

    /**
     * Constructs a {@code JsonAdaptedAssessment} with the given question details.
     */
    @JsonCreator
    public JsonAdaptedAssessment(@JsonProperty("description") String description,
                                 @JsonProperty("submission") List<JsonAdaptedSubmission> submissionTracker,
                                 @JsonProperty("type") String type,
                                 @JsonProperty("date") String deadline,
                                 @JsonProperty("date") String examDate) {
        this.description = description;
        if (submissionTracker != null) {
            this.submissionTracker.addAll(submissionTracker);
        }
        this.type = type;
        this.deadline = deadline;
        this.examDate = examDate;
    }

    /**
     * Converts a given {@code Question} into this class for Jackson use.
     */
    public JsonAdaptedAssessment(Assessment source) {
        description = source.getDescription();
        List<Submission> submissions = source.getSubmissionTracker();
        for (Submission submission: submissions) {
            submissionTracker.add(new JsonAdaptedSubmission(submission));
        }

        if (source instanceof Homework) {
            type = "homework";
            deadline = ((Homework) source).getDeadline().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        } else if (source instanceof Exam) {
            type = "exam";
            examDate = ((Exam) source).getExamDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        } else {
            type = null;
        }
    }

    /**
     * Converts this Jackson-friendly adapted assessment object into the model's {@code Assessment}
     * object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted
     *                               question.
     */
    public Assessment toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "DESCRIPTION"));
        }
        final String modelDescription = description;
        if (submissionTracker == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "SUBMISSION TRACKER"));
        }
        final List<Submission> modelSubmission = new ArrayList<>();
        for (JsonAdaptedSubmission jsonAdaptedSubmission: submissionTracker) {
            modelSubmission.add(jsonAdaptedSubmission.toModelType());
        }
        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "TYPE"));
        }
        final String modelType = type;

        if (type.equals("homework")) {
            if (deadline == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "DEADLINE"));
            }
            Homework modelHomework = new Homework(description, deadline);
            modelHomework.setSubmissionTracker(modelSubmission);
            return modelHomework;
        } else if (type.equals("exam")) {
            if (examDate == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "EXAM DATE"));
            }
            Exam modelExam = new Exam(description, examDate);
            modelExam.setSubmissionTracker(modelSubmission);
            return modelExam;
        } else {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "TYPE"));
        }
    }

}