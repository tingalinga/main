package seedu.address.storage.academics;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.model.academics.ReadOnlyAcademics;

import seedu.address.commons.exceptions.DataConversionException;

/**
 * Represents a storage for {@link seedu.address.model.academics.Academics}.
 */
public interface AcademicsStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getSavedAcademicsFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyAcademics}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAcademics> readAcademics() throws DataConversionException, IOException;

    /**
     * @see #getSavedAcademicsFilePath()
     */
    Optional<ReadOnlyAcademics> readAcademics(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAcademics} to the storage.
     * @param academics cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAcademics(ReadOnlyAcademics academics) throws IOException;

    /**
     * @see #saveAcademics(ReadOnlyAcademics)
     */
    void saveAcademics(ReadOnlyAcademics academics, Path filePath) throws IOException;
}