package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOOKING_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOOKING_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.EditBookingCommand.MESSAGE_BOOKING_MISSING;
import static seedu.address.testutil.TypicalBookings.BOOKING_ID_1;
import static seedu.address.testutil.TypicalBookings.INVALID_BOOKING_ID;
import static seedu.address.testutil.TypicalBookings.getTypicalBookingBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRoomService.getTypicalRoomServiceBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.*;
import seedu.address.model.booking.Booking;
import seedu.address.testutil.BookingBuilder;
import seedu.address.testutil.EditBookingDescriptorBuilder;

public class EditBookingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalRoomBook(),
            getTypicalBookingBook(), getTypicalRoomServiceBook());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Booking editedBooking = new BookingBuilder().build();
        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder(editedBooking).build();
        EditBookingCommand editBookingCommand = new EditBookingCommand(BOOKING_ID_1, descriptor);

        String expectedMessage = String.format(EditBookingCommand.MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking);

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getRoomBook(), model.getBookingBook(),
                model.getRoomServiceBook());
        expectedModel.setBooking(model.getFilteredBookingList().get(0), editedBooking);

        assertCommandSuccess(editBookingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Booking firstBooking = model.getBookingWithId(BOOKING_ID_1);

        BookingBuilder bookingInList = new BookingBuilder(firstBooking);
        Booking editedBooking = bookingInList.withRoomId(VALID_ROOM_ID_BOB).withStartDate(VALID_START_DATE_BOB).build();

        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder()
                .withRoomId(VALID_ROOM_ID_BOB).withStartDate(VALID_START_DATE_BOB).build();
        EditBookingCommand editBookingCommand = new EditBookingCommand(BOOKING_ID_1, descriptor);

        String expectedMessage = String.format(EditBookingCommand.MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking);

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getRoomBook(),
                model.getBookingBook(), model.getRoomServiceBook());
        expectedModel.setBooking(firstBooking, editedBooking);

        assertCommandSuccess(editBookingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditBookingCommand editBookingCommand = new EditBookingCommand(BOOKING_ID_1,
                new EditBookingCommand.EditBookingDescriptor());
        Booking editedBooking = model.getBookingWithId(BOOKING_ID_1);

        String expectedMessage = String.format(EditBookingCommand.MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking);

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getRoomBook(),
                new BookingBook(model.getBookingBook()), model.getRoomServiceBook());

        assertCommandSuccess(editBookingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {

        Booking bookingInFilteredList = model.getBookingWithId(BOOKING_ID_1);
        Booking editedBooking = new BookingBuilder(bookingInFilteredList).withRoomId(VALID_ROOM_ID_BOB)
                .withStartDate(VALID_START_DATE_BOB).withEndDate(VALID_END_DATE_BOB).build();
        EditBookingCommand editBookingCommand = new EditBookingCommand(BOOKING_ID_1,
                new EditBookingDescriptorBuilder().withRoomId(VALID_ROOM_ID_BOB)
                        .withStartDate(VALID_START_DATE_BOB).withEndDate(VALID_END_DATE_BOB).build());

        String expectedMessage = String.format(EditBookingCommand.MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking);

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getRoomBook(),
                new BookingBook(model.getBookingBook()), model.getRoomServiceBook());
        expectedModel.setBooking(model.getFilteredBookingList().get(0), editedBooking);

        assertCommandSuccess(editBookingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidBookingIndexUnfilteredList_failure() {
        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder()
                .withRoomId(VALID_ROOM_ID_BOB).build();
        EditBookingCommand editBookingCommand = new EditBookingCommand(INVALID_BOOKING_ID, descriptor);

        assertCommandFailure(editBookingCommand, model, MESSAGE_BOOKING_MISSING);
    }

    @Test
    public void equals() {
        final EditBookingCommand standardCommand = new EditBookingCommand(BOOKING_ID_1, DESC_BOOKING_AMY);

        // same values -> returns true
        EditBookingCommand.EditBookingDescriptor copyDescriptor =
                new EditBookingCommand.EditBookingDescriptor(DESC_BOOKING_AMY);
        EditBookingCommand commandWithSameValues = new EditBookingCommand(BOOKING_ID_1, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditBookingCommand(INVALID_BOOKING_ID, DESC_BOOKING_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditBookingCommand(BOOKING_ID_1, DESC_BOOKING_BOB)));
    }

}
