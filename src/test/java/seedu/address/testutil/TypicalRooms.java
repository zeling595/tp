package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.RoomBook;
import seedu.address.model.room.Room;
import seedu.address.model.room.Single;

/**
 * Initiates a Hotel that has 5 Singles, 5 Doubles and 5 Suites.
 */
public class TypicalRooms {

    public static final Room DEFAULT_ROOM = new Single(2103);
    public static final int DEFAULT_ROOMID = 2103;

    public static final Room SINGLE_1 = new Single(2104);
    public static final Room SINGLE_2 = new Single(2105);
    public static final Room SINGLE_3 = new Single(2106);
    public static final Room SINLGE_4 = new Single(2107);
    public static final Room SINGLE_5 = new Single(2108);

    public static final Room DOUBLE_1 = new Single(2113);
    public static final Room DOUBLE_2 = new Single(2114);
    public static final Room DOUBLE_3 = new Single(2115);
    public static final Room DOUBLE_4 = new Single(2116);
    public static final Room DOUBLE_5 = new Single(2117);

    public static final Room SUITE_1 = new Single(2123);
    public static final Room SUITE_2 = new Single(2123);
    public static final Room SUITE_3 = new Single(2123);
    public static final Room SUITE_4 = new Single(2123);
    public static final Room SUITE_5 = new Single(2123);


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
