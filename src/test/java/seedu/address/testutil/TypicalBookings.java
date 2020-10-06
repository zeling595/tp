package seedu.address.testutil;

import seedu.address.model.booking.Booking;
import seedu.address.model.booking.BookingBook;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.address.logic.commands.CommandTestUtil.*;

public class TypicalBookings {

    public static final LocalDate startDate_1 = LocalDate.of(2020, 10, 20);
    public static final LocalDate endDate_1 = LocalDate.of(2020, 10, 25);
    public static final LocalDate startDate_2 = LocalDate.of(2020, 10, 23);
    public static final LocalDate endDate_2 = LocalDate.of(2020, 10, 26);
    public static final LocalDate startDate_3 = LocalDate.of(2020, 10, 26);
    public static final LocalDate endDate_3 = LocalDate.of(2020, 10, 27);

    public static final Booking BOOKING_1 = new Booking(1235, 1, startDate_1, endDate_1, false);
    public static final Booking BOOKING_2 = new Booking(1236, 2,  startDate_1, endDate_1, false);
    public static final Booking BOOKING_3 = new Booking(1237, 3,  startDate_2, endDate_2, false);
    public static final Booking BOOKING_4 = new Booking(1238, 4,  startDate_2, endDate_2, false);
    public static final Booking BOOKING_5 = new Booking(1236, 5,  startDate_3, endDate_3, false);
    public static final Booking BOOKING_6 = new Booking(1235, 1,  startDate_3, endDate_3, false);

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Booking BOOKING_AMY = new Booking(4102, 23, VALID_START_DATE_AMY,
            VALID_END_DATE_AMY, false);
    public static final Booking BOOKING_BOB = new Booking(2301, 12, VALID_START_DATE_BOB,
            VALID_END_DATE_BOB, false);

    private TypicalBookings() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static BookingBook getTypicalBookingBook() {
        BookingBook ab = new BookingBook();
        for (Booking r : getTypicalBookings()) {
            ab.addBooking(r);
        }
        return ab;
    }

    public static List<Booking> getTypicalBookings() {
        return new ArrayList<>(Arrays.asList(BOOKING_1, BOOKING_2, BOOKING_3, BOOKING_4, BOOKING_5, BOOKING_6));
    }

}
