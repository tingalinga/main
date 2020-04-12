package seedu.address.testutil.academics;

import seedu.address.model.academics.Submission;

/**
 * A utility class to help with building Student objects.
 */
public class SubmissionBuilder {

    public static final String DEFAULT_STUDENT_NAME = "Simon Lam";
    public static final String DEFAULT_SUBMITTED = "true";
    public static final String DEFAULT_MARKED = "true";
    public static final String DEFAULT_SCORE = "80";

    private String studentName;
    private boolean submitted;
    private boolean marked;
    private int score;

    public SubmissionBuilder() {
        studentName = DEFAULT_STUDENT_NAME;
        submitted = DEFAULT_SUBMITTED.equals("true");
        marked = DEFAULT_MARKED.equals("true");
        score = Integer.parseInt(DEFAULT_SCORE);
    }

    /**
     * Initializes the SubmissionBuilder with the data of {@code submissionToCopy}.
     */
    public SubmissionBuilder(Submission submissionToCopy) {
        studentName = submissionToCopy.getStudentName();
        submitted = submissionToCopy.hasSubmitted();
        marked = submissionToCopy.isMarked();
        score = submissionToCopy.getScore();
    }

    /**
     * Sets the {@code StudentName} of the {@code Submission} that we are building.
     */
    public SubmissionBuilder withStudentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    /**
     * Sets the {@code submitted} of the {@code Submission} that we are building.
     */
    public SubmissionBuilder withSubmitted(String submitted) {
        this.submitted = submitted.equals("true");
        return this;
    }

    /**
     * Sets the {@code marked} of the {@code Submission} that we are building.
     */
    public SubmissionBuilder withMarked(String marked) {
        this.marked = marked.equals("true");
        return this;
    }

    /**
     * Sets the {@code score} of the {@code Submission} that we are building.
     */
    public SubmissionBuilder withScore(String score) {
        this.score = Integer.parseInt(score);
        return this;
    }

    /**
     * Builds an submission.
     */
    public Submission build() {
        return new Submission(studentName, submitted, marked, score);
    }
}
