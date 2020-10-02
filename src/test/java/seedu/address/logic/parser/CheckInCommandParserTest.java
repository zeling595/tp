package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSONAL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        String userInput = PREFIX_PERSONAL_ID + nonEmptyPersonalId + " "
                + PREFIX_ROOM_ID + nonEmptyRoomId + " "
                + PREFIX_START_DATE + startDate + " "
                + PREFIX_END_DATE + endDate;
        CheckInCommand expectedCommand = new CheckInCommand(Integer.parseInt(nonEmptyPersonalId),
                Integer.parseInt(nonEmptyRoomId),
                LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        assertParseSuccess(parser, userInput, expectedCommand);
        parser.parse(userInput);

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