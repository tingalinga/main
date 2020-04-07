package seedu.address.storage.teapet;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.student.ReadOnlyTeaPet;

/**
 * Represents a storage for {@link seedu.address.model.student.TeaPet}.
 */
public interface TeaPetStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTeaPetFilePath();

    /**
     * Returns TeaPet data as a {@link ReadOnlyTeaPet}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTeaPet> readTeaPet() throws DataConversionException, IOException;

    /**
     * @see #getTeaPetFilePath()
     */
    Optional<ReadOnlyTeaPet> readTeaPet(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTeaPet} to the storage.
     * @param teaPet cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTeaPet(ReadOnlyTeaPet teaPet) throws IOException;

    /**
     * @see #saveTeaPet(ReadOnlyTeaPet)
     */
    void saveTeaPet(ReadOnlyTeaPet teaPet, Path filePath) throws IOException;
}
