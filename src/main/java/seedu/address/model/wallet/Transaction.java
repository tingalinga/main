package seedu.address.model.wallet;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Transaction in the Wallet.
 */
public abstract class Transaction {

    // Data fields
    protected final Description description;
    protected final Amount amount;
    protected final LocalDate date;
    protected final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Transaction(Description description, Amount amount, LocalDate date, Set<Tag> tags) {
        requireAllNonNull(description, amount, date, tags);
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.tags.addAll(tags);
    }

    public Description getDescription() {
        return description;
    }

    public Amount getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both Transactions have the same data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Transaction)) {
            return false;
        }

        Transaction otherTransaction = (Transaction) other;
        return otherTransaction.getDescription().equals(getDescription())
                && otherTransaction.getAmount().equals(getAmount())
                && otherTransaction.getDate().equals(getDate())
                && otherTransaction.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, amount, date, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append(" Description: ")
                .append(getDescription())
                .append(" Amount: ")
                .append(getAmount())
                .append(" Date: ")
                .append(getDate())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
