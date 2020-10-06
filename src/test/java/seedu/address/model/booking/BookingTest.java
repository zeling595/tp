package seedu.address.model.booking;

import org.junit.jupiter.api.Test;
import seedu.address.model.booking.Booking;
import seedu.address.testutil.BookingBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalRooms.*;
import static seedu.address.testutil.TypicalRooms.ROOM_1;

public class BookingTest {

    @Test
    public void isSameBooking() {
        // same object -> return true
        assertTrue(DEFAULT_ROOM.equals(DEFAULT_ROOM));

        // null -> returns false
        assertFalse(DEFAULT_ROOM.equals(null));

        // different roomID and price -> return false
        Booking editedRoom1 = new RoomBuilder(ROOM_1).withRoomID(ROOM_2.getRoomID()).withPrice(ROOM_3.getPrice()).build();
        assertFalse(ROOM_1.equals(editedRoom1));

        // different roomID only -> return false
        editedRoom1 = new RoomBuilder(ROOM_1).withRoomID(ROOM_2.getRoomID()).build();
        assertFalse(ROOM_1.equals(editedRoom1));

        // different price only -> return false
        editedRoom1 = new RoomBuilder(ROOM_1).withPrice(200).build();
        assertFalse(ROOM_1.equals(editedRoom1));

        // same roomID and price -> return true
        editedRoom1 = new RoomBuilder(ROOM_2).withRoomID(ROOM_1.getRoomID()).withPrice(ROOM_1.getPrice()).build();
        assertTrue(ROOM_1.equals(editedRoom1));
    }

    @Test
    public void equals() {
        // same value -> return true
        Booking room1Copy = new RoomBuilder(ROOM_1).build();
        assertTrue(ROOM_1.equals(room1Copy));

        // same object -> return true
        assertTrue(ROOM_1.equals(ROOM_1));

        //null -> return false
        assertFalse(ROOM_1.equals(null));

        // different type -> returns false
        assertFalse(ROOM_1.equals(5));

        // different Booking -> return false
        assertFalse((ROOM_1.equals(ROOM_2)));

        // different price -> returns false
        Booking editedRoom1 = new RoomBuilder(ROOM_1).withPrice(200).build();
        assertFalse(ROOM_1.equals(editedRoom1));

        //different Booking ID -> return false
        editedRoom1 = new RoomBuilder(ROOM_1).withRoomID(ROOM_2.getRoomID()).build();
        assertFalse(ROOM_1.equals(editedRoom1));
    }
}
}
