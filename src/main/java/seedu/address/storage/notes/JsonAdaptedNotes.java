package seedu.address.storage.notes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.notes.Notes;

public class JsonAdaptedNotes {
    public final static String MISSING_FIELD_MESSAGE_FORMAT = "Notes in incorrect format.";

    private final String student;
    private final String content;
    private final String dateTime;

    /**
     * Constructs a {@code JsonAdaptedNotes} with the given note details.
     * @param student
     * @param content
     * @param dateTime
     */
    @JsonCreator
    public JsonAdaptedNotes(@JsonProperty("student") String student, @JsonProperty("content") String content,
                            @JsonProperty("dateTime") String dateTime) {
        this.student = student;
        this.content = content;
        this.dateTime = dateTime;
    }

    /**
     * Converts a given {@code source} into this class for Jackson use.
     */
    public JsonAdaptedNotes(Notes source) {
        student = source.getStudent();
        content = source.getContent();
        dateTime = source.getDateTime();
    }

    /**
     * Converts this Jackson-friendly adapted notes object into the model's {@code Notes} object.
     * @throws IllegalValueException
     */
    public Notes toModelType() throws IllegalValueException {
        if (student == null || content == null || dateTime == null) {
            throw new IllegalValueException(MISSING_FIELD_MESSAGE_FORMAT);
        }
        return new Notes(student, content, dateTime);
    }



}
