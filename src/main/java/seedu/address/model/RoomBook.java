package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.room.Room;
import seedu.address.model.room.UniqueRoomList;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class RoomBook implements ReadOnlyRoomBook {

    private final UniqueRoomList rooms;

    {
        rooms = new UniqueRoomList();
    }

    public RoomBook() {}

    public RoomBook(ReadOnlyRoomBook tobeCopied) {
        this();
        resetData(tobeCopied);
    }

    /// list level operations
    public ObservableList<Integer> getAvailableRooms(ObservableList<Integer> unavailableRooms) {
        return rooms.getComplementRooms(unavailableRooms);
    }

    public void addRoom(Room r) {
        rooms.add(r);
    }

    public void setRooms(List<Room> rooms) {
        this.rooms.setRooms(rooms);
    }

    public void resetData(ReadOnlyRoomBook newData) {
        requireNonNull(newData);

        setRooms(newData.getRoomList());
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
