package seedu.address.storage.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.admin.Admin;
import seedu.address.testutil.TypicalDates;

public class JsonSerializableAdminTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableAdminTest");
    private static final Path TYPICAL_DATES_FILE = TEST_DATA_FOLDER.resolve("typicalDatesAdmin.json");
    private static final Path INVALID_DATE_FILE = TEST_DATA_FOLDER.resolve("invalidDateAdmin.json");
    private static final Path DUPLICATE_DATE_FILE = TEST_DATA_FOLDER.resolve("duplicateDateAdmin.json");

    @Test
    public void toModelType_typicalDatesFile_success() throws Exception {
        JsonSerializableAdmin dataFromFile = JsonUtil.readJsonFile(TYPICAL_DATES_FILE,
                JsonSerializableAdmin.class).get();
        Admin adminFromFile = dataFromFile.toModelType();
        Admin typicalDatesAdmin = TypicalDates.getTypicalAdmin();
        file:///Users/freddy/Desktop/main/build/reports/checkstyle/main.html
        assertEquals(adminFromFile, typicalDatesAdmin);
    }

    @Test
    public void toModelType_invalidDateFile_throwsIllegalValueException() throws DataConversionException {
        boolean thrown = false;
        try {
            JsonSerializableAdmin dataFromFile = JsonUtil.readJsonFile(INVALID_DATE_FILE,
                    JsonSerializableAdmin.class).get();
        } catch (DataConversionException ife) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void toModelType_duplicateDates_throwsIllegalValueException() throws Exception {
        JsonSerializableAdmin dataFromFile = JsonUtil.readJsonFile(DUPLICATE_DATE_FILE,
                JsonSerializableAdmin.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAdmin.MESSAGE_DUPLICATE_DATE,
                dataFromFile::toModelType);
    }
}
