package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_SERVICE_TYPE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RoomServiceCommand;
import seedu.address.model.roomservice.RoomService;
import seedu.address.model.roomservice.RoomServiceType;

class RoomServiceCommandParserTest {

    private RoomServiceCommandParser parser = new RoomServiceCommandParser();

    @Test
    void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RoomServiceCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        String input = " " + PREFIX_BOOKING_ID + "1 " + PREFIX_ROOM_SERVICE_TYPE + RoomServiceType.WIFI.getName();
        assertParseSuccess(
                parser, input, new RoomServiceCommand(new RoomService(1, RoomServiceType.WIFI)));

        String input2 = " " + PREFIX_ROOM_SERVICE_TYPE + RoomServiceType.WIFI.getName() + " " + PREFIX_BOOKING_ID + "1";
        assertParseSuccess(
                parser, input2, new RoomServiceCommand(new RoomService(1, RoomServiceType.WIFI)));
    }
}
