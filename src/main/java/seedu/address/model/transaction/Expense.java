package seedu.address.model.transaction;

import java.time.LocalDate;

import seedu.address.model.tag.Tag;

/**
 * Represents an outflow of money from the Wallet.
 */
public class Expense extends Transaction {

    /**
     * Every field must be present and not null.
     */
    public Expense(Description description, Amount amount, LocalDate date, Tag tag) {
        super(description, amount, date, tag);
    }

}
