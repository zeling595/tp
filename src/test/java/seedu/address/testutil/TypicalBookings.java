package seedu.address.testutil;

import seedu.address.model.booking.Booking;
import seedu.address.model.BookingBook;

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

    public static final Booking BOOKING_1 = new Booking(1235, 1, startDate_1, endDate_1, false,
            1);
    public static final Booking BOOKING_2 = new Booking(1236, 2,  startDate_1, endDate_1, false,
            2);
    public static final Booking BOOKING_3 = new Booking(1237, 3,  startDate_2, endDate_2, false,
            3);
    public static final Booking BOOKING_4 = new Booking(1238, 4,  startDate_2, endDate_2, false,
            4);
    public static final Booking BOOKING_5 = new Booking(1236, 5,  startDate_3, endDate_3, false,
            5);
    public static final Booking BOOKING_6 = new Booking(1235, 1,  startDate_3, endDate_3, false,
            6);

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Booking BOOKING_AMY = new Booking(VALID_ROOM_ID_AMY, VALID_PERSONAL_ID_AMY, VALID_START_DATE_AMY,
            VALID_END_DATE_AMY, false, VALID_BOOKING_ID_AMY);
    public static final Booking BOOKING_BOB = new Booking(VALID_ROOM_ID_BOB, VALID_PERSONAL_ID_BOB, VALID_START_DATE_BOB,
            VALID_END_DATE_BOB, false, VALID_BOOKING_ID_BOB);
    public static final Booking CONFLICT_AMY_BOOKING_CHLOE = new Booking(VALID_ROOM_ID_AMY, VALID_PERSONAL_ID_CHLOE,
            CONFLICT_AMY_START_DATE_CHLOE, CONFLICT_AMY_END_DATE_CHLOE, false, VALID_BOOKING_ID_AMY);

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
