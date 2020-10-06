package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.UniqueBookingList;

import static java.util.Objects.requireNonNull;

public class BookingBook implements ReadOnlyBookingBook {
    private final UniqueBookingList bookings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        bookings = new UniqueBookingList();
    }

    public BookingBook() {}


    //// list overwrite operations


    //// booking-level operations

    public Booking getBooking(int roomID) {
        return bookings.getBooking(roomID);
    }

    public void setBookingInactive(int roomID) {
        bookings.setBookingInactive(roomID);
    }

    /**
     * Returns true if a booking with the same identity as {@code booking} exists in the address book.
     */
    public boolean hasBooking(Booking booking) {
        return bookings.contains(booking);
    }

    /**
     * Returns true if a person with the id is in the address book.
     */
    public boolean hasBookingWithId(Integer id) {
        requireNonNull(id);
        return bookings.hasBookingWithId(id);
    }

    public void addBooking(Booking bookingToAdd) {
        bookings.add(bookingToAdd);
    }

    //// util methods

    @Override
    public ObservableList<Booking> getBookingList() {
        return bookings.asUnmodifiableObservableList();
    }
}
