package seedu.address.model.roomservice;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Class for different types of room service
 */
public class RoomService {

    // Identity fields
    private final Integer bookingId;
    private final RoomServiceType type;

    public RoomService(Integer bookingId, RoomServiceType type) {
        requireAllNonNull(bookingId);
        this.bookingId = bookingId;
        this.type = type;
    }

    public Integer getBookingId() {
        return this.bookingId;
    }

    public boolean belongsToBooking(Integer bookingId) {
        return this.getBookingId().equals(bookingId);
    }

    public RoomServiceType getType() {
        return this.type;
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
        return otherRoomService.getBookingId().equals(getBookingId())
                && otherRoomService.type == type;
    }
}
