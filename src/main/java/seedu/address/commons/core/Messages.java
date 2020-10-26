package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    // person
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid.";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_PERSONAL_ID_MISSING = "No valid personalId can be found.";

    // room
    public static final String MESSAGE_ROOM_ID_MISSING = "No valid roomId can be be found.";

    // booking
    public static final String MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX = "The bookingId index is invalid.";
    public static final String MESSAGE_INVALID_BOOKING_DISPLAYED_ID = "The bookingId provided is invalid.";
    public static final String MESSAGE_BOOKINGS_LISTED_OVERVIEW = "%1$d booking listed!";
    public static final String MESSAGE_CONFLICTING_BOOKING = "The room has already been booked during this period.";
    public static final String MESSAGE_BOOKING_MISSING = "No valid booking can be found.";

}
