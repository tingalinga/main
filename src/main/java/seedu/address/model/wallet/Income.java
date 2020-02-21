package seedu.address.model.wallet;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an inflow of money to the Wallet.
 */
public class Income extends Transaction {

    /**
     * Every field must be present and not null.
     */
    public Income(Description description, Amount amount, LocalDate date, Set<Tag> tags) {
        super(description, amount, date, tags);
    }

    /**
     * Returns true if both Incomes have the same data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Income)) {
            return false;
        }

        Income otherIncome = (Income) other;
        return otherIncome.getDescription().equals(getDescription())
                && otherIncome.getAmount().equals(getAmount())
                && otherIncome.getDate().equals(getDate())
                && otherIncome.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash("income", description, amount, date, tags);
    }

}
