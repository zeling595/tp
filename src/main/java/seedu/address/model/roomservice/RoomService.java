package seedu.address.model.roomservice;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Abstract class for different types of room service
 */
public class RoomService {

    // Identity fields
    protected final Integer bookingId;


    protected RoomService(Integer bookingId) {
        requireAllNonNull(bookingId);
        this.bookingId = bookingId;
    }

    public Integer getBookingId() {
        return this.bookingId;
    }

    public boolean belongsToBooking(Integer bookingId) {
        return this.getBookingId().equals(bookingId);
    }



    /**
     * Returns true if both room service have the same booking id
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RoomService)) {
            return false;
        }

        RoomService otherRoomService = (RoomService) other;
        return otherRoomService.getBookingId().equals(getBookingId());
    }
}
