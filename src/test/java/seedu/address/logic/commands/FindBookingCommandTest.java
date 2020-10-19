package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_BOOKINGS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalBookings.BOOKING_1;
import static seedu.address.testutil.TypicalBookings.BOOKING_6;
import static seedu.address.testutil.TypicalBookings.getTypicalBookingBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.booking.BookingMatchesRoomIdPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindBookingCommand}.
 */
public class FindBookingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalRoomBook(),
            getTypicalBookingBook());
    private Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getRoomBook(),
            model.getBookingBook());
    @Test
    public void equals() {
        BookingMatchesRoomIdPredicate firstPredicate =
                new BookingMatchesRoomIdPredicate(1235);
        BookingMatchesRoomIdPredicate secondPredicate =
                new BookingMatchesRoomIdPredicate(1236);

        FindBookingCommand findFirstBookingCommand = new FindBookingCommand(Arrays.asList(firstPredicate));
        FindBookingCommand findSecondBookingCommand = new FindBookingCommand(Arrays.asList(secondPredicate));

        // same object -> returns true
        assertTrue(findFirstBookingCommand.equals(findFirstBookingCommand));

        // same values -> returns true
        FindBookingCommand findFirstCommandCopy = new FindBookingCommand(Arrays.asList(firstPredicate));
        assertTrue(findFirstBookingCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstBookingCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstBookingCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstBookingCommand.equals(findSecondBookingCommand));
    }


    @Test
    public void execute_onePredicate_multipleBookingFound() {
        String expectedMessage = String.format(MESSAGE_BOOKINGS_LISTED_OVERVIEW, 2);
        BookingMatchesRoomIdPredicate predicate = preparePredicate("rid/1235");
        FindBookingCommand command = new FindBookingCommand(Arrays.asList(predicate));
        expectedModel.updateFilteredBookingList(new BookingMatchesRoomIdPredicate(1235));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BOOKING_1, BOOKING_6), model.getFilteredBookingList());
    }

    @Test
    public void execute_onePredicate_noBookingFound() {
        String expectedMessage = String.format(MESSAGE_BOOKINGS_LISTED_OVERVIEW, 0);
        BookingMatchesRoomIdPredicate predicate = preparePredicate("rid/1000");
        FindBookingCommand command = new FindBookingCommand(Arrays.asList(predicate));
        expectedModel.updateFilteredBookingList(new BookingMatchesRoomIdPredicate(1000));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookingList());
    }
    

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private BookingMatchesRoomIdPredicate preparePredicate(String userInput) {
        return new BookingMatchesRoomIdPredicate(Integer.valueOf(userInput.substring(4)));
    }
}
