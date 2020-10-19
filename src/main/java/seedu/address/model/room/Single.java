package seedu.address.model.room;

public class Single extends Room {
    private int SINGLE_PRICE = 70;
    /**
     * Creates a Single Room Object with the standard {@code SINGLE_PRICE} and given room ID.
     */
    public Single(int roomID) {
        super(roomID);
        this.price = SINGLE_PRICE;
    }
}
