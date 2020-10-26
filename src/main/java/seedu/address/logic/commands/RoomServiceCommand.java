package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.roomservice.RoomService;
import seedu.address.model.roomservice.RoomServiceType;


public class RoomServiceCommand extends Command {

    public static final String COMMAND_WORD = "orderRoomService";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Orders room service for booking.\n"
            + "Valid room types are: "
            + RoomServiceType.getAllNames()
            + "\n"
            + "Parameters: "
            + PREFIX_BOOKING_ID + "BOOKING_ID "
            + PREFIX_ROOM_SERVICE_TYPE + "TYPE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_BOOKING_ID + "3 "
            + PREFIX_ROOM_SERVICE_TYPE + "DINING";

    public static final String MESSAGE_SUCCESS = "Ordered room service:\n%s";
    public static final String MESSAGE_BOOKING_MISSING = "No booking with id %s can be found.";
    public static final String MESSAGE_BOOKING_INVALID = "Booking with id %s is inactive.";

    private final RoomService roomService;

    /**
     * Creates a RoomServiceCommand
     */
    public RoomServiceCommand(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        if (!model.hasBookingWithId(roomService.getBookingId())) {
            throw new CommandException(String.format(MESSAGE_BOOKING_MISSING, roomService.getBookingId()));
        }

        if (!model.getBookingWithId(roomService.getBookingId()).isActive()) {
            throw new CommandException(String.format(MESSAGE_BOOKING_INVALID, roomService.getBookingId()));
        }

        model.addRoomService(roomService);

        return new CommandResult(String.format(MESSAGE_SUCCESS, roomService));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomServiceCommand // instanceof handles nulls
                && roomService.equals(((RoomServiceCommand) other).roomService));
    }
}
