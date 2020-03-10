package seedu.address.model.student;

public class NextOfKin {

    public static final String MESSAGE_CONSTRAINTS =
            "NOK should be in this format: add later la shag alr ";
    private static final String VALIDATION_REGEX_NOKNAME = "[\\p{Alnum}][\\p{Alnum} ]*";
    private static final String VALIDATION_REGEX_NOKCONTACT = "\\d{3,}";
    public final String value;

    private final String detailsOfNOK;
    private final String nameOfNOK;
    private final String relationshipOfNOK;
    private final String contactOfNOK;

    public NextOfKin(String detailsOfNOK) {
        if (detailsOfNOK.equals("Insert NOK details here!")) {
            this.detailsOfNOK = detailsOfNOK;
            this.nameOfNOK = "Insert NOK name here!";
            this.relationshipOfNOK = "Insert NOK relationship here!";
            this.contactOfNOK = "Insert NOK contact here!";
            value = detailsOfNOK;
        } else {
            this.detailsOfNOK = detailsOfNOK;
            this.nameOfNOK = this.detailsOfNOK.split("-")[0];
            this.relationshipOfNOK = this.detailsOfNOK.split("-")[1];
            this.contactOfNOK = this.detailsOfNOK.split("-")[2];
            value = detailsOfNOK;
        }
    }



    public String getNameOfNOK() {
        return this.nameOfNOK.trim();
    }

    public String getRelationshipOfNOK() {
        return this.relationshipOfNOK.trim();
    }

    public String getContactOfNOK() {
        return this.contactOfNOK.trim();
    }

    public static boolean isValidNokName(String test) {
        if (test.equals("Insert NOK details here!")) {
            return true;
        } else {
            return test.matches(VALIDATION_REGEX_NOKNAME);
        }
    }

    public static boolean isValidNokRelationship(String test) {
        String lowerCaseTest = test.trim().toLowerCase();
        switch (lowerCaseTest){
        case "Insert NOK details here!":
            return true;
        case "father":
            return true;
        case "mother":
            return true;
        case "sister":
            return true;
        case "brother":
            return true;
        case "grandfather":
            return true;
        case "grandmother":
            return true;
        default:
            return false;
        }
    }

    public static boolean isValidNokContact(String test) {
        if (test.equals("Insert NOK details here!")) {
            return true;
        } else {
            return test.trim().matches(VALIDATION_REGEX_NOKCONTACT);
        }
    }

    public static boolean isValidNok(String test) {
        if (test.equals("Insert NOK details here!")) {
            return true;
        } else {
            String nameOfNOK = test.split("-")[0];
            String relationshipOfNOK = test.split("-")[1];
            String contactOfNOK = test.split("-")[2];
            return isValidNokName(nameOfNOK) && isValidNokContact(contactOfNOK) && isValidNokRelationship(relationshipOfNOK);
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
