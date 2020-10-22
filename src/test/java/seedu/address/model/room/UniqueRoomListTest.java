package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRooms.DEFAULT_ROOM;
import static seedu.address.testutil.TypicalRooms.DEFAULT_ROOMID;
import static seedu.address.testutil.TypicalRooms.SINGLE_1;
import static seedu.address.testutil.TypicalRooms.SINGLE_2;
import static seedu.address.testutil.TypicalRooms.DOUBLE_1;
import static seedu.address.testutil.TypicalRooms.DOUBLE_2;
import static seedu.address.testutil.TypicalRooms.SUITE_1;
import static seedu.address.testutil.TypicalRooms.SUITE_2;

import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.room.exceptions.DuplicateRoomException;


public class UniqueRoomListTest {

    private final UniqueRoomList uniqueRoomList = new UniqueRoomList();

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueRoomList.contains(DEFAULT_ROOMID));
    }

    @Test public void contains_roomInList_returnsTrue() {
        uniqueRoomList.add(DEFAULT_ROOM);
        assertTrue(uniqueRoomList.contains(DEFAULT_ROOMID));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoomList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueRoomList.add(DEFAULT_ROOM);
        assertThrows(DuplicateRoomException.class, () -> uniqueRoomList.add(DEFAULT_ROOM));
    }

    @Test
    public void getComplementRooms_returnsCorrectList() {
        List<Room> rooms = FXCollections.observableArrayList(SINGLE_1, SINGLE_2, DOUBLE_1, DOUBLE_2, SUITE_1, SUITE_2);
        uniqueRoomList.setRooms(rooms);
//        List<Integer> roomId = FXCollections.observableArrayList(SINGLE_1.getRoomID(),
//                                                                    SINGLE_2.getRoomID(),
//                                                                    DOUBLE_1.getRoomID(),
//                                                                    DOUBLE_2.getRoomID(),
//                                                                    SUITE_1.getRoomID(),
//                                                                    SUITE_2.getRoomID());
        ObservableList<Integer> input = FXCollections.observableArrayList(SINGLE_1.getRoomID(),
                                                                            DOUBLE_1.getRoomID(),
                                                                            SUITE_1.getRoomID());
        List<Integer> output = uniqueRoomList.getComplementRooms(input);
        assertTrue(output.contains(SINGLE_2.getRoomID())
                            && output.contains(DOUBLE_2.getRoomID())
                            && output.contains(SUITE_2.getRoomID())
                            && !output.contains(SINGLE_1.getRoomID())
                            && !output.contains(DOUBLE_1.getRoomID())
                            && !output.contains(SUITE_1.getRoomID()));
    }
    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
            uniqueRoomList.asUnmodifiableObservableList().remove(0));
    }
}
