package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.notes.Notes;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedNotes {

    private final String student;
    private final String content;
    private final String dateTime;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedNotes(@JsonProperty("student") String student, @JsonProperty("content") String content,
                            @JsonProperty("dateTime") String dateTime) {
        this.student = student;
        this.content = content;
        this.dateTime = dateTime;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedNotes(Notes source) {
        this.student = source.getStudent();
        this.content = source.getContent();
        this.dateTime = source.getDateTime();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Notes toModelType() {
        return new Notes(student, content, dateTime);
    }

}

