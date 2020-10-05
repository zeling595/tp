package seedu.address.model.booking;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.booking.exception.ConflictingBookingException;
import seedu.address.model.booking.exception.BookingNotFoundException;

import java.util.Iterator;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UniqueBookingList implements Iterable<Booking> {
    private final ObservableList<Booking> internalList = FXCollections.observableArrayList();
    private final ObservableList<Booking> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a booking to the list.
     * The booking must not already exist in the list.
     */
    public void add(Booking toAdd) {
        requireNonNull(toAdd);
        // check if conflict
        boolean hasConflict;
        hasConflict = internalList.stream().anyMatch(booking -> booking.hasConflict(toAdd));
        if (hasConflict) {
            throw new ConflictingBookingException();
        } else {
            internalList.add(toAdd);
        }
    }

    public Booking getBooking(int roomID) {
        requireNonNull(roomID);
        return internalList.stream()
                .filter(booking -> booking.getId() == roomID)
                .filter(Booking::isActive)
                .findFirst().orElseThrow(() -> new BookingNotFoundException());
    }

    public void setBookingInactive(int roomID) {
        requireNonNull(roomID);
        Booking booking = getBooking(roomID);
        Booking editedBooking = new Booking(booking.getRoomId(), booking.getPersonId(),
                booking.getStartDate(), booking.getEndDate(), false);
        setBooking(booking, editedBooking);
    }

    public void setBooking(Booking target, Booking editedBooking) {
        requireAllNonNull(target, editedBooking);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BookingNotFoundException();
        }

        internalList.set(index, editedBooking);
    }

    @Override
    public Iterator<Booking> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueBookingList // instanceof handles nulls
                && internalList.equals(((UniqueBookingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
