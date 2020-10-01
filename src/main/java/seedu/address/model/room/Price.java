package seedu.address.model.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Price {

    public final int value;

    /**
     * Constructs a {@code Price}
     * @param value a valid Price.
     */
    public Price (int value) {
        requireNonNull(value);
        checkArgument(value >= 0);
        this.value = value;
    }

    public static boolean isValidPrice(int test) {
        return test >= 0;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
