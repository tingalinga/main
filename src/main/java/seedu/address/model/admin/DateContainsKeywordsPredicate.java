package seedu.address.model.admin;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that a {@code Date}'s {@code Date} matches any of the keywords given.
 */
public class DateContainsKeywordsPredicate implements Predicate<Date> {
    private final LocalDate date;

    public DateContainsKeywordsPredicate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean test(Date date) {
        return date.toString().equals(date.getDate().toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateContainsKeywordsPredicate // instanceof handles nulls
                && date.equals(((DateContainsKeywordsPredicate) other).date)); // state check
    }
}
