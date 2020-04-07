package seedu.address.storage.teapet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.TeaPet;
import seedu.address.model.student.ReadOnlyTeaPet;
import seedu.address.model.student.Student;

/**
 * An Immutable TeaPet that is serializable to JSON format.
 */
@JsonRootName(value = "teapet")
class JsonSerializableTeaPet {
    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTeaPet} with the given students.
     */
    @JsonCreator
    public JsonSerializableTeaPet(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyTeaPet} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTeaPet}.
     */
    public JsonSerializableTeaPet(ReadOnlyTeaPet source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this tea pet into the model's {@code TeaPet} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TeaPet toModelType() throws IllegalValueException {
        TeaPet teaPet = new TeaPet();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (teaPet.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            teaPet.addStudent(student);
        }
        return teaPet;
    }
}
