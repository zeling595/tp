package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Address;

public class PriceTest {

    @Test
    public void constructor_invalidPrice_throwsIllegalArgumentException() {
        int invalidPrice= -15;
        assertThrows(IllegalArgumentException.class, () -> new Price(invalidPrice));
    }

    @Test
    public void isValidPrice() {
        //invalid price
        assertFalse(Price.isValidPrice(-15)); // negative price

        //valid price
        assertTrue(Price.isValidPrice(0));
        assertTrue(Price.isValidPrice(70));
        assertTrue(Price.isValidPrice(1000));

    }
}
