package seedu.address.model.booking;

import java.util.function.Predicate;


/**
 * Tests that a {@code Booking}'s {@code roomId} matches any of the keywords given.
 */
public class BookingContainsRoomIdPredicate implements Predicate<Booking> {
    private final Integer roomId;

    public BookingContainsRoomIdPredicate(Integer roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean test(Booking booking) {
        return roomId.equals(booking.getRoomId());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookingContainsRoomIdPredicate // instanceof handles nulls
                && roomId.equals(((BookingContainsRoomIdPredicate) other).roomId)); // state check
    }

}
