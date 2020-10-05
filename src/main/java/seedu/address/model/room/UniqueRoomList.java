package seedu.address.model.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.room.exceptions.DuplicateRoomException;

public class UniqueRoomList implements Iterable<Room> {

    private final ObservableList<Room> internalRoomList = FXCollections.observableArrayList();
    private final ObservableList<Room> internalUnmodifiableRoomList =
            FXCollections.unmodifiableObservableList(internalRoomList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(int roomId) {
        return internalRoomList.stream().anyMatch(n -> n.getRoomID() == (roomId));
    }

    public Room getRoom(int roomId) {
        Room ret = new Room(-1, -1);
        for (int k = 0; k < internalRoomList.size(); k++) {
            Room curr = internalRoomList.get(k); // get the roomID
            if (curr.getRoomID() == roomId) {
                ret = curr;
            }
        }
        return ret;
    }

    /**
     * Returns an {@code ObservableList} of roomIDs that are not in the input
     * @param input {@code ObservableList} of roomIDs
     * @return {@code ObservableList} of rooms
     */
    public ObservableList<Integer> getComplementRooms(ObservableList<Integer> input) {
        ObservableList<Integer> ret = FXCollections.observableArrayList();
        for (int k = 0; k < internalRoomList.size(); k++) {
            int curr = internalRoomList.get(k).getRoomID(); // get the roomID
            if (!input.contains(curr)) {
                ret.add(curr);
            }
        }
        return ret;
    }

    /**
     * Adds a Room to the list.
     * The room must not already exist in the list.
     */
    public void add(Room toAdd) {
        requireNonNull(toAdd);
        if (internalRoomList.contains(toAdd)) {
            throw new DuplicateRoomException();
        }
        internalRoomList.add(toAdd);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setRooms(List<Room> rooms) {
        requireAllNonNull(rooms);
        if (!roomsAreUnique(rooms)) {
            throw new DuplicatePersonException();
        }

        internalRoomList.setAll(rooms);
    }
    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Room> asUnmodifiableObservableList() {
        return internalUnmodifiableRoomList;
    }

    @Override
    public Iterator<Room> iterator() {
        return internalRoomList.iterator();
    }

    /**
     * Returns true if {@code persons} contains only unique rooms.
     */
    private boolean roomsAreUnique(List<Room> rooms) {
        for (int i = 0; i < rooms.size() - 1; i++) {
            for (int j = i + 1; j < rooms.size(); j++) {
                if (rooms.get(i).equals(rooms.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
