package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.RoomBuilder;

import static seedu.address.testutil.TypicalRooms.DEFAULT_ROOM;
import static seedu.address.testutil.TypicalRooms.DEFAULT_ROOMID;
import static seedu.address.testutil.TypicalRooms.ROOM_1;
import static seedu.address.testutil.TypicalRooms.ROOM_2;
import static seedu.address.testutil.TypicalRooms.ROOM_3;
import static seedu.address.testutil.TypicalRooms.ROOM_4;
import static seedu.address.testutil.TypicalRooms.ROOM_5;
import static seedu.address.testutil.TypicalRooms.ROOM_6;

public class RoomTest {

    @Test
    public void isSameRoom() {
        // same object -> return true
        assertTrue(DEFAULT_ROOM.equals(DEFAULT_ROOM));

        // null -> returns false
        assertFalse(DEFAULT_ROOM.equals(null));

        // different roomID and price -> return false
        Room edited_ROOM1 = new RoomBuilder(ROOM_1).withRoomID(ROOM_2.getRoomID()).withPrice(ROOM_3.getPrice()).build();
        assertFalse(ROOM_1.equals(edited_ROOM1));

        // different roomID only -> return false
        edited_ROOM1 = new RoomBuilder(ROOM_1).withRoomID(ROOM_2.getRoomID()).build();
        assertFalse(ROOM_1.equals(edited_ROOM1));

        // different price only -> return false
        edited_ROOM1 = new RoomBuilder(ROOM_1).withPrice(ROOM_2.getPrice()).build();
        assertFalse(ROOM_1.equals(edited_ROOM1));

        // same roomID and price -> return true
        edited_ROOM1 = new RoomBuilder(ROOM_2).withRoomID(ROOM_1.getRoomID()).withPrice(ROOM_1.getPrice()).build();
        assertTrue(ROOM_1.equals(edited_ROOM1));
    }

    @Test
    public void equals() {
        // same value -> return true
        Room ROOM1Copy = new RoomBuilder(ROOM_1).build();
        assertTrue(ROOM_1.equals(ROOM1Copy));

        // same object -> return true
        assertTrue(ROOM_1.equals(ROOM_1));

        //null -> return false
        assertFalse(ROOM_1.equals(null));

        // different type -> returns false
        assertFalse(ROOM_1.equals(5));

        // different room -> return false
        assertFalse((ROOM_1.equals(ROOM_2)));

        // different price -> returns false
        Room editedROOM_1 = new RoomBuilder(ROOM_1).withPrice(ROOM_2.getPrice()).build();
        assertFalse(ROOM_1.equals(editedROOM_1));

        //different room ID -> return false
        editedROOM_1 = new RoomBuilder(ROOM_1).withRoomID(ROOM_2.getRoomID()).build();
        assertFalse(ROOM_1.equals(editedROOM_1));
    }
}
