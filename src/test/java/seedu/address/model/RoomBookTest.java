package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRooms.ROOM_1;

import java.util.Collections;

import org.junit.jupiter.api.Test;

public class RoomBookTest {

    private final RoomBook roomBook = new RoomBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), roomBook.getRoomList());
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(roomBook.hasRoom(ROOM_1.getRoomID().id));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        roomBook.addRoom(ROOM_1);
        assertTrue(roomBook.hasRoom(ROOM_1.getRoomID().id));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> roomBook.getRoomList().remove(0));
    }
}
