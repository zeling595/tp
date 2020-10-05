package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.END_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PERSONAL_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ROOM_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.START_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSONAL_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSONAL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CheckInCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class CheckInCommandParserTest {
    private CheckInCommandParser parser = new CheckInCommandParser();
    private final String nonEmptyPersonalId = "420";
    private final String nonEmptyRoomId = "7200";
    private final String startDate = "2020-03-15";
    private final String endDate = "2020-03-19";

    @Test
    public void parse_valuesSpecified_success() throws ParseException {
        // have remark
        String userInput = PERSONAL_ID_DESC_AMY
                + ROOM_ID_DESC_AMY
                + START_DATE_DESC_AMY
                + END_DATE_DESC_AMY;

        CheckInCommand expectedCommand = new CheckInCommand(VALID_PERSONAL_ID_AMY,
                VALID_ROOM_ID_AMY,
                VALID_START_DATE_AMY,
                VALID_END_DATE_AMY);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckInCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, CheckInCommand.COMMAND_WORD, expectedMessage);

        // no personalId
        assertParseFailure(parser, CheckInCommand.COMMAND_WORD + " "
                        + PREFIX_ROOM_ID + nonEmptyRoomId + " "
                        + PREFIX_START_DATE + startDate + " "
                        + PREFIX_END_DATE + endDate,
                expectedMessage);

        // no roomId
        assertParseFailure(parser, CheckInCommand.COMMAND_WORD + " "
                        + PREFIX_PERSONAL_ID + nonEmptyPersonalId + " "
                        + PREFIX_START_DATE + startDate + " "
                        + PREFIX_END_DATE + endDate,
                expectedMessage);

        // no startDate
        assertParseFailure(parser, CheckInCommand.COMMAND_WORD + " "
                        + PREFIX_PERSONAL_ID + nonEmptyPersonalId + " "
                        + PREFIX_ROOM_ID + nonEmptyRoomId + " "
                        + PREFIX_END_DATE + endDate,
                expectedMessage);

        // no endDate
        assertParseFailure(parser, CheckInCommand.COMMAND_WORD + " "
                        + PREFIX_PERSONAL_ID + nonEmptyPersonalId + " "
                        + PREFIX_ROOM_ID + nonEmptyRoomId + " "
                        + PREFIX_START_DATE + startDate,
                expectedMessage);
    }
}
