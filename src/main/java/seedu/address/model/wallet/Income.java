package seedu.address.model.wallet;

import java.time.LocalDate;

import seedu.address.model.tag.Tag;

/**
 * Represents an inflow of money to the Wallet.
 */
public class Income extends Transaction {

    /**
     * Every field must be present and not null.
     */
    public Income(Description description, Amount amount, LocalDate date, Tag tag) {
        super(description, amount, date, tag);
    }

}
