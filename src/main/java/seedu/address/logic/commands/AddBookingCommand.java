package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_CONFLICTING_BOOKING;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_BOOKING;
import static seedu.address.commons.core.Messages.MESSAGE_EXCEED_DURATION;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONAL_ID_MISSING;
import static seedu.address.commons.core.Messages.MESSAGE_ROOM_ID_MISSING;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSONAL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.time.LocalDate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.exception.ConflictingBookingException;
import seedu.address.model.booking.exception.DuplicateBookingException;
import seedu.address.model.booking.exception.ExceedDurationStayException;

/**
 * Encapsulates the AddBooking feature.
 */
public class AddBookingCommand extends Command {

    public static final String COMMAND_WORD = "addBooking";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a booking to the hotel. "
            + "Dates should be in the format yyyy-MM-dd. "
            + "The start date should be before the end date and start and end dates should be "
            + "within 30 nights of each other.\n"
            + "Parameters: "
            + PREFIX_PERSONAL_ID + "PERSONAL_ID (must be an existing personal Id) "
            + PREFIX_ROOM_ID + "ROOM_ID (must be an existing room Id) "
            + PREFIX_START_DATE + "START_DATE "
            + PREFIX_END_DATE + "END_DATE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PERSONAL_ID + "69 "
            + PREFIX_ROOM_ID + "2126 "
            + PREFIX_START_DATE + "2020-09-14 "
            + PREFIX_END_DATE + "2020-09-17";

    public static final String MESSAGE_ARGUMENTS = "Personal id: %1$d, Room Id: %2$d, Start date: %3$s, End date: %4$s";
    public static final String MESSAGE_SUCCESS = "Successfully added booking: %s";

    private final int personalId;
    private final int roomId;
    private final LocalDate startDate;
    private final LocalDate endDate;

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    /**
     * Creates a AddBookingCommand.
     *
     * @param personalId the personalId of the person who is staying at the hotel
     * @param roomId the roomId of the room that the person is going to stay in
     * @param startDate the start date of the booking
     * @param endDate the end date of the booking
     */
    public AddBookingCommand(int personalId, int roomId, LocalDate startDate, LocalDate endDate) {
        requireAllNonNull(personalId, roomId, startDate, endDate);

        this.personalId = personalId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Booking booking;
        assert roomId > 0;
        assert startDate.isBefore(endDate);
        logger.info(String.format("adding a booking involving person with id %s and room %s", personalId, roomId));

        if (!model.hasPersonWithId(personalId)) {
            throw new CommandException(MESSAGE_PERSONAL_ID_MISSING);
        }

        assert model.hasPersonWithId(personalId);

        if (!model.hasRoom(roomId)) {
            throw new CommandException(MESSAGE_ROOM_ID_MISSING);
        }

        assert model.hasRoom(roomId);

        booking = new Booking(roomId, personalId, startDate, endDate, true);
        int bookingId = booking.getId();

        try {
            model.addBooking(booking);
            return new CommandResult(String.format(MESSAGE_SUCCESS, booking));
        } catch (ConflictingBookingException conflictE) {
            Booking.setNextAvailableId(bookingId);
            throw new CommandException(MESSAGE_CONFLICTING_BOOKING);
        } catch (DuplicateBookingException duplicateE) {
            Booking.setNextAvailableId(bookingId);
            throw new CommandException(MESSAGE_DUPLICATE_BOOKING);
        } catch (ExceedDurationStayException exceedE) {
            Booking.setNextAvailableId(bookingId);
            throw new CommandException(MESSAGE_EXCEED_DURATION);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddBookingCommand)) {
            return false;
        }

        // state check
        AddBookingCommand e = (AddBookingCommand) other;
        return personalId == e.personalId
                && roomId == e.roomId
                && startDate.isEqual(e.startDate)
                && endDate.isEqual(e.endDate);
    }
}
