package seedu.address.testutil;

import seedu.address.model.room.Price;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomID;

/**
 * A utility class to help with building Room objects.
 */
public class RoomBuilder {
    public static final RoomID DEFAULT_ROOMID = new RoomID(1234);
    public static final Price DEFAULT_PRICE = new Price(50);

    private RoomID roomId;
    private Price price;

    /**
     * Default Constructor for RoomBuilder.
     */
    public RoomBuilder() {
        this.price = DEFAULT_PRICE;
        this.roomId = DEFAULT_ROOMID;
    }

    /**
     * Constructs a {@code RoomBuilder}
     * @param tobeCopied a valid Room.
     */
    public RoomBuilder(Room tobeCopied) {
        this.roomId = tobeCopied.getRoomID();
        this.price = tobeCopied.getPrice();
    }

    /**
     * Sets RoomBuilder with new RoomID.
     * @param roomId new RoomID
     * @return a {@code RoomBuilder} with modified {@code RoomID}
     */
    public RoomBuilder withRoomID(RoomID roomId) {
        this.roomId = roomId;
        return this;
    }

    /**
     * Sets RoomBuilder with new Price.
     * @param price new Price
     * @return a {@code RoomBuilder} with modified {@code Price}
     */
    public RoomBuilder withPrice(Price price) {
        this.price = price;
        return this;
    }

    /**
     * Creates a Room object from the current fields.
     * @return {@code Room}
     */
    public Room build() {
        return new Room(this.price, this.roomId);
    }
}
