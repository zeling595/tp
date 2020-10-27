package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_BOOKINGS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalBookings.*;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRoomService.getTypicalRoomServiceBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.BookingMatchesEndDatePredicate;
import seedu.address.model.booking.BookingMatchesIsActivePredicate;
import seedu.address.model.booking.BookingMatchesRoomIdPredicate;
import seedu.address.model.booking.BookingMatchesStartDatePredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindBookingCommand}.
 */
public class FindBookingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalRoomBook(),
            getTypicalBookingBook(), getTypicalRoomServiceBook());
    private Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getRoomBook(),
            model.getBookingBook(), model.getRoomServiceBook());
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
    public void execute_oneRoomIdPredicate_multipleBookingFound() {
        String expectedMessage = String.format(MESSAGE_BOOKINGS_LISTED_OVERVIEW, 2);
        BookingMatchesRoomIdPredicate predicate = prepareRoomIdPredicate("rid/2103");
        FindBookingCommand command = new FindBookingCommand(Arrays.asList(predicate));
        expectedModel.updateFilteredBookingList(new BookingMatchesRoomIdPredicate(2103));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BOOKING_1, BOOKING_6), model.getFilteredBookingList());
    }

    @Test
    public void execute_oneRoomIdPredicate_noBookingFound() {
        String expectedMessage = String.format(MESSAGE_BOOKINGS_LISTED_OVERVIEW, 0);
        BookingMatchesRoomIdPredicate predicate = prepareRoomIdPredicate("rid/1000");
        FindBookingCommand command = new FindBookingCommand(Arrays.asList(predicate));
        expectedModel.updateFilteredBookingList(new BookingMatchesRoomIdPredicate(1000));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookingList());
    }

    @Test
    public void execute_oneIsActivePredicate_multipleBookingFound() {
        String expectedMessage = String.format(MESSAGE_BOOKINGS_LISTED_OVERVIEW, 6);
        BookingMatchesIsActivePredicate predicate = prepareIsActivePredicate("ac/false");
        FindBookingCommand command = new FindBookingCommand(Arrays.asList(predicate));
        expectedModel.updateFilteredBookingList(new BookingMatchesIsActivePredicate(false));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BOOKING_1, BOOKING_2, BOOKING_3, BOOKING_4, BOOKING_5, BOOKING_6),
                model.getFilteredBookingList());
    }

    @Test
    public void execute_oneIsActivePredicate_noBookingFound() {
        String expectedMessage = String.format(MESSAGE_BOOKINGS_LISTED_OVERVIEW, 0);
        BookingMatchesIsActivePredicate predicate = prepareIsActivePredicate("ac/true");
        FindBookingCommand command = new FindBookingCommand(Arrays.asList(predicate));
        expectedModel.updateFilteredBookingList(new BookingMatchesIsActivePredicate(true));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookingList());
    }

    @Test
    public void execute_multiplePredicates_oneBookingFound() {
        String expectedMessage = String.format(MESSAGE_BOOKINGS_LISTED_OVERVIEW, 1);
        BookingMatchesRoomIdPredicate roomIdPredicate = prepareRoomIdPredicate("rid/2103");
        BookingMatchesStartDatePredicate startTimePredicate = prepareStartTimePredicate("sd/2020-10-20");
        FindBookingCommand command = new FindBookingCommand(Arrays.asList(roomIdPredicate, startTimePredicate));

        List<Predicate<Booking>> predicates = new ArrayList<>();
        predicates.add(roomIdPredicate);
        predicates.add(startTimePredicate);
        Predicate<Booking> predicate = predicates.stream().reduce(x -> true, Predicate::and);
        expectedModel.updateFilteredBookingList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BOOKING_1), model.getFilteredBookingList());
    }

    @Test
    public void execute_multiplePredicates_noBookingFound() {
        String expectedMessage = String.format(MESSAGE_BOOKINGS_LISTED_OVERVIEW, 0);
        BookingMatchesRoomIdPredicate roomIdPredicate = prepareRoomIdPredicate("rid/2103");
        BookingMatchesEndDatePredicate endTimePredicate = prepareEndTimePredicate("ed/2020-10-20");
        FindBookingCommand command = new FindBookingCommand(Arrays.asList(roomIdPredicate, endTimePredicate));

        List<Predicate<Booking>> predicates = new ArrayList<>();
        predicates.add(roomIdPredicate);
        predicates.add(endTimePredicate);
        Predicate<Booking> predicate = predicates.stream().reduce(x -> true, Predicate::and);
        expectedModel.updateFilteredBookingList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookingList());
    }

    /**
     * Parses {@code userInput} into a {@code BookingMatchesRoomIdPredicate}.
     */
    private BookingMatchesRoomIdPredicate prepareRoomIdPredicate(String userInput) {
        return new BookingMatchesRoomIdPredicate(Integer.valueOf(userInput.substring(4)));
    }

    /**
     * Parses {@code userInput} into a {@code BookingMatchesStartDatePredicate}.
     */
    private BookingMatchesStartDatePredicate prepareStartTimePredicate(String userInput) {
        return new BookingMatchesStartDatePredicate(LocalDate.parse(userInput.substring(3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    /**
     * Parses {@code userInput} into a {@code BookingMatchesEndDatePredicate}.
     */
    private BookingMatchesEndDatePredicate prepareEndTimePredicate(String userInput) {
        return new BookingMatchesEndDatePredicate(LocalDate.parse(userInput.substring(3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    /**
     * Parses {@code userInput} into a {@code prepareIsActivePredicate}.
     */
    private BookingMatchesIsActivePredicate prepareIsActivePredicate(String userInput) {
        return new BookingMatchesIsActivePredicate(Boolean.parseBoolean(userInput.substring(3)));
    }


}
