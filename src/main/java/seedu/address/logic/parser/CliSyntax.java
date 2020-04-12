package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Common Prefix definitions */
    public static final Prefix PREFIX_ADD = new Prefix("add");
    public static final Prefix PREFIX_EDIT = new Prefix("edit");
    public static final Prefix PREFIX_DELETE = new Prefix("delete");
    public static final Prefix PREFIX_FIND = new Prefix("find");
    public static final Prefix PREFIX_EXPORT = new Prefix("export");

    /* Prefix definitions */
    public static final Prefix PREFIX_CLEAR = new Prefix("clear");
    public static final Prefix PREFIX_REFRESH = new Prefix("refresh");
    public static final Prefix PREFIX_DEFAULT = new Prefix("default");
    public static final Prefix PREFIX_DETAILED = new Prefix("detailed");
    public static final Prefix PREFIX_NAME = new Prefix("name/");
    public static final Prefix PREFIX_PHONE = new Prefix("phone/");
    public static final Prefix PREFIX_EMAIL = new Prefix("email/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("adr/");
    public static final Prefix PREFIX_TAG = new Prefix("tag/");
    public static final Prefix PREFIX_NOK = new Prefix("nok/");
    public static final Prefix PREFIX_TEMPERATURE = new Prefix("temp/");
    public static final Prefix PREFIX_ATTENDANCE = new Prefix("att/");

    /* Academics Prefixes */
    public static final Prefix PREFIX_HOMEWORK = new Prefix("homework");
    public static final Prefix PREFIX_EXAM = new Prefix("exam");
    public static final Prefix PREFIX_REPORT = new Prefix("report");
    public static final Prefix PREFIX_SUBMIT = new Prefix("submit");
    public static final Prefix PREFIX_MARK = new Prefix("mark");
    public static final Prefix PREFIX_STUDENT = new Prefix("stu/");
    public static final Prefix PREFIX_ASSESSMENT_DESCRIPTION = new Prefix("desc/");
    public static final Prefix PREFIX_ASSESSMENT_TYPE = new Prefix("type/");
    public static final Prefix PREFIX_ASSESSMENT_DATE = new Prefix("date/");

    /* Note definitions */
    public static final Prefix PREFIX_CONTENT = new Prefix("cont/");
    public static final Prefix PREFIX_PRIORITY = new Prefix("pr/");

    /* Event Prefixes */
    public static final Prefix PREFIX_EVENT_NAME = new Prefix("eventName/");
    public static final Prefix PREFIX_START_DATETIME = new Prefix("startDateTime/");
    public static final Prefix PREFIX_END_DATETIME = new Prefix("endDateTime/");
    public static final Prefix PREFIX_VIEW_DATE = new Prefix("date/");
    public static final Prefix PREFIX_RECUR = new Prefix("recur/");
    public static final Prefix PREFIX_COLOR = new Prefix("color/");
    public static final Prefix PREFIX_GET_INDEX = new Prefix("indexGet/");
    public static final Prefix PREFIX_ALL_INDEX = new Prefix("indexAll");
    public static final Prefix PREFIX_VIEW_MODE = new Prefix("mode/");
    public static final Prefix PREFIX_VIEW = new Prefix("view");
    public static final Prefix PREFIX_EVENT_DELETE = new Prefix("delete");
    public static final Prefix PREFIX_EVENT_EDIT = new Prefix("edit");

    /* Notes Prefixes */
    public static final Prefix PREFIX_NOTES_ADD = new Prefix("add");
    public static final Prefix PREFIX_NOTES_EDIT = new Prefix("edit");
    public static final Prefix PREFIX_NOTES_DELETE = new Prefix("delete");
    public static final Prefix PREFIX_NOTES_FILTER = new Prefix("filter");
    public static final Prefix PREFIX_NOTES_STUDENT = new Prefix("name/");
    public static final Prefix PREFIX_NOTES_CONTENT = new Prefix("cont/");
    public static final Prefix PREFIX_NOTES_PRIORITY = new Prefix("pr/");

}
