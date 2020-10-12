package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import java.time.LocalDate;

public class ListRoomCommand extends Command {

    public static final String COMMAND_WORD = "listRoom";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists the rooms available within a certain date."
            + "Parameters: "
            + PREFIX_START_DATE + "[START_DATE] (in the format YYYY-MM-DD) "
            + PREFIX_END_DATE + "[END_DATE] (in the format YYYY-MM-DD)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_START_DATE + "2020-12-10 "
            + PREFIX_END_DATE + "2020-12-15";
    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "ListRoom Command not implemented yet";

    private final LocalDate startDate;
    private final LocalDate endDate;

    public ListRoomCommand(LocalDate startDate, LocalDate endDate) {
        requireAllNonNull(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ListRoomCommand)) {
            return false;
        }
        ListRoomCommand e = (ListRoomCommand) other;
        return this.startDate.equals(e.startDate) && this.endDate.equals(e.endDate);
    }
}
