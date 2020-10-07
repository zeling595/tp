package seedu.address.model.booking;

import org.junit.jupiter.api.Test;
import seedu.address.model.booking.exception.ConflictingBookingException;
import seedu.address.model.booking.exception.DuplicateBookingException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.BookingBuilder;
import seedu.address.testutil.PersonBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBookings.*;


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

//    @Test
//    public void contains_bookingWithSameIdentityFieldsInList_returnsTrue() {
//        uniqueBookingList.add(BOOKING_BOB);
//        Booking editedBookingBob = new BookingBuilder()
//        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
//                .build();
//        assertTrue(uniquePersonList.contains(editedAlice));
//    }

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
}
