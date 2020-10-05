package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.RoomBook;
import seedu.address.model.room.Room;

public class TypicalRooms {

    public static final Room DEFAULT_ROOM = new Room(100, 1234);
    public static final int DEFAULT_ROOMID = 1234;

    public static final Room ROOM_1 = new Room(100, 1235);
    public static final Room ROOM_2 = new Room(100, 1236);
    public static final Room ROOM_3 = new Room(100, 1237);
    public static final Room ROOM_4 = new Room(100, 1238);
    public static final Room ROOM_5 = new Room(100, 1239);
    public static final Room ROOM_6 = new Room(100, 1240);

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
