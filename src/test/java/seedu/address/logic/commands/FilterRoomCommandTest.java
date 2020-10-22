package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalBookings.getTypicalBookingBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRoomService.getTypicalRoomServiceBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) and unit tests for FilterRoomCommand.
 */
public class FilterRoomCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalRoomBook(),
            getTypicalBookingBook(), getTypicalRoomServiceBook());

    @Test
    public void execute_success() throws CommandException {
        LocalDate startDate = LocalDate.of(2020, 10, 15);
        LocalDate endDate = LocalDate.of(2020, 10, 22);

        // Single Rooms
        int roomType1 = 1;
        FilterRoomCommand command1 = new FilterRoomCommand(startDate, endDate, roomType1);
        ArrayList<Integer> expectedRoomId1 = new ArrayList<>();
        expectedRoomId1.add(2104);
        expectedRoomId1.add(2105);
        expectedRoomId1.add(2106);
        expectedRoomId1.add(2107);
        expectedRoomId1.add(2108);
        String expectedOutput1 = model.displaySingleRooms(FXCollections.observableArrayList(expectedRoomId1));

        assertEquals(String.format(FilterRoomCommand.MESSAGE_SUCCESS, expectedOutput1),
                command1.execute(model).getFeedbackToUser());

        // Double Rooms
        int roomType2 = 2;
        FilterRoomCommand command2 = new FilterRoomCommand(startDate, endDate, roomType2);
        ArrayList<Integer> expectedRoomId2 = new ArrayList<>();
        expectedRoomId2.add(2113);
        expectedRoomId2.add(2114);
        expectedRoomId2.add(2115);
        expectedRoomId2.add(2116);
        expectedRoomId2.add(2117);
        String expectedOutput2 = model.displayDoubleRooms(FXCollections.observableArrayList(expectedRoomId2));
        assertEquals(String.format(FilterRoomCommand.MESSAGE_SUCCESS, expectedOutput2),
                command2.execute(model).getFeedbackToUser());
    }

    @Test
    public void equals() {
        LocalDate startDate = LocalDate.of(2020, 10, 15);
        LocalDate endDate = LocalDate.of(2020, 10, 22);
        int roomType = 1;

        LocalDate wrongStart = LocalDate.of(2020, 10, 20);
        LocalDate wrongEnd = LocalDate.of(2020, 10, 15);
        int wrongType = 2;
        FilterRoomCommand command = new FilterRoomCommand(startDate, endDate, roomType);

        // same values -> returns true
        FilterRoomCommand commandWithSameValues = new FilterRoomCommand(startDate, endDate, roomType);
        assertTrue(command.equals(commandWithSameValues));

        // same object -> return true
        assertTrue(command.equals(command));

        // null -> return false
        assertFalse(command.equals(null));

        // different types -> return false
        assertFalse(command.equals(new ClearCommand()));

        // different start Date -> return false
        assertFalse(command.equals(new FilterRoomCommand(wrongStart, endDate, roomType)));

        // different end Date -> return false
        assertFalse(command.equals(new FilterRoomCommand(startDate, wrongEnd, roomType)));

        // different roomType -> return false
        assertFalse(command.equals(new FilterRoomCommand(startDate, endDate, wrongType)));
    }

}
