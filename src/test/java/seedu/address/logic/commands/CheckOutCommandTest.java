package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_BOOKING_MISSING;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalBookings.BOOKING_AMY;
import static seedu.address.testutil.TypicalBookings.getTypicalBookingBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRoomService.getTypicalRoomServiceBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.booking.Booking;
import seedu.address.testutil.TypicalBookings;

/**
 * Contains integration tests (interaction with the Model) and unit tests for CheckOutCommand.
 */
class CheckOutCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalRoomBook(), getTypicalBookingBook(), getTypicalRoomServiceBook());

    @Test
    public void execute_success() throws CommandException {
        model.addBooking(TypicalBookings.ACTIVE_BOOKING_DAN);
        CheckOutCommand command = new CheckOutCommand(CommandTestUtil.VALID_BOOKING_ID_DAN);
        String result = command.execute(model).getFeedbackToUser();
        Booking booking = model.getBookingWithId(CommandTestUtil.VALID_BOOKING_ID_DAN);
        assertEquals(String.format(CheckOutCommand.MESSAGE_SUCCESS, booking), result);
    }

    @Test
    public void execute_bookingMissing_failure() {
        CheckOutCommand command = new CheckOutCommand(CommandTestUtil.VALID_BOOKING_ID_DAN);
        assertCommandFailure(command, model, MESSAGE_BOOKING_MISSING);
    }

    @Test
    public void execute_bookingInactive_failure() {
        model.addBooking(BOOKING_AMY);
        CheckOutCommand command = new CheckOutCommand(CommandTestUtil.VALID_BOOKING_ID_AMY);
        assertCommandFailure(command, model, CheckOutCommand.MESSAGE_ALREADY_CHECKED_OUT);
    }

    @Test
    public void equals() {
        int roomId = 2103;
        final CheckOutCommand standardCommand = new CheckOutCommand(roomId);

        // same values -> returns true
        CheckOutCommand commandWithSameValues = new CheckOutCommand(roomId);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different Room Id -> return false
        assertFalse(standardCommand.equals(new CheckOutCommand(roomId + 1)));
    }
}
