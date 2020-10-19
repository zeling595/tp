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
import static seedu.address.logic.commands.CommandTestUtil.showBookingAtIndex;
import static seedu.address.testutil.TypicalBookings.getTypicalBookingBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BOOKING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BOOKING;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.*;
import seedu.address.model.booking.Booking;
import seedu.address.testutil.BookingBuilder;
import seedu.address.testutil.EditBookingDescriptorBuilder;

public class EditBookingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalRoomBook(),
            getTypicalBookingBook());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Booking editedBooking = new BookingBuilder().build();
        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder(editedBooking).build();
        EditBookingCommand editBookingCommand = new EditBookingCommand(INDEX_FIRST_BOOKING, descriptor);

        String expectedMessage = String.format(EditBookingCommand.MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking);

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getRoomBook(), model.getBookingBook());
        expectedModel.setBooking(model.getFilteredBookingList().get(0), editedBooking);

        assertCommandSuccess(editBookingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastBooking = Index.fromOneBased(model.getFilteredBookingList().size());
        Booking lastBooking = model.getFilteredBookingList().get(indexLastBooking.getZeroBased());

        BookingBuilder bookingInList = new BookingBuilder(lastBooking);
        Booking editedBooking = bookingInList.withRoomId(VALID_ROOM_ID_BOB).withStartDate(VALID_START_DATE_BOB).build();

        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder()
                .withRoomId(VALID_ROOM_ID_BOB).withStartDate(VALID_START_DATE_BOB).build();
        EditBookingCommand editBookingCommand = new EditBookingCommand(indexLastBooking, descriptor);

        String expectedMessage = String.format(EditBookingCommand.MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking);

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getRoomBook(), model.getBookingBook());
        expectedModel.setBooking(lastBooking, editedBooking);

        assertCommandSuccess(editBookingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditBookingCommand editBookingCommand = new EditBookingCommand(INDEX_FIRST_BOOKING,
                new EditBookingCommand.EditBookingDescriptor());
        Booking editedBooking = model.getFilteredBookingList().get(INDEX_FIRST_BOOKING.getZeroBased());

        String expectedMessage = String.format(EditBookingCommand.MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking);

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getRoomBook(),
                new BookingBook(model.getBookingBook()));

        assertCommandSuccess(editBookingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showBookingAtIndex(model, INDEX_FIRST_BOOKING);

        Booking bookingInFilteredList = model.getFilteredBookingList().get(INDEX_FIRST_BOOKING.getZeroBased());
        Booking editedBooking = new BookingBuilder(bookingInFilteredList).withRoomId(VALID_ROOM_ID_BOB)
                .withStartDate(VALID_START_DATE_BOB).withEndDate(VALID_END_DATE_BOB).build();
        EditBookingCommand editBookingCommand = new EditBookingCommand(INDEX_FIRST_BOOKING,
                new EditBookingDescriptorBuilder().withRoomId(VALID_ROOM_ID_BOB)
                        .withStartDate(VALID_START_DATE_BOB).withEndDate(VALID_END_DATE_BOB).build());

        String expectedMessage = String.format(EditBookingCommand.MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking);

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), model.getRoomBook(),
                new BookingBook(model.getBookingBook()));
        expectedModel.setBooking(model.getFilteredBookingList().get(0), editedBooking);

        assertCommandSuccess(editBookingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidBookingIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookingList().size() + 1);
        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder()
                .withRoomId(VALID_ROOM_ID_BOB).build();
        EditBookingCommand editBookingCommand = new EditBookingCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editBookingCommand, model, Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of booking book
     */
    @Test
    public void execute_invalidBookingIndexFilteredList_failure() {
        showBookingAtIndex(model, INDEX_FIRST_BOOKING);
        Index outOfBoundIndex = INDEX_SECOND_BOOKING;
        // ensures that outOfBoundIndex is still in bounds of booking book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getBookingBook().getBookingList().size());

        EditBookingCommand editBookingCommand = new EditBookingCommand(outOfBoundIndex,
                new EditBookingDescriptorBuilder().withRoomId(VALID_ROOM_ID_BOB).build());

        assertCommandFailure(editBookingCommand, model, Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditBookingCommand standardCommand = new EditBookingCommand(INDEX_FIRST_BOOKING, DESC_BOOKING_AMY);

        // same values -> returns true
        EditBookingCommand.EditBookingDescriptor copyDescriptor =
                new EditBookingCommand.EditBookingDescriptor(DESC_BOOKING_AMY);
        EditBookingCommand commandWithSameValues = new EditBookingCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditBookingCommand(INDEX_SECOND_BOOKING, DESC_BOOKING_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditBookingCommand(INDEX_FIRST_BOOKING, DESC_BOOKING_BOB)));
    }

}
