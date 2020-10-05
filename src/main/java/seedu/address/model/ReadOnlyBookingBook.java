package seedu.address.model;

import seedu.address.model.booking.Booking;

public interface ReadOnlyBookingBook {
    void addBooking(Booking bookingToAdd); // check not clash with other booking,  time, id
    Booking getBooking(int roomID); // get booking
    void setBookingInactive(int roomID); // two currently active room id
}
