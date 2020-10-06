package seedu.address.model.booking;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.BookingBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSONAL_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_BOB;
import static seedu.address.testutil.TypicalBookings.BOOKING_AMY;


public class BookingTest {

    @Test
    public void equals() {
        // same value -> return true
        Booking bookingAmyCopy = new BookingBuilder(BOOKING_AMY).build();
        assertTrue(bookingAmyCopy.equals(BOOKING_AMY));

        // same object -> return true
        assertTrue(BOOKING_AMY.equals(BOOKING_AMY));

        //null -> return false
        assertFalse(BOOKING_AMY.equals(null));

        // different type -> returns false
        assertFalse(BOOKING_AMY.equals(5));

        // different roomId -> return false
        Booking editedBookingAmy = new BookingBuilder(BOOKING_AMY).withRoomId(VALID_ROOM_ID_BOB).build();
        assertFalse((BOOKING_AMY.equals(editedBookingAmy)));

        // different personId -> return false
        editedBookingAmy = new BookingBuilder(BOOKING_AMY).withPersonId(VALID_PERSONAL_ID_BOB).build();
        assertFalse((BOOKING_AMY.equals(editedBookingAmy)));

        // different startDate -> return false
        editedBookingAmy = new BookingBuilder(BOOKING_AMY).withStartDate(VALID_START_DATE_BOB).build();
        assertFalse((BOOKING_AMY.equals(editedBookingAmy)));

        // different endDate -> return false
        editedBookingAmy = new BookingBuilder(BOOKING_AMY).withEndDate(VALID_END_DATE_BOB).build();
        assertFalse((BOOKING_AMY.equals(editedBookingAmy)));

        // different isActive -> return false
        editedBookingAmy = new BookingBuilder(BOOKING_AMY).withIsActive(true).build();
        assertFalse((BOOKING_AMY.equals(editedBookingAmy)));

        //different Booking Id -> return false
        editedBookingAmy = new BookingBuilder(BOOKING_AMY).withId(VALID_BOOKING_ID_BOB).build();
        assertFalse(BOOKING_AMY.equals(editedBookingAmy));
    }
}


