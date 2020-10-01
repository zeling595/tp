package seedu.address.testutil;

import seedu.address.model.person.Person;
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

    public RoomBuilder() {
        this.price = DEFAULT_PRICE;
        this.roomId = DEFAULT_ROOMID;
    }

    public RoomBuilder(Room tobeCopied) {
        this.roomId = tobeCopied.getRoomID();
        this.price = tobeCopied.getPrice();
    }

    public RoomBuilder withRoomID(RoomID roomId) {
        this.roomId = roomId;
        return this;
    }

    public RoomBuilder withPrice(Price price) {
        this.price = price;
        return this;
    }

    public Room build() { return new Room(this.price, this.roomId); }
}
