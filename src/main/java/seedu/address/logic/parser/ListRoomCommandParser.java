package seedu.address.logic.parser;

import seedu.address.logic.commands.ListRoomCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_TYPE;

public class ListRoomCommandParser implements Parser<ListRoomCommand> {

    public ListRoomCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ROOM_TYPE);

        int roomType = 0;

        if (argMultimap.getValue(PREFIX_ROOM_TYPE).isPresent()) {
            roomType = ParserUtil.parseRoomType(argMultimap.getValue(PREFIX_ROOM_TYPE).get());
        }

        if (roomType < 0 || roomType > 3) {
            throw new ParseException("Invalid Room Type. Only 1, 2 and 3 accepted.");
        }

        return new ListRoomCommand(roomType);
    }
}
