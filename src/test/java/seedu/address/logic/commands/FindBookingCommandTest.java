package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalBookings.getTypicalBookingBook;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.booking.BookingMatchesRoomIdPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindBookingCommand}.
 */
public class FindBookingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalRoomBook(),
            getTypicalBookingBook());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), model.getRoomBook(),
            model.getBookingBook());

    @Test
    public void equals() {
        BookingMatchesRoomIdPredicate firstPredicate =
                new BookingMatchesRoomIdPredicate(2103);
        BookingMatchesRoomIdPredicate secondPredicate =
                new BookingMatchesRoomIdPredicate(2104);

        FindBookingCommand findFirstBookingCommand = new FindBookingCommand(Arrays.asList(firstPredicate));
        FindBookingCommand findSecondBookingCommand = new FindBookingCommand(Arrays.asList(firstPredicate));

        // same object -> returns true
        assertTrue(findFirstBookingCommand.equals(findFirstBookingCommand));

        // same values -> returns true
        FindBookingCommand findFirstCommandCopy = new FindBookingCommand(Collections.singletonList(firstPredicate));
        assertTrue(findFirstBookingCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstBookingCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstBookingCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstBookingCommand.equals(findSecondBookingCommand));
    }

//    @Test
//    public void execute_zeroKeywords_noPersonFound() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
//        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
//        FindBookingCommand command = new FindBookingCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
//    }
//
//    @Test
//    public void execute_multipleKeywords_multiplePersonsFound() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
//        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
//        FindBookingCommand command = new FindBookingCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
//    }
//
//    /**
//     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
//     */
//    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
//        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
//    }
}
