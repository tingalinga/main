package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.Address;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.NextOfKin;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Remark;
import seedu.address.model.student.Student;
import seedu.address.model.student.Temperature;
import seedu.address.model.student.notes.Notes;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");
    public static final ArrayList<Notes> EMPTY_NOTES = new ArrayList<>();

    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Simon Lam"), new Phone("87438807"), new Email("simonlam@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), new Temperature("36.5"),
                    new Attendance("Sick"), new NextOfKin("Joseph Lam - father - 99999999"),
                    EMPTY_NOTES, EMPTY_REMARK,
                    getTagSet("Sheares")),
            new Student(new Name("Gerren Seow"), new Phone("99272758"), new Email("gerrenseow@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Temperature("37.0"),
                    new Attendance("Present"), new NextOfKin("Joseph Seow - father - 99999999"),
                    EMPTY_NOTES, EMPTY_REMARK,
                    getTagSet("Temasek")),
            new Student(new Name("Lee Hui Ting"), new Phone("93210283"), new Email("huiting@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Temperature("36.9"),
                    new Attendance("Present"), new NextOfKin("Joseph Lee - father - 99999999"),
                    EMPTY_NOTES, EMPTY_REMARK,
                    getTagSet("CAPT")),
            new Student(new Name("Gary Syndromes"), new Phone("91031282"), new Email("leegary@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Temperature("36.4"),
                    new Attendance("Present"), new NextOfKin("Joseph Syndromes - father - 99999999"),
                    EMPTY_NOTES, EMPTY_REMARK,
                    getTagSet("Home")),
            new Student(new Name("Freddy Zhang"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), new Temperature("37.4"),
                    new Attendance("Present"), new NextOfKin("Joseph Zhang - father - 99999999"),
                    EMPTY_NOTES, EMPTY_REMARK,
                    getTagSet("classmates"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
