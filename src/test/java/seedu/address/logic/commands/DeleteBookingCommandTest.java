package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBookingAtIndex;
import static seedu.address.testutil.TypicalBookings.getTypicalBookingBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BOOKING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BOOKING;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRoomService.getTypicalRoomServiceBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.booking.Booking;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteBookingCommand}.
 */
public class DeleteBookingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalRoomBook(),
            getTypicalBookingBook(), getTypicalRoomServiceBook());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Booking bookingToDelete = model.getFilteredBookingList().get(INDEX_FIRST_BOOKING.getZeroBased());
        DeleteBookingCommand deleteBookingCommand = new DeleteBookingCommand(INDEX_FIRST_BOOKING);

        String expectedMessage = String.format(DeleteBookingCommand.MESSAGE_DELETE_BOOKING_SUCCESS, bookingToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getRoomBook(),
                model.getBookingBook(), model.getRoomServiceBook());
        expectedModel.deleteBooking(bookingToDelete);

        assertCommandSuccess(deleteBookingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookingList().size() + 1);
        DeleteBookingCommand deleteBookingCommand = new DeleteBookingCommand(outOfBoundIndex);

        assertCommandFailure(deleteBookingCommand, model, Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showBookingAtIndex(model, INDEX_FIRST_BOOKING);

        Booking bookingToDelete = model.getFilteredBookingList().get(INDEX_FIRST_BOOKING.getZeroBased());
        DeleteBookingCommand deleteBookingCommand = new DeleteBookingCommand(INDEX_FIRST_BOOKING);

        String expectedMessage = String.format(DeleteBookingCommand.MESSAGE_DELETE_BOOKING_SUCCESS, bookingToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getRoomBook(),
                model.getBookingBook(), model.getRoomServiceBook());
        expectedModel.deleteBooking(bookingToDelete);
        showNoBooking(expectedModel);

        assertCommandSuccess(deleteBookingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showBookingAtIndex(model, INDEX_FIRST_BOOKING);

        Index outOfBoundIndex = INDEX_SECOND_BOOKING;
        // ensures that outOfBoundIndex is still in bounds of booking book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getBookingBook().getBookingList().size());

        DeleteBookingCommand deleteBookingCommand = new DeleteBookingCommand(outOfBoundIndex);

        assertCommandFailure(deleteBookingCommand, model, Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteBookingCommand deleteFirstCommand = new DeleteBookingCommand(INDEX_FIRST_BOOKING);
        DeleteBookingCommand deleteSecondCommand = new DeleteBookingCommand(INDEX_SECOND_BOOKING);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteBookingCommand deleteFirstCommandCopy = new DeleteBookingCommand(INDEX_FIRST_BOOKING);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoBooking(Model model) {
        model.updateFilteredBookingList(p -> false);

        assertTrue(model.getFilteredBookingList().isEmpty());
    }
}
