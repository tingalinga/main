package seedu.address.model.academics;

/**
 * Represents a students submission for an assessment.
 */
public class Submission implements Comparable<Submission> {
    private boolean submitted;
    private boolean marked;
    private int score;

    public Submission() {
        this.submitted = false;
        this.marked = false;
        this.score = 0;
    }

    /**
     * Returns a boolean that indicates if assessment has been submitted.
     * @return boolean showing whether assessment is submitted by the student.
     */
    public boolean hasSubmitted() {
        return this.submitted;
    }

    /**
     * Returns a boolean that indicates if assessment is marked.
     * @return boolean showing whether assessment is marked.
     */
    public boolean isMarked() {
        return this.marked;
    }

    public int getScore() {
        return this.score;
    }

    /**
     * Submits current assessment.
     */
    public void markAsSubmitted() {
        this.submitted = true;
    }

    /**
     * Assigns a mark to the the assessment.
     */
    public void markAssessment(int score) {
        this.marked = true;
        this.score = score;
    }

    @Override
    public int compareTo(Submission s) {
        return 0;
    }
}
