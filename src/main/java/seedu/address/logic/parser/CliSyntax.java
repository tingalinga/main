package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_NOK = new Prefix("nok/");
    public static final Prefix PREFIX_TEMPERATURE = new Prefix("temp/");
    public static final Prefix PREFIX_CONTENT = new Prefix("c/");

    /* Assessment Prefixes */
    public static final Prefix PREFIX_ADD = new Prefix("add");
    public static final Prefix PREFIX_ASSESSMENT_DESCRIPTION = new Prefix("desc/");
    public static final Prefix PREFIX_ASSESSMENT_TYPE = new Prefix("type/");
    public static final Prefix PREFIX_ASSESSMENT_DATE = new Prefix("date/");

}
