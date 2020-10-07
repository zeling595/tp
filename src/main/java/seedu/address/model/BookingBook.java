package seedu.address.model;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.UniqueBookingList;

import java.util.List;

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

    public void addBooking(Booking bookingToAdd) {
        bookings.add(bookingToAdd);
    }

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

    //// util methods

    @Override
    public ObservableList<Booking> getBookingList() {
        return bookings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookingBook // instanceof handles nulls
                && bookings.equals(((BookingBook) other).bookings));
    }
}
