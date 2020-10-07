package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.exception.DuplicateBookingException;
import seedu.address.testutil.BookingBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBookings.*;

public class BookingBookTest {
    private final BookingBook bookingBook = new BookingBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), bookingBook.getBookingList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bookingBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyBookingBook_replacesData() {
        BookingBook newData = getTypicalBookingBook();
        bookingBook.resetData(newData);
        assertEquals(newData, bookingBook);
    }

    @Test
    public void resetData_withDuplicateBookings_throwsDuplicateBookingException() {
        // Two booking with the same identity fields
        Booking editedBookingAmy = new BookingBuilder(BOOKING_BOB)
                .withRoomId(VALID_ROOM_ID_AMY)
                .withPersonId(VALID_PERSONAL_ID_AMY)
                .withStartDate(VALID_START_DATE_AMY)
                .withEndDate(VALID_END_DATE_AMY).build();

        List<Booking> newBookings = Arrays.asList(BOOKING_AMY, editedBookingAmy);

        BookingBookTest.BookingBookStub newData = new BookingBookTest.BookingBookStub(newBookings);

        assertThrows(DuplicateBookingException.class, () -> bookingBook.resetData(newData));
    }

    @Test
    public void hasBooking_nullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bookingBook.hasBooking(null));
    }

    @Test
    public void hasBooking_bookingNotInBookingBook_returnsFalse() {
        assertFalse(bookingBook.hasBooking(BOOKING_AMY));
    }

    @Test
    public void hasBooking_bookingInBookingBook_returnsTrue() {
        bookingBook.addBooking(BOOKING_AMY);
        assertTrue(bookingBook.hasBooking(BOOKING_AMY));
    }

    @Test
    public void hasBooking_bookingWithSameIdentityFieldsInBookingBook_returnsTrue() {
        bookingBook.addBooking(BOOKING_AMY);
        Booking editedBookingAmy = new BookingBuilder(BOOKING_BOB)
                .withRoomId(VALID_BOOKING_ID_AMY)
                .withPersonId(VALID_PERSONAL_ID_AMY)
                .withStartDate(VALID_START_DATE_AMY)
                .withEndDate(VALID_END_DATE_AMY).build();

        assertTrue(bookingBook.hasBooking(editedBookingAmy));
    }

    @Test
    public void getBookingList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> bookingBook.getBookingList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class BookingBookStub implements ReadOnlyBookingBook {
        private final ObservableList<Booking> bookings = FXCollections.observableArrayList();

        BookingBookStub(Collection<Booking> bookings) {
            this.bookings.setAll(bookings);
        }

        @Override
        public ObservableList<Booking> getBookingList() {
            return bookings;
        }
    }

}
