package seedu.address.logic.parser;

import seedu.address.logic.commands.CheckInCommand;
import seedu.address.logic.commands.ListRoomCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalDate;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;


public class ListRoomCommandParser implements Parser<ListRoomCommand>{
    public ListRoomCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_START_DATE, PREFIX_END_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_START_DATE, PREFIX_END_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListRoomCommand.MESSAGE_USAGE));
        }

        LocalDate startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_DATE).get());
        LocalDate endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_END_DATE).get());

        if (!startDate.isBefore(endDate)) {
            throw new ParseException("Start Date must be before End Date!");
        }

        return new ListRoomCommand(startDate, endDate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
