package seedu.address.model.room;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Room {

    // Identity fields
    private final Price price;
    private final RoomID roomID;

    /**
     * Every field must be present and not null.
     */
    public Room(Price price, RoomID roomID) {
        requireAllNonNull(price, roomID);
        this.price = price;
        this.roomID = roomID;
    }

    public RoomID getRoomID() {
        return this.roomID;
    }

    public Price getPrice() {
        return this.price;
    }

    /**
     * Returns true if both rooms have the same price and roomId.
     */
    public boolean equals(Room otherRoom) {
        if (otherRoom == this) {
            return true;
        }

        return otherRoom != null
                && otherRoom.getPrice().equals(getPrice())
                && otherRoom.getRoomID().equals(getRoomID());
    }

    @Override
    public String toString() {
        return "Room{" + "price=" + price + ", roomID=" + roomID + '}';
    }
}
