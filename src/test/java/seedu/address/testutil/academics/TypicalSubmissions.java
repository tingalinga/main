package seedu.address.testutil.academics;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SCIENCE_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MARKED_GRACE_PAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MARKED_SHARADH_RAJARAMAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_GRACE_PAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_SHARADH_RAJARAMAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_GRACE_PAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_SHARADH_RAJARAMAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBMITTED_GRACE_PAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBMITTED_SHARADH_RAJARAMAN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.academics.Assessment;
import seedu.address.model.academics.Submission;

/**
 * A utility class containing a list of {@code Submission} objects to be used in tests.
 */
public class TypicalSubmissions {

    public static final Submission[] SUBMISSIONS = {
            new Submission("Simon Lam"),
            new Submission("Gerren Seow"),
            new Submission("Lee Hui Ting"),
            new Submission("Gary Syndromes"),
            new Submission("Freddy Zhang")};

    public static final Submission SIMON_LAM = new SubmissionBuilder().withStudentName("Simon Lam")
            .withSubmitted("true").withMarked("true").withScore("76").build();
    public static final Submission GERREN_SEOW = new SubmissionBuilder().withStudentName("Gerren Seow")
            .withSubmitted("true").withMarked("true").withScore("89").build();
    public static final Submission LEE_HUI_TING = new SubmissionBuilder().withStudentName("Lee Hui Ting")
            .withSubmitted("true").withMarked("true").withScore("80").build();
    public static final Submission GARY_SYNDROMES = new SubmissionBuilder().withStudentName("Gary Syndromes")
            .withSubmitted("true").withMarked("true").withScore("47").build();
    public static final Submission FREDDY_ZHANG = new SubmissionBuilder().withStudentName("Freddy Zhang")
            .withSubmitted("true").withMarked("true").withScore("92").build();

    // Manually added - Submission's details found in {@code CommandTestUtil}
    public static final Submission SHARADH_RAJARAMAN = new SubmissionBuilder()
            .withStudentName(VALID_STUDENT_NAME_SHARADH_RAJARAMAN)
            .withSubmitted(VALID_SUBMITTED_SHARADH_RAJARAMAN).withMarked(VALID_MARKED_SHARADH_RAJARAMAN)
            .withScore(VALID_SCORE_SHARADH_RAJARAMAN).build();
    public static final Submission GRACE_PAN = new SubmissionBuilder()
            .withStudentName(VALID_STUDENT_NAME_GRACE_PAN)
            .withSubmitted(VALID_SUBMITTED_GRACE_PAN).withMarked(VALID_MARKED_GRACE_PAN)
            .withScore(VALID_SCORE_GRACE_PAN).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalSubmissions() {} // prevents instantiation

    /**
     * Returns an {@code Assessment} with all the typical submissions.
     */
    public static Assessment getTypicalAssessment() {
        Assessment assessment = new Assessment("Chemistry Homework", "homework", "2020-03-04");
        assessment.setSubmissionTracker(getTypicalSubmissions());
        return assessment;
    }

    public static List<Submission> getTypicalSubmissions() {
        return new ArrayList<>(Arrays.asList(SIMON_LAM, GERREN_SEOW, LEE_HUI_TING,
                GARY_SYNDROMES, FREDDY_ZHANG));
    }
}
