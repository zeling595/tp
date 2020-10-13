package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalBookings.getTypicalBookingBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListRoomCommand.
 */
public class ListRoomCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalRoomBook(), getTypicalBookingBook());

    @Test
    public void execute_success() throws CommandException {
        LocalDate startDate = LocalDate.of(2020,10, 18);
        LocalDate endDate = LocalDate.of(2020,10, 23);
        List<Integer> output = new ArrayList<>();
        output.add(1235);
        output.add(1236);
        output.add(1239);
        output.add(1240);

        ListRoomCommand command = new ListRoomCommand(startDate, endDate);
        ObservableList<Integer> expectedResult = FXCollections.observableArrayList(output);
        String actualOutput = command.execute(model).getFeedbackToUser();
        String expectedOutput = ListRoomCommand.MESSAGE_SUCCESS + "\n" + Arrays.toString(expectedResult.toArray());

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void equals() {
        final LocalDate standardStart = LocalDate.of(2020, 10, 12);
        final LocalDate standardEnd = LocalDate.of(2020, 10, 20);
        final ListRoomCommand standardCommand = new ListRoomCommand(standardStart, standardEnd);

        // Different startDate -> return false
        LocalDate start1 = LocalDate.of(2020,10, 15);
        ListRoomCommand c1 = new ListRoomCommand(start1, standardEnd);
        assertFalse(standardCommand.equals(c1));

        // Different endDate -> return false
        LocalDate end1 = LocalDate.of(2020, 10, 23);
        ListRoomCommand c2 = new ListRoomCommand(standardStart, end1);
        assertFalse(standardCommand.equals(c2));

        // Same start and End Date -> return true
        LocalDate start2 = LocalDate.of(2020, 10, 12);
        LocalDate end2 = LocalDate.of(2020, 10, 20);
        ListRoomCommand c3 = new ListRoomCommand(start2, end2);
        assertTrue(standardCommand.equals(c3));

        // Same object -> return true
        assertTrue(standardCommand.equals(standardCommand));
    }
}
