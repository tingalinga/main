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
    public static final Prefix PREFIX_ATTENDANCE = new Prefix("att/");
    public static final Prefix PREFIX_CONTENT = new Prefix("c/");

    /* Assessment Prefixes */
    public static final Prefix PREFIX_ADD = new Prefix("add");
    public static final Prefix PREFIX_ASSESSMENT_DESCRIPTION = new Prefix("desc/");
    public static final Prefix PREFIX_ASSESSMENT_TYPE = new Prefix("type/");
    public static final Prefix PREFIX_ASSESSMENT_DATE = new Prefix("date/");

    /* Event Prefixes */
    public static final Prefix PREFIX_EVENT_NAME = new Prefix("eventName/");
    public static final Prefix PREFIX_START_DATETIME = new Prefix("startDateTime/");
    public static final Prefix PREFIX_END_DATETIME = new Prefix("endDateTime/");
    public static final Prefix PREFIX_VIEW_DATE = new Prefix("targetDate/");
    public static final Prefix PREFIX_VIEW = new Prefix("view");
    public static final Prefix PREFIX_RECUR = new Prefix("recur/");
    public static final Prefix PREFIX_COLOR = new Prefix("color/");
    public static final Prefix PREFIX_GET_INDEX = new Prefix("index/");
    public static final Prefix PREFIX_VIEW_MODE = new Prefix("scheduleMode/");
    public static final Prefix PREFIX_DELETE = new Prefix("delete");


}
