package seedu.address.model.admin;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import seedu.address.model.student.Student;

/**
 * Represents a Date of the admin details of the class.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in YYYY-MM-DD format";
    private LocalDate date;
    private List<Student> students;

    /**
     * Every entry field must be present and not null.
     *
     * @param date description of assessment.
     */
    public Date(LocalDate date, List<Student> students) {
        this.date = date;
        this.students = students;
    }

    /**
     * Returns the date of the admin detail.
     * @return date of admin detail;.
     */
    public LocalDate getDate() {
        return date;
    }

    public List<Student> getStudents() {
        return students;
    }

    /**
     * Returns true if both dates have the same date.
     * This defines a weaker notion of equality between two dates.
     */
    public boolean isSameDate(Date otherDate) {
        if (otherDate == this) {
            return true;
        }
        return otherDate != null
                && otherDate.getDate().equals(this.getDate());
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Date)) {
            return false;
        }
        return this.getDate().toString().equals(((Date) o).getDate().toString());
    }
}

/**
 * Comparator class for dates.
 */
class DateComparator implements Comparator<Date> {
    @Override
    public int compare(Date a1, Date a2) {
        return a1.getDate().compareTo(a2.getDate());
    }
}
