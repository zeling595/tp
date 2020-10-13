package seedu.address.logic.commands;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_ID;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.exception.BookingNotFoundException;
import seedu.address.model.room.Room;

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
    public static final String MESSAGE_ROOM_MISSING = "No valid room can be found";
    public static final String MESSAGE_BOOKING_MISSING = "No valid booking can be found.";
    public static final String MESSAGE_SUCCESS_GET_BILL = "Total bill for room id: %1$d is %2$d";

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
        Booking booking;
        Room room;

        if (!model.hasRoom(roomId)) {
            throw new CommandException(MESSAGE_ROOM_MISSING);
        }

        try {
            booking = model.getBooking(roomId);
        } catch (BookingNotFoundException e) {
            throw new CommandException(MESSAGE_BOOKING_MISSING);
        }

        room = model.getRoom(roomId);
        int pricePerNight = room.getPrice();
        int numNights = booking.getDuration();
        int totalPrice = pricePerNight * numNights;
        return new CommandResult(String.format(MESSAGE_SUCCESS_GET_BILL, roomId, totalPrice));
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
