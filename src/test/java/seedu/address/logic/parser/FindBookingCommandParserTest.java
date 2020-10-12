package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FindBookingCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.BookingMatchesRoomIdPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindBookingCommandParserTest {

    private FindBookingCommandParser parser = new FindBookingCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindBookingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {

        BookingMatchesRoomIdPredicate p1 = new BookingMatchesRoomIdPredicate(1037);
        BookingMatchesRoomIdPredicate p2 = new BookingMatchesRoomIdPredicate(1037);
        List<Predicate<Booking>> predicates1 = new ArrayList<Predicate<Booking>>();
        predicates1.add(p1);
        List<Predicate<Booking>> predicates2 = new ArrayList<Predicate<Booking>>();
        predicates1.add(p1);
        boolean b = predicates1.equals(predicates2);
        System.out.println(b);

        // no leading and trailing whitespaces
        FindBookingCommand expectedFindCommand =
                new FindBookingCommand(Arrays.asList(new BookingMatchesRoomIdPredicate(1037)));
        assertParseSuccess(parser, "rid/1037", expectedFindCommand);



//        // multiple whitespaces between keywords
//        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

}
