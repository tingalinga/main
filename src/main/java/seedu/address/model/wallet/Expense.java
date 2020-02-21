package seedu.address.model.wallet;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an outflow of money from the Wallet.
 */
public class Expense extends Transaction {

    /**
     * Every field must be present and not null.
     */
    public Expense(Description description, Amount amount, LocalDate date, Set<Tag> tags) {
        super(description, amount, date, tags);
    }

    /**
     * Returns true if both Expenses have the same data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Expense)) {
            return false;
        }

        Expense otherExpense = (Expense) other;
        return otherExpense.getDescription().equals(getDescription())
                && otherExpense.getAmount().equals(getAmount())
                && otherExpense.getDate().equals(getDate())
                && otherExpense.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash("expense", description, amount, date, tags);
    }

}
