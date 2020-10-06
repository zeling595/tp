package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.UniqueBookingList;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class BookingBook implements ReadOnlyBookingBook {
    private final UniqueBookingList bookings;

    {
        bookings = new UniqueBookingList();
    }

    public BookingBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public BookingBook(ReadOnlyBookingBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the booking list with {@code bookings}.
     * {@code persons} must not contain duplicate bookings.
     */
    public void setBookings(List<Booking> bookings) {
        this.bookings.setBookings(bookings);
    }

    /**
     * Resets the existing data of this {@code BookingBook} with {@code newData}.
     */
    public void resetData(ReadOnlyBookingBook newData) {
        requireNonNull(newData);
        setBookings(newData.getBookingList());
    }

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
