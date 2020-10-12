package seedu.address.logic.commands;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_ID;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Calculates the total bill for a particular occupied room.
 */
public class GetBillCommand extends Command {

    public static final String COMMAND_WORD = "getBill";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets total bill for a particular occupied room.\n"
            + "Parameters: ROOM ID "
            + PREFIX_ROOM_ID + "[ROOM ID]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROOM_ID + "2302";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "getBill command not implemented yet";

    public static final String MESSAGE_ARGUMENTS = "Room id: %1$d";

    private final int roomId;

    /**
     * Creates a GetBillCommand.
     * @param roomId the room id of which to get the bill for
     */
    public GetBillCommand(int roomId) {
        requireAllNonNull(roomId);
        this.roomId = roomId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, roomId));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GetBillCommand)) {
            return false;
        }

        // state check
        GetBillCommand e = (GetBillCommand) other;
        return roomId == e.roomId;
    }
}
