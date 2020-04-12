package seedu.address.model.academics;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.academics.AssessmentBuilder;

public class DescriptionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DescriptionContainsKeywordsPredicate firstPredicate =
                new DescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        DescriptionContainsKeywordsPredicate secondPredicate =
                new DescriptionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DescriptionContainsKeywordsPredicate firstPredicateCopy =
                new DescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_descriptionContainsKeywords_returnsTrue() {
        // One keyword
        DescriptionContainsKeywordsPredicate predicate = new DescriptionContainsKeywordsPredicate(Collections.singletonList("Science"));
        assertTrue(predicate.test(new AssessmentBuilder().withDescription("Science Homework").build()));

        // Multiple keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("Science", "Math"));
        assertTrue(predicate.test(new AssessmentBuilder().withDescription("Science Math").build()));

        // Only one matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("Math", "Chinese"));
        assertTrue(predicate.test(new AssessmentBuilder().withDescription("Science Chinese").build()));

        // Mixed-case keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("sCIence", "maTH"));
        assertTrue(predicate.test(new AssessmentBuilder().withDescription("Science Math").build()));
    }

    @Test
    public void test_descriptionDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DescriptionContainsKeywordsPredicate predicate = new DescriptionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new AssessmentBuilder().withDescription("Science").build()));

        // Non-matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("Chinese"));
        assertFalse(predicate.test(new AssessmentBuilder().withDescription("Science Math").build()));

        // Keywords match type and date and address, but does not match description
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("homework", "2020-03-04"));
        assertFalse(predicate.test(new AssessmentBuilder().withDescription("Science").withType("homework")
                .withDate("2020-03-04").build()));
    }
}
