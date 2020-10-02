package seedu.address.logic.commands;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSONAL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;

/**
 * Encapsulates the Check In feature.
 */
public class CheckInCommand extends Command {

    public static final String COMMAND_WORD = "checkIn";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Checks in a person into the hotel.\n"
            + "Parameters: " + PREFIX_PERSONAL_ID + "/[PERSONAL_ID] (must be a positive integer) "
            + PREFIX_ROOM_ID + "[ROOM_ID] (must be a valid room number) "
            + PREFIX_START_DATE + "[START_DATE] (in the format YYYY-MM-DD) "
            + PREFIX_END_DATE + "[END_DATE] (in the format YYYY-MM-DD)\n"
            + "Example: " + COMMAND_WORD + PREFIX_PERSONAL_ID + "/69 "
            + PREFIX_ROOM_ID + "4102 "
            + PREFIX_START_DATE + "2020-09-14 "
            + PREFIX_END_DATE + "2020-09-17";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "checkIn command not implemented yet";

    public static final String MESSAGE_ARGUMENTS = "Personal id: %1$d, Room Id: %2$d, Start date: %3$s, End date: %4$s";

    private final int personalId;
    private final int roomId;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public CheckInCommand(int personalId, int roomId, LocalDate startDate, LocalDate endDate) {
        requireAllNonNull(personalId, roomId, startDate, endDate);

        this.personalId = personalId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, personalId, roomId, startDate, endDate));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CheckInCommand)) {
            return false;
        }

        // state check
        CheckInCommand e = (CheckInCommand) other;
        return personalId == e.personalId
                && roomId == e.roomId
                && startDate.equals(e.startDate)
                && endDate.equals(e.endDate);
    }
}
