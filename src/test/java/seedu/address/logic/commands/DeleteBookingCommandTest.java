package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBookingWithId;
import static seedu.address.testutil.TypicalBookings.getTypicalBookingBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRoomService.getTypicalRoomServiceBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
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
    public void execute_validBookingId_success() {
        Booking bookingToDelete = model.getBookingWithId(1);
        DeleteBookingCommand deleteBookingCommand = new DeleteBookingCommand(1);

        String expectedMessage = String.format(DeleteBookingCommand.MESSAGE_DELETE_BOOKING_SUCCESS, bookingToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getRoomBook(),
                model.getBookingBook(), model.getRoomServiceBook());
        expectedModel.deleteBooking(bookingToDelete);

        assertCommandSuccess(deleteBookingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidBookingId_throwsCommandException() {
        Integer outOfBoundBookingId = model.getBookingBook().getBookingList().size() + 1;
        DeleteBookingCommand deleteBookingCommand = new DeleteBookingCommand(outOfBoundBookingId);

        assertCommandFailure(deleteBookingCommand, model, Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_ID);
    }

    @Test
    public void execute_validBookingIDdFilteredList_success() {
        showBookingWithId(model, 1);

        Booking bookingToDelete = model.getBookingWithId(1);
        DeleteBookingCommand deleteBookingCommand = new DeleteBookingCommand(1);

        String expectedMessage = String.format(DeleteBookingCommand.MESSAGE_DELETE_BOOKING_SUCCESS, bookingToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getRoomBook(),
                model.getBookingBook(), model.getRoomServiceBook());
        expectedModel.deleteBooking(bookingToDelete);
        showNoBooking(expectedModel);

        assertCommandSuccess(deleteBookingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showBookingWithId(model, 1);

        Integer outOfBoundId = model.getBookingBook().getBookingList().size() + 1;

        DeleteBookingCommand deleteBookingCommand = new DeleteBookingCommand(outOfBoundId);

        assertCommandFailure(deleteBookingCommand, model, Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_ID);
    }

    @Test
    public void equals() {
        DeleteBookingCommand deleteFirstCommand = new DeleteBookingCommand(0);
        DeleteBookingCommand deleteSecondCommand = new DeleteBookingCommand(1);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteBookingCommand deleteFirstCommandCopy = new DeleteBookingCommand(0);
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
