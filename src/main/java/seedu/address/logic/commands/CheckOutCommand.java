package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_ID;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.exception.BookingNotFoundException;

/**
 * Checks out guest with room id
 */
public class CheckOutCommand extends Command {

    public static final String COMMAND_WORD = "checkOut";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Checks out person from hotel. "
            + "Parameters: " + PREFIX_ROOM_ID + "[ROOM_ID] (must be a valid room number)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ROOM_ID + "2100";

    public static final String MESSAGE_SUCCESS = "Successfully booked out: %s";
    public static final String MESSAGE_BOOKING_MISSING = "No valid booking can be found.";

    private final int roomId;

    /**
     * Creates a CheckOutCommand
     * @param roomId id of room to check out person from
     */
    public CheckOutCommand(int roomId) {
        requireAllNonNull(roomId);
        this.roomId = roomId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Booking booking;

        try {
            booking = model.getBooking(roomId);
        } catch (BookingNotFoundException e) {
            throw new CommandException(MESSAGE_BOOKING_MISSING);
        }

        model.setBookingInactive(roomId);
        return new CommandResult(String.format(MESSAGE_SUCCESS, booking));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CheckOutCommand)) {
            return false;
        }

        CheckOutCommand o = (CheckOutCommand) other;
        return this.roomId == o.roomId;
    }
}
