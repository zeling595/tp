package seedu.address.model.room;

public class Double extends Room {
    private int DOUBLE_PRICE = 100;

    /**
     * Creates a Double Room Object with the standard {@code DOUBLE_PRICE} and given room ID.
     */
    public Double(int roomID) {
        super(roomID);
        this.price = DOUBLE_PRICE;
    }
}
