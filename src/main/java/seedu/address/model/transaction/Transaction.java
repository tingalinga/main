package seedu.address.model.transaction;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;

import seedu.address.model.tag.Tag;

/**
 * Represents a Transaction in the Wallet.
 */
public abstract class Transaction {

    // Data fields
    protected final Description description;
    protected final Amount amount;
    protected final LocalDate date;
    protected final Tag tag;

    /**
     * Every field must be present and not null.
     */
    public Transaction(Description description, Amount amount, LocalDate date, Tag tag) {
        requireAllNonNull(description, amount, date, tag);
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.tag = tag;
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

    public Tag getTag() {
        return tag;
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
                .append(" Tag: ")
                .append(getTag());
        return builder.toString();
    }

}
