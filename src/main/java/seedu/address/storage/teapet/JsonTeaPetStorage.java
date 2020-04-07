package seedu.address.storage.teapet;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.student.ReadOnlyTeaPet;

/**
 * A class to access TeaPet data stored as a json file on the hard disk.
 */
public class JsonTeaPetStorage implements TeaPetStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTeaPetStorage.class);

    private Path filePath;

    public JsonTeaPetStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTeaPetFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTeaPet> readTeaPet() throws DataConversionException {
        return readTeaPet(filePath);
    }

    /**
     * Similar to {@link #readTeaPet()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTeaPet> readTeaPet(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTeaPet> jsonTeaPet = JsonUtil.readJsonFile(
                filePath, JsonSerializableTeaPet.class);
        if (!jsonTeaPet.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTeaPet.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTeaPet(ReadOnlyTeaPet teaPet) throws IOException {
        saveTeaPet(teaPet, filePath);
    }

    /**
     * Similar to {@link #saveTeaPet(ReadOnlyTeaPet)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTeaPet(ReadOnlyTeaPet teaPet, Path filePath) throws IOException {
        requireNonNull(teaPet);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTeaPet(teaPet), filePath);
    }
}
