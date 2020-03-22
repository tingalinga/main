package seedu.address.storage.academics;

import java.time.LocalDate;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.academics.Assessment;
import seedu.address.model.academics.Exam;
import seedu.address.model.academics.Homework;
import seedu.address.model.academics.Submission;
import seedu.address.model.student.Student;

/**
 * Jackson-friendly version of {@link Assessment}.
 */
class JsonAdaptedAssessment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "question %s field is missing!";

    private final String description;
    private final HashMap<Student, Submission> submissionTracker;
    private final String type;
    private LocalDate deadline = null;
    private LocalDate examDate = null;

    /**
     * Constructs a {@code JsonAdaptedAssessment} with the given question details.
     */
    @JsonCreator
    public JsonAdaptedAssessment(@JsonProperty("description") String description,
                                 @JsonProperty("submission") HashMap<Student, Submission> submissionTracker,
                                 @JsonProperty("type") String type,
                                 @JsonProperty("date") LocalDate examDate,
                                 @JsonProperty("date") LocalDate deadline) {
        this.description = description;
        this.submissionTracker = submissionTracker;
        this.type = type;
    }

    /**
     * Converts a given {@code Question} into this class for Jackson use.
     */
    public JsonAdaptedAssessment(Assessment source) {
        description = source.getDescription();
        int noOfStudents = source.noOfSubmittedStudents() + source.noOfUnsubmittedStudents();
        submissionTracker = source.getSubmissionTracker();

        if (source instanceof Homework) {
            type = "homework";
            deadline = ((Homework) source).getDeadline();
        } else if (source instanceof Exam) {
            type = "exam";
            examDate = ((Exam) source).getExamDate();
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
        final HashMap<Student, Submission> modelSubmission = submissionTracker;
        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "TYPE"));
        }
        final String modelType = type;

        if (type.equals("homework")) {
            if (deadline == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "DEADLINE"));
            }
            return new Homework(description, deadline);
        } else if (type.equals("exam")) {
            if (examDate == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "EXAM DATE"));
            }
            return new Exam(description, examDate);
        } else {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "TYPE"));
        }
    }

}