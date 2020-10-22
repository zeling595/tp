package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.room.Room;
import seedu.address.model.room.UniqueRoomList;


public class RoomBook implements ReadOnlyRoomBook {

    private final UniqueRoomList rooms;

    {
        rooms = new UniqueRoomList();
    }

    public RoomBook() {}

    /**
     * Creates an RoomBook using the rooms in the {@code toBeCopied}
     */
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
        assert !rooms.isEmpty();
        this.rooms.setRooms(rooms);
    }

    /**
     * Returns a {@code String} that lists in rooms according to room types.
     */
    public String displayRooms(ObservableList<Integer> input) {
        return rooms.getAllRooms(input);
    }

    /**
     * Returns a {@code String} that lists only Single Rooms.
     */
    public String getSingleRooms(ObservableList<Integer> input) {
        return rooms.getSingleRooms(input);
    }

    /**
     * Returns a {@code String} that lists only Double Rooms.
     */
    public String getDoubleRooms(ObservableList<Integer> input) {
        return rooms.getDoubleRooms(input);
    }

    /**
     * Returns a {@code String} that lists only Suite Rooms.
     */
    public String getSuiteRooms(ObservableList<Integer> input) {
        return rooms.getSuiteRooms(input);
    }

    /**
     * Resets the existing data of this {@code RoomBook} with {@code newData}.
     */
    public void resetData(ReadOnlyRoomBook newData) {
        requireNonNull(newData);

        setRooms(newData.getRoomList());
    }

    /// room level operations
    public boolean hasRoom(int roomId) {
        return rooms.contains(roomId);
    }

    public Room getRoom(int roomId) {
        return rooms.getRoom(roomId);
    }

    /// util methods
    @Override
    public ObservableList<Room> getRoomList() {
        return rooms.asUnmodifiableObservableList();
    }
}
