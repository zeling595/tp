package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CheckInCommand.MESSAGE_ARGUMENTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSONAL_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSONAL_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for CheckInCommand.
 */
public class CheckInCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        assertCommandFailure(new CheckInCommand(VALID_PERSONAL_ID_AMY, VALID_ROOM_ID_AMY,
                VALID_START_DATE_AMY, VALID_END_DATE_AMY), model,
                String.format(MESSAGE_ARGUMENTS, VALID_PERSONAL_ID_AMY, VALID_ROOM_ID_AMY,
                        VALID_START_DATE_AMY, VALID_END_DATE_AMY));
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
