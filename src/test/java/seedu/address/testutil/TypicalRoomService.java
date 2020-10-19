package seedu.address.testutil;

import seedu.address.model.RoomServiceBook;

public class TypicalRoomService {

    private TypicalRoomService() {} // prevents instantiation

    /**
     * Returns an {@code RoomServiceBook} with all the typical room services.
     */
    public static RoomServiceBook getTypicalRoomServiceBook() {
        return new RoomServiceBook();
    }
}
