package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_DAN;
import static seedu.address.testutil.TypicalBookings.*;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.booking.Booking;

/**
 * Contains integration tests (interaction with the Model) and unit tests for CheckOutCommand.
 */
class CheckOutCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalRoomBook(), getTypicalBookingBook());

    @Test
    public void execute_success() throws CommandException {
        model.addBooking(ACTIVE_BOOKING_DAN);
        Booking booking = model.getBooking(VALID_ROOM_ID_DAN);
        CheckOutCommand command = new CheckOutCommand(VALID_ROOM_ID_DAN);
        assertEquals(String.format(CheckOutCommand.MESSAGE_SUCCESS, booking),
                command.execute(model).getFeedbackToUser());
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
