package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.notes.Notes;

/**
 * Jackson-friendly version of Notes.
 */
class JsonAdaptedNotes {

    private final String student;
    private final String content;
    private final String dateTime;

    /**
     * Constructs a JsonAdaptedNotes
     */
    @JsonCreator
    public JsonAdaptedNotes(@JsonProperty("student") String student, @JsonProperty("content") String content,
                            @JsonProperty("dateTime") String dateTime) {
        this.student = student;
        this.content = content;
        this.dateTime = dateTime;
    }

    /**
     * Converts a given Notes object into this class for Jackson use.
     */
    public JsonAdaptedNotes(Notes source) {
        this.student = source.getStudent();
        this.content = source.getContent();
        this.dateTime = source.getDateTime();
    }

    /**
     * Converts this Jackson-friendly adapted notes object into the model's Notes object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted notes.
     */
    public Notes toModelType() {
        return new Notes(student, content, dateTime);
    }

}

