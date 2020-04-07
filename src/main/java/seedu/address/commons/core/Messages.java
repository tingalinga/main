package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    /* General Messages */
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "No such student found!";

    /* Student Messages */
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = "The student index provided is invalid";
    public static final String MESSAGE_STUDENTS_LISTED_OVERVIEW = "%1$d students listed!";

    /* Academics Messages */
    public static final String MESSAGE_INVALID_ASSESSMENT_DISPLAYED_INDEX = "The assessment index provided is invalid";
    public static final String MESSAGE_INVALID_ASSESSMENT_TYPE = "Invalid assessment type!";
    public static final String MESSAGE_INVALID_STUDENT_SUBMISSION = "Invalid student!";
    public static final String MESSAGE_INVALID_MARKS_SUBMISSION =
            "Invalid score! Score must be from 0 to 100 inclusive.";
    public static final String MESSAGE_STUDENT_HAS_NOT_SUBMITTED = "Invalid marking! "
            + "Student has not submitted his/her work.";
    public static final String MESSAGE_MISSING_SCORE = "Missing score!";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Invalid date! Input YYYY-MM-DD and it should be a valid "
            + "date";

    /* Scheduler Messages */
    public static final String MESSAGE_MISSING_EVENT_NAME = "Missing event name!";
    public static final String MESSAGE_INVALID_EVENT_DATETIME_RANGE = "Invalid date range! ";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists! Please choose another event.";
    public static final String MESSAGE_INVALID_RECURRENCE_TYPE = "Invalid recurrence type."
            + " Input either none, daily or weekly.";
    public static final String MESSAGE_INVALID_COLOR_STRING = "Invalid color code."
            + " Color code must be from 0 to 23 inclusive.";
    public static final String MESSAGE_INVALID_DATE = "Invalid date input passed."
            + " The format should be YYYY-MM-DD and should be a valid date.";
    public static final String MESSAGE_INVALID_EVENT_INDEX = "Invalid event index provided."
            + " Please use indexGet/<eventName> to obtain the right index";
    public static final String MESSAGE_INDEX_INVALID_EVENT_NAME = "Invalid event name entered."
            + " Please input a valid Event Name." + "\n"
            + " schedule indexGet/<eventName>";
    public static final String MESSAGE_SCHEDULE_HELP = "Invalid schedule command entered."
            + " Please input a valid schedule command." + "\n"
            + "schedule add" + "\n"
            + "schedule edit" + "\n"
            + "schedule delete" + "\n"
            + "schedule indexGet/" + "\n"
            + "schedule view";
    public static final String MESSAGE_INVALID_VIEW_MODE = "Invalid schedule view mode."
            + " Please input either weekly or daily.";

    /* Notes Messages */
    public static final String MESSAGE_INVALID_NOTES_DISPLAYED_INDEX = "The note index provided is invalid";
    public static final String MESSAGE_INVALID_NOTES_COMMAND = "";
    public static final String MESSAGE_DUPLICATE_NOTES = "This same note already exists";
    public static final String MESSAGE_UNAVAILABLE_NOTES = "This note is unavailable";
    public static final String MESSAGE_INVALID_PRIORITY =
            "Invalid Priority indicated. Only HIGH, MEDIUM or LOW is allowed.";

}
