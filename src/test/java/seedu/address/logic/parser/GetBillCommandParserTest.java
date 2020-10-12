package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ROOM_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GetBillCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class GetBillCommandParserTest {
    private GetBillCommandParser parser = new GetBillCommandParser();

    @Test
    public void parse_valuesSpecified_success() throws ParseException {
        String userInput = ROOM_ID_DESC_AMY;

        GetBillCommand expectedCommand = new GetBillCommand(VALID_ROOM_ID_AMY);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingRoomIdField_failure() {

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetBillCommand.MESSAGE_USAGE);
        String userInput = "";

        assertParseFailure(parser, userInput, expectedMessage);
    }

}
