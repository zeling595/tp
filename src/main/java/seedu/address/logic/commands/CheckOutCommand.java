package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_ID;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Checks out guest with booking id
 */
public class CheckOutCommand extends Command {

    public static final String COMMAND_WORD = "checkOut";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Checks out person from hotel. "
            + "Parameters: " + PREFIX_BOOKING_ID + "[BOOKING_ID] (must be a valid booking id)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_BOOKING_ID + "2100";

    public static final String MESSAGE_SUCCESS = "Successfully checked out: %s";
    public static final String MESSAGE_BOOKING_MISSING = "No valid booking can be found.";
    public static final String MESSAGE_ALREADY_CHECKED_OUT = "You have already checked out from this booking.";

    private final int bookingId;

    /**
     * Creates a CheckOutCommand
     * @param bookingId booking to check out person from
     */
    public CheckOutCommand(int bookingId) {
        requireAllNonNull(bookingId);
        this.bookingId = bookingId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        if (!model.hasBookingWithId(bookingId)) {
            throw new CommandException(MESSAGE_BOOKING_MISSING);
        }

        if (!model.getBookingWithId(bookingId).isActive()) {
            throw new CommandException(MESSAGE_ALREADY_CHECKED_OUT);
        }

        model.setBookingInactive(bookingId);
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getBookingWithId(bookingId)));
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
        return this.bookingId == o.bookingId;
    }
}
