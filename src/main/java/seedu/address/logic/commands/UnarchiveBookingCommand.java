package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_ID;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.LogicManager;

/**
 * Unarchive (change an inactive booking back to active) the booking with a particular booking id
 */
public class UnarchiveBookingCommand {
    public static final String COMMAND_WORD = "unarchiveBooking";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unarchive a booking. \n"
            + "Parameters: "
            + PREFIX_BOOKING_ID + "BOOKING_ID (must be a valid booking id)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_BOOKING_ID + "3";

    public static final String MESSAGE_SUCCESS = "Successfully unarchived booking: %s";
    public static final String MESSAGE_NOT_YET_ARCHIVED = "This booking is not archived yet.";
}
