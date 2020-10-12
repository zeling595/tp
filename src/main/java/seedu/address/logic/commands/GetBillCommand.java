package seedu.address.logic.commands;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_ID;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Calculates the total bill for a particular occupied room.
 */
public class GetBillCommand extends Command {

    public static final String COMMAND_WORD = "getBill";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets total bill for a particular occupied room.\n"
            + "Parameters: ROOM ID"
            + PREFIX_ROOM_ID + "[ROOM ID]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROOM_ID + "2302";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "getBill command not implemented yet";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
