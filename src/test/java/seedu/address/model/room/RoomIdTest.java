package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoomIdTest {

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        int invalidRoomId = 123;
        assertThrows(IllegalArgumentException.class, () -> new RoomID(invalidRoomId));
    }

    @Test
    public void isValidRoomID() {
        //invalid Room ID
        assertFalse(RoomID.isValidRoomID("1"));
        assertFalse(RoomID.isValidRoomID("-123"));
        assertFalse(RoomID.isValidRoomID("98765"));

        //valid Room ID
        assertTrue(RoomID.isValidRoomID("1234"));
        assertTrue(RoomID.isValidRoomID("0987"));
    }
}
