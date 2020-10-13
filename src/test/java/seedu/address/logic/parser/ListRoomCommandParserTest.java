package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.CheckInCommand;
import seedu.address.logic.commands.ListRoomCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class ListRoomCommandParserTest {
    private ListRoomCommandParser parser = new ListRoomCommandParser();
    private final String startDate = "2020-03-15";
    private final String endDate = "2020-03-19";

    @Test
    public void parse_valuesSpecified_success() throws ParseException {
        String userInput =  START_DATE_DESC_AMY + END_DATE_DESC_AMY;
        ListRoomCommand expectedCommand = new ListRoomCommand( VALID_START_DATE_AMY, VALID_END_DATE_AMY);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListRoomCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, ListRoomCommand.COMMAND_WORD, expectedMessage);

        // no startDate
        assertParseFailure(parser, ListRoomCommand.COMMAND_WORD + " " + PREFIX_END_DATE + endDate,
                expectedMessage);

        // no startDate
        assertParseFailure(parser, ListRoomCommand.COMMAND_WORD + " " + PREFIX_START_DATE + startDate + " ",
                expectedMessage);
    }
}
