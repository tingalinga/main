package seedu.address.storage.academics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.academics.Academics;
import seedu.address.model.academics.Assessment;
import seedu.address.model.academics.ReadOnlyAcademics;
import seedu.address.model.academics.SavedAcademics;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "academics")
class JsonSerializableAcademics {

    public static final String MESSAGE_DUPLICATE_ASSESSMENT = "Academics list contains duplicate assessment(s).";

    private final List<JsonAdaptedAssessment> assessments = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAcademics} with the given assessments.
     */
    @JsonCreator
    public JsonSerializableAcademics(@JsonProperty("assessment") List<JsonAdaptedAssessment> assessments) {
        this.assessments.addAll(assessments);
    }

    /**
     * Converts a given {@code ReadOnlyAcademics} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAcademics(ReadOnlyAcademics source) {
        assessments.addAll(source.getAcademicsList().stream().map(JsonAdaptedAssessment::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SavedAcademics toModelType() throws IllegalValueException {
        SavedAcademics savedAcademics = new SavedAcademics();
        for (JsonAdaptedAssessment jsonAdaptedAssessment : assessments) {
            Assessment assessment = jsonAdaptedAssessment.toModelType();
            if (savedAcademics.hasAssessment(assessment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ASSESSMENT);
            }
            savedAcademics.addAssessment(assessment);
        }
        return savedAcademics;
    }

}
