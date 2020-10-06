package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.UniqueBookingList;

public class BookingBook implements ReadOnlyBookingBook {
    private final UniqueBookingList bookings;

    {
        bookings = new UniqueBookingList();
    }

    public BookingBook() {}

    @Override
    public ObservableList<Booking> getBookingList() {
        return bookings.asUnmodifiableObservableList();
    }

    public void addBooking(Booking bookingToAdd) {
        bookings.add(bookingToAdd);
    }

    public Booking getBooking(int roomID) {
        return bookings.getBooking(roomID);
    }

    public void setBookingInactive(int roomID) {
        bookings.setBookingInactive(roomID);
    }
}
