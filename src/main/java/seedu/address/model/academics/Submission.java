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

    public boolean hasSubmitted() {
        return this.submitted;
    }

    public boolean isMarked() {
        return this.marked;
    }

    public int getScore() {
        return this.score;
    }

    public void markAsSubmitted() {
        this.submitted = true;
    }

    public void markAssessment(int score) {
        this.marked = true;
        this.score = score;
    }

    @Override
    public int compareTo(Submission s) {
        return 0;
    }
}