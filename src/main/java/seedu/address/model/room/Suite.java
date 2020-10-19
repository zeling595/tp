package seedu.address.model.room;

public class Suite extends Room {
    private int SUITE_PRICE = 150;

    /**
     * Creates a Suite Room Object with the standard {@code SUITE_PRICE} and given room ID.
     */
    public Suite(int roomID) {
        super(roomID);
        this.price = SUITE_PRICE;
    }
}
