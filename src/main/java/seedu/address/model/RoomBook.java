package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.room.Room;
import seedu.address.model.room.UniqueRoomList;

public class RoomBook implements ReadOnlyRoomBook {

    private final UniqueRoomList rooms;

    {
        rooms = new UniqueRoomList();
    }

    public RoomBook() {}

    /// list level operations
    // change to list of integers
    public ObservableList<Room> getAvailableRooms(ObservableList<Room> unavailableRooms) {
        return rooms.getComplementRooms(unavailableRooms);
    }

    public void addRoom(Room r) {
        rooms.add(r);
    }

    /// room level operations
    public boolean hasRoom(int roomId) {
        return rooms.contains(roomId);
    }

    /// util methods
    @Override
    public ObservableList<Room> getRoomList() {
        return rooms.asUnmodifiableObservableList();
    }
}
