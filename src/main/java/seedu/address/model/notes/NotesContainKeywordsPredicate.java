package seedu.address.model.notes;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests whether a Note's content matches any of the keywords given.
 * Contents checked include:
 * String student
 * String content
 * String priority
 * String dateTime
 * Check is done based on similarity in characters, and not full word match.
 */
public class NotesContainKeywordsPredicate implements Predicate<Notes> {
    private final List<String> keywords;

    /**
     * Constructor of NotesContainKeywordsPredicate
     * @param keywords
     */
    public NotesContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Getter of keywords
     * @return list of keywords
     */
    public List<String> getKeywords() {
        return keywords;
    }

    /**
     * Method which checks if student, content, priority and dateTime field contain
     * keywords from user's input.
     * @param notes
     * @return true or false depending if a note contains any keyword.
     */
    @Override
    public boolean test(Notes notes) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.hasKeywordInString(notes.getStudent(), keyword)
                || StringUtil.hasKeywordInString(notes.getContent(), keyword)
                || StringUtil.hasKeywordInString(notes.getPriority(), keyword)
                || StringUtil.hasKeywordInString(notes.getDateTime(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NotesContainKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NotesContainKeywordsPredicate) other).keywords)); // state check
    }
}
