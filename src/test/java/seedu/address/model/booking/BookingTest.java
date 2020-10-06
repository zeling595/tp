package seedu.address.model.booking;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.BookingBuilder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalBookings.BOOKING_1;
import static seedu.address.testutil.TypicalBookings.BOOKING_2;
import static seedu.address.testutil.TypicalBookings.BOOKING_AMY;


public class BookingTest {

    @Test
    public void hasConflict() {
        // other booking has same startDate, earlier endDate -> return true
        Booking conflictedAmy1 = new BookingBuilder(BOOKING_AMY).
                withEndDate(LocalDate.of(2020, 10,11)).build();
        assertTrue(conflictedAmy1.hasConflict(BOOKING_AMY));

        // other booking has later startDate, same endDate -> return true
        conflictedAmy1 = new BookingBuilder(BOOKING_AMY).
                withStartDate(LocalDate.of(2020, 10,3)).build();
        assertTrue(conflictedAmy1.hasConflict(BOOKING_AMY));

        // booking A starts on the day in which Booking B ends
        conflictedAmy1 = new BookingBuilder(BOOKING_AMY)
                .withStartDate(VALID_END_DATE_AMY)
                .withEndDate(LocalDate.of(2020, 10,11)).build();
        assertFalse(conflictedAmy1.hasConflict(BOOKING_AMY));

        // booking B starts on the day in which Booking A ends
        assertFalse(BOOKING_AMY.hasConflict(conflictedAmy1));
    }

    @Test
    public void getDuration() {
        // same value -> return true
        int Duration = BOOKING_AMY.getDuration();
        assertEquals(Duration, BOOKING_DURATION_AMY);

        // different value -> return false
        Duration = 6;
        assertNotEquals(Duration, BOOKING_DURATION_AMY);
    }

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


