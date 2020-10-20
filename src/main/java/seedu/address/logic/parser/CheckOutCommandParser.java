package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_ID;

import seedu.address.logic.commands.CheckOutCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class CheckOutCommandParser implements Parser<CheckOutCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CheckOutCommand
     * and returns a CheckOutCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CheckOutCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args, PREFIX_BOOKING_ID);

        if (argMultiMap.getValue(PREFIX_BOOKING_ID).isEmpty() || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckOutCommand.MESSAGE_USAGE));
        }

        int bookingId = ParserUtil.parseBookingId(argMultiMap.getValue(PREFIX_BOOKING_ID).get());

        return new CheckOutCommand(bookingId);
    }
}
