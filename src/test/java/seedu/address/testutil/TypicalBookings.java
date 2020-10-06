package seedu.address.testutil;

import seedu.address.model.booking.Booking;
import seedu.address.model.booking.BookingBook;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypicalBookings {

    public static final Booking DEFAULT_BOOKING = new Booking(1, 1234, LocalDate.of(2020, 10, 20),
            LocalDate.of(2020, 10, 20), false);
    public static final int DEFAULT_ROOMID = 1234;

    public static final LocalDate startDate_1 = LocalDate.of(2020, 10, 20);
    public static final LocalDate endDate_1 = LocalDate.of(2020, 10, 25);
    public static final LocalDate startDate_2 = LocalDate.of(2020, 10, 23);
    public static final LocalDate endDate_2 = LocalDate.of(2020, 10, 26);
    public static final LocalDate startDate_3 = LocalDate.of(2020, 10, 26);
    public static final LocalDate endDate_3 = LocalDate.of(2020, 10, 27);

    public static final Booking Booking_1 = new Booking(1001, 1, startDate_1, endDate_1, false);
    public static final Booking Booking_2 = new Booking(1002, 2,  startDate_1, endDate_1, false);
    public static final Booking Booking_3 = new Booking(1003, 3,  startDate_2, endDate_2, false);
    public static final Booking Booking_4 = new Booking(1004, 4,  startDate_2, endDate_2, false);
    public static final Booking Booking_5 = new Booking(1002, 5,  startDate_3, endDate_3, false);
    public static final Booking Booking_6 = new Booking(1001, 1,  startDate_3, endDate_3, false);

    private TypicalBookings() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static BookingBook getTypicalRoomBook() {
        BookingBook ab = new BookingBook();
        for (Booking r : getTypicalRooms()) {
            ab.addBooking(r);
        }
        return ab;
    }

    public static List<Booking> getTypicalRooms() {
        return new ArrayList<>(Arrays.asList(Booking_1, Booking_2, Booking_3, Booking_4, Booking_5, Booking_6));
    }

}
