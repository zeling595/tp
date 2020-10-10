package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBookings.BOOKING_AMY;
import static seedu.address.testutil.TypicalBookings.BOOKING_BOB;
import static seedu.address.testutil.TypicalBookings.CONFLICT_AMY_BOOKING_CHLOE;

import org.junit.jupiter.api.Test;

import seedu.address.model.booking.exception.BookingNotFoundException;
import seedu.address.model.booking.exception.ConflictingBookingException;
import seedu.address.model.booking.exception.DuplicateBookingException;
import seedu.address.testutil.BookingBuilder;


public class UniqueBookingListTest {
    private final UniqueBookingList uniqueBookingList = new UniqueBookingList();

    @Test
    public void contains_nullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.contains(null));
    }

    @Test
    public void contains_bookingNotInList_returnsFalse() {
        assertFalse(uniqueBookingList.contains(BOOKING_BOB));
    }

    @Test
    public void contains_bookingInList_returnsTrue() {
        uniqueBookingList.add(BOOKING_BOB);
        assertTrue(uniqueBookingList.contains(BOOKING_BOB));
    }

    @Test
    public void add_nullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.add(null));
    }

    @Test
    public void add_duplicateBooking_throwsDuplicateBookingException() {
        uniqueBookingList.add(BOOKING_BOB);
        assertThrows(DuplicateBookingException.class, () -> uniqueBookingList.add(BOOKING_BOB));
    }

    @Test
    public void add_conflictingBooking_throwsConflictingBookingException() {
        uniqueBookingList.add(BOOKING_AMY);
        assertThrows(ConflictingBookingException.class, () -> uniqueBookingList.add(CONFLICT_AMY_BOOKING_CHLOE));
    }

    @Test
    public void setBooking_nullTargetBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.setBooking(null, BOOKING_AMY));
    }

    @Test
    public void setBooking_nullEditedBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.setBooking(BOOKING_AMY, null));
    }

    @Test
    public void setBooking_targetBookingNotInList_throwsBookingNotFoundException() {
        assertThrows(BookingNotFoundException.class, () -> uniqueBookingList.setBooking(BOOKING_AMY, BOOKING_AMY));
    }

    @Test
    public void setBooking_editedBookingIsSameBooking_success() {
        uniqueBookingList.add(BOOKING_AMY);
        uniqueBookingList.setBooking(BOOKING_AMY, BOOKING_AMY);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(BOOKING_AMY);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setBooking_editedBookingHasSameIdentity_success() {
        uniqueBookingList.add(BOOKING_AMY);
        Booking editedBookingAmy = new BookingBuilder(BOOKING_AMY).withRoomId(VALID_ROOM_ID_BOB).build();
        uniqueBookingList.setBooking(BOOKING_AMY, editedBookingAmy);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(editedBookingAmy);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

}
