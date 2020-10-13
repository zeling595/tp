package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.PAST_END_DATE;
import static seedu.address.logic.commands.CommandTestUtil.PAST_START_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_GENE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSONAL_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSONAL_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSONAL_ID_GENE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_GENE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_GENE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBookings.getTypicalBookingBook;
import static seedu.address.testutil.TypicalPersons.GENE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.booking.Booking;

/**
 * Contains integration tests (interaction with the Model) and unit tests for CheckInCommand.
 */
public class CheckInCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalRoomBook(),
            getTypicalBookingBook());

    @Test
    public void constructor_nullStartDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CheckInCommand(VALID_PERSONAL_ID_GENE, VALID_ROOM_ID_GENE,
                null, VALID_END_DATE_GENE));
    }

    @Test
    public void constructor_nullEndDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CheckInCommand(VALID_PERSONAL_ID_GENE, VALID_ROOM_ID_GENE,
                VALID_START_DATE_GENE, null));
    }

    @Test
    public void execute_success() throws CommandException {
        model.addPerson(GENE);

        // stub
        // when i create a new booking on line 42, my booking ID++
        // so when i assertEquals, i fail because my checkInCommand will create a booking with the incremented ID
        final int testBookingId = 7;

        Booking booking = new Booking(VALID_ROOM_ID_GENE, VALID_PERSONAL_ID_GENE,
                VALID_START_DATE_GENE, VALID_END_DATE_GENE, true, testBookingId);

        CheckInCommand command = new CheckInCommand(VALID_PERSONAL_ID_GENE, VALID_ROOM_ID_GENE,
                VALID_START_DATE_GENE, VALID_END_DATE_GENE);

        assertEquals(String.format(CheckInCommand.MESSAGE_SUCCESS, booking),
                command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_pastStartDateAndValidEndDate_throwsCommandException() {
        assertThrows(CommandException.class, () -> new CheckInCommand(VALID_PERSONAL_ID_GENE,
                VALID_ROOM_ID_GENE, PAST_START_DATE, VALID_END_DATE_GENE).execute(model));
    }

    @Test
    public void execute_pastStartDateAndPastEndDate_throwsCommandException() {
        assertThrows(CommandException.class, () -> new CheckInCommand(VALID_PERSONAL_ID_GENE,
                VALID_ROOM_ID_GENE, PAST_START_DATE, PAST_END_DATE).execute(model));
    }

    @Test
    public void equals() {
        final CheckInCommand standardCommand = new CheckInCommand(VALID_PERSONAL_ID_AMY, VALID_ROOM_ID_AMY,
                VALID_START_DATE_AMY, VALID_END_DATE_AMY);

        // same values -> returns true
        CheckInCommand commandWithSameValues = new CheckInCommand(VALID_PERSONAL_ID_AMY, VALID_ROOM_ID_AMY,
                VALID_START_DATE_AMY, VALID_END_DATE_AMY);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different Personal Id -> return false
        assertFalse(standardCommand.equals(new CheckInCommand(VALID_PERSONAL_ID_BOB, VALID_ROOM_ID_AMY,
                VALID_START_DATE_AMY, VALID_END_DATE_AMY)));

        // different Room Id -> return false
        assertFalse(standardCommand.equals(new CheckInCommand(VALID_PERSONAL_ID_AMY, VALID_ROOM_ID_BOB,
                VALID_START_DATE_AMY, VALID_END_DATE_AMY)));

        // different start date -> return false
        assertFalse(standardCommand.equals(new CheckInCommand(VALID_PERSONAL_ID_AMY, VALID_ROOM_ID_AMY,
                VALID_START_DATE_BOB, VALID_END_DATE_AMY)));

        // different end date -> return false
        assertFalse(standardCommand.equals(new CheckInCommand(VALID_PERSONAL_ID_AMY, VALID_ROOM_ID_AMY,
                VALID_START_DATE_AMY, VALID_END_DATE_BOB)));
    }
}
