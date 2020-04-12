package seedu.address.testutil.academics;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.academics.Assessment;
import seedu.address.model.academics.Submission;

/**
 * A utility class to help with building Student objects.
 */
public class AssessmentBuilder {

    public static final String DEFAULT_DESCRIPTION = "Science Homework";
    public static final String DEFAULT_TYPE = "homework";
    public static final String DEFAULT_DATE = "2020-03-04";

    private String description;
    private String type;
    private String date;
    private List<Submission> submissionTracker;

    public AssessmentBuilder() {
        description = DEFAULT_DESCRIPTION;
        type = DEFAULT_TYPE;
        date = DEFAULT_DATE;
        submissionTracker = new ArrayList<>();
    }

    /**
     * Initializes the AssessmentBuilder with the data of {@code assessmentToCopy}.
     */
    public AssessmentBuilder(Assessment assessmentToCopy) {
        description = assessmentToCopy.getDescription();
        type = assessmentToCopy.getType();
        date = assessmentToCopy.getDate().toString();
        if (submissionTracker != null) {
            for (Submission submission: submissionTracker) {
                submissionTracker.add(submission);
            }
        }
    }

    /**
     * Sets the {@code Description} of the {@code Assessment} that we are building.
     */
    public AssessmentBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Parses the {@code submissions} into a {@code List<Submission>} and set it to the
     * {@code Assessment} that we are building.
     */
    public AssessmentBuilder withSubmissions(Submission ... submissions) {
        for (Submission submission: submissions) {
            submissionTracker.add(submission);
        }
        return this;
    }

    /**
     * Sets the {@code type} of the {@code Assessment} that we are building.
     */
    public AssessmentBuilder withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Sets the {@code date} of the {@code Assessment} that we are building.
     */
    public AssessmentBuilder withDate(String date) {
        this.date = date;
        return this;
    }

    /**
     * Builds an assessment.
     */
    public Assessment build() {
        Assessment assessment = new Assessment(description, type, date);
        assessment.setSubmissionTracker(submissionTracker);
        return assessment;
    }
}
