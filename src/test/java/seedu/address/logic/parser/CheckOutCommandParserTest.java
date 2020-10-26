package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.BOOKING_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_ID_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CheckOutCommand;

public class CheckOutCommandParserTest {
    private CheckOutCommandParser parser = new CheckOutCommandParser();

    @Test
    public void parse_valuesSpecified_success() {
        String userInput = BOOKING_ID_DESC_AMY;

        CheckOutCommand expectedCommand = new CheckOutCommand(VALID_BOOKING_ID_AMY);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingRoomIdField_failure() {

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckOutCommand.MESSAGE_USAGE);
        String userInput = "";

        assertParseFailure(parser, userInput, expectedMessage);
    }
}
