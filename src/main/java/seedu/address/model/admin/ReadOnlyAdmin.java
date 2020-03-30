package seedu.address.model.admin;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an admin list
 */
public interface ReadOnlyAdmin {

    /**
     * Returns an unmodifiable view of the admin list.
     */
    ObservableList<Date> getDateList();

}
