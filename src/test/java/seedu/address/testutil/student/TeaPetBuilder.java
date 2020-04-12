package seedu.address.testutil.student;

import seedu.address.model.student.Student;
import seedu.address.model.student.TeaPet;

/**
 * A utility class to help with building TeaPet objects.
 * Example usage: <br>
 *     {@code TeaPet ab = new TeaPetBuilder().withStudent("John", "Doe").build();}
 */
public class TeaPetBuilder {

    private TeaPet teaPet;

    public TeaPetBuilder() {
        teaPet = new TeaPet();
    }

    public TeaPetBuilder(TeaPet teaPet) {
        this.teaPet = teaPet;
    }

    /**
     * Adds a new {@code Student} to the {@code TeaPet} that we are building.
     */
    public TeaPetBuilder withStudent(Student student) {
        teaPet.addStudent(student);
        return this;
    }

    public TeaPet build() {
        return teaPet;
    }
}
