package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.RoomBook;
import seedu.address.model.room.Room;
import seedu.address.model.room.Single;

public class TypicalRooms {

    public static final Room DEFAULT_ROOM = new Single(2103);
    public static final int DEFAULT_ROOMID = 2103;

    public static final Room ROOM_1 = new Single(2104);
    public static final Room ROOM_2 = new Single(2105);
    public static final Room ROOM_3 = new Single(2106);
    public static final Room ROOM_4 = new Single(2107);
    public static final Room ROOM_5 = new Single(2108);
    public static final Room ROOM_6 = new Single(2109);

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static RoomBook getTypicalRoomBook() {
        RoomBook ab = new RoomBook();
        for (Room r : getTypicalRooms()) {
            ab.addRoom(r);
        }
        return ab;
    }

    public static List<Room> getTypicalRooms() {
        return new ArrayList<>(Arrays.asList(ROOM_1, ROOM_2, ROOM_3, ROOM_4, ROOM_5, ROOM_6));
    }

}
