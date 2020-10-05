package seedu.address.model.room;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Room {

    // Identity fields
    private final int price;
    private final int roomID;

    /**
     * Every field must be present and not null.
     */
    public Room(int price, int roomID) {
        requireAllNonNull(price, roomID);
        this.price = price;
        this.roomID = roomID;
    }

    public int getRoomID() {
        return this.roomID;
    }

    public int getPrice() {
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
                && otherRoom.getPrice() == (getPrice())
                && otherRoom.getRoomID() == (getRoomID());
    }

    @Override
    public String toString() {
        return "Room{" + "price=" + price + ", roomID=" + roomID + '}';
    }
}
