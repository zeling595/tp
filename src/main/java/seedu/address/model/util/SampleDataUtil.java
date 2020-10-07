package seedu.address.model.util;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.BookingBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyBookingBook;
import seedu.address.model.booking.Booking;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), 1),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), 2),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), 3),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), 4),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), 5),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), 6)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static Booking[] getSampleBookings() {
        return new Booking[] {
                new Booking(1234, 1, LocalDate.of(2020, 1, 1),
                        LocalDate.of(2020, 1, 2), true, 1),
                new Booking(1235, 2, LocalDate.of(2020, 1, 1),
                        LocalDate.of(2020, 1, 3), true, 2),
                new Booking(1236, 3, LocalDate.of(2020, 2, 2),
                        LocalDate.of(2020, 2, 22), true, 3),
                new Booking(1237, 4, LocalDate.of(2019, 3, 1),
                        LocalDate.of(2020, 3, 12), false, 4),
                new Booking(1238, 5, LocalDate.of(2019, 1, 10),
                        LocalDate.of(2020, 1, 13), false, 5),
                new Booking(1235, 6, LocalDate.of(2020, 6, 1),
                        LocalDate.of(2020, 6, 20), false, 6),
        };
    }

    public static ReadOnlyBookingBook getSampleBookingBook() {
        BookingBook sampleBb = new BookingBook();
        for (Booking sampleBooking : getSampleBookings()) {
            sampleBb.addBooking(sampleBooking);
        }
        return sampleBb;
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
