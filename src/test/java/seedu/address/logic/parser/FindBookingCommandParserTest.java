package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindBookingCommand;
import seedu.address.model.booking.BookingMatchesEndDatePredicate;
import seedu.address.model.booking.BookingMatchesIsActivePredicate;
import seedu.address.model.booking.BookingMatchesPersonIdPredicate;
import seedu.address.model.booking.BookingMatchesRoomIdPredicate;
import seedu.address.model.booking.BookingMatchesStartDatePredicate;


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
        BookingMatchesRoomIdPredicate predicate1 = new BookingMatchesRoomIdPredicate(2103);
        FindBookingCommand expectedFindBookingCommand =
                new FindBookingCommand(Collections.singletonList(predicate1));
        assertParseSuccess(parser, " rid/2103", expectedFindBookingCommand);

        // multiple whitespaces between filter words
        BookingMatchesPersonIdPredicate predicate2 = new BookingMatchesPersonIdPredicate(1);
        FindBookingCommand expectedFindBookingCommand2 =
                new FindBookingCommand(Arrays.asList(predicate1, predicate2));
        assertParseSuccess(parser, " \n rid/2103 \n \t pid/1  \t", expectedFindBookingCommand2);

        // different order of parameters should give the same result
        assertParseSuccess(parser, " \n pid/1 \t \n rid/2103 \t", expectedFindBookingCommand2);

        // even more parameters
        BookingMatchesStartDatePredicate predicate3 = new BookingMatchesStartDatePredicate(LocalDate.of(2020, 10, 20));
        BookingMatchesEndDatePredicate predicate4 = new BookingMatchesEndDatePredicate(LocalDate.of(2020, 10, 25));
        BookingMatchesIsActivePredicate predicate5 = new BookingMatchesIsActivePredicate(false);
        FindBookingCommand expectedFindBookingCommand3 =
                new FindBookingCommand(Arrays.asList(predicate1, predicate2, predicate3, predicate4, predicate5));
        assertParseSuccess(parser, " \n pid/1 \t \n rid/2103 \t \n sd/2020-10-20 \t \n ed/2020-10-25 "
                        + "\t \n ac/false\t",
                expectedFindBookingCommand3);


    }

}
