package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindBookingCommand;
import seedu.address.model.booking.BookingMatchesPersonIdPredicate;
import seedu.address.model.booking.BookingMatchesRoomIdPredicate;


public class FindBookingCommandParserTest {

    private FindBookingCommandParser parser = new FindBookingCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindBookingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {

        // no leading and trailing whitespaces
        BookingMatchesRoomIdPredicate predicate1 = new BookingMatchesRoomIdPredicate(1037);
        FindBookingCommand expectedFindBookingCommand =
                new FindBookingCommand(Collections.singletonList(predicate1));
        assertParseSuccess(parser, " rid/1037", expectedFindBookingCommand);

        // multiple whitespaces between filter words
        BookingMatchesPersonIdPredicate predicate2 = new BookingMatchesPersonIdPredicate(1);
        FindBookingCommand expectedFindBookingCommand2 =
                new FindBookingCommand(Arrays.asList(predicate1, predicate2));
        assertParseSuccess(parser, " \n rid/1037 \n \t pid/1  \t", expectedFindBookingCommand2);
    }

}
