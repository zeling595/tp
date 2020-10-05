package seedu.address.model;

import seedu.address.model.booking.Booking;
import seedu.address.model.booking.UniqueBookingList;

import static java.util.Objects.requireNonNull;

public class BookingBook implements ReadOnlyBookingBook {
    private final UniqueBookingList bookings;

    {
        bookings = new UniqueBookingList();
    }

    public BookingBook() {}

    @Override
    public void addBooking(Booking bookingToAdd) {
        bookings.add(bookingToAdd);
    }

    @Override
    public Booking getBooking(int roomID) {
        return bookings.getBooking(roomID);
    }

    @Override
    public void setBookingInactive(int RoomID) {
        bookings.setBookingInactive(RoomID);
    }
}
