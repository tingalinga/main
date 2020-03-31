package seedu.address.model.notes;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class NotesContainKeywordsPredicate implements Predicate<Notes> {
    private final List<String> keywords;

    public NotesContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    @Override
    public boolean test(Notes notes) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(notes.getContent(), keyword)
                || StringUtil.containsWordIgnoreCase(notes.getDateTime(), keyword)
                || StringUtil.containsWordIgnoreCase(notes.getStudent(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NotesContainKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NotesContainKeywordsPredicate) other).keywords)); // state check
    }
}
