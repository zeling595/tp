package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IS_ACTIVE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;

/**
 * Finds and list the active booking in booking book which roomId corresponding input.
 */
public class FindBookingCommand extends Command {

    public static final String COMMAND_WORD = "findBooking";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the active booking in booking book "
            + "associated with the personId, roomId, start and end date, isActive and show as a list with one item.\n"
            + "Parameters:"
            + PREFIX_ROOM_ID + "[ROOM_ID] (must be a valid room number) "
            + PREFIX_START_DATE + "[START_DATE] (in the format YYYY-MM-DD) "
            + PREFIX_END_DATE + "[END_DATE] (in the format YYYY-MM-DD)\n"
            + PREFIX_IS_ACTIVE + "[IS_ACTIVE] (in the format true or false)\n"

            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROOM_ID + "3017 "
            + PREFIX_START_DATE + "2020-09-14 "
            + PREFIX_END_DATE + "2020-09-17"
            + PREFIX_IS_ACTIVE + "false\n";


    private final List<Predicate<Booking>> predicates;

    public FindBookingCommand(List<Predicate<Booking>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Booking> predicate = predicates.stream().reduce(x -> true, Predicate::and);
        model.updateFilteredBookingList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_BOOKINGS_LISTED_OVERVIEW, model.getFilteredBookingList().size()),
                false, false, false, true);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindBookingCommand // instanceof handles nulls
                && predicates.equals(((FindBookingCommand) other).predicates));
    }
}
