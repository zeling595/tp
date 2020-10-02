package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSONAL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.CheckInCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code CheckInCommand} object
 */
public class CheckInCommandParser implements Parser<CheckInCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code CheckInCommand}
     * and returns a {@code CheckInCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CheckInCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PERSONAL_ID, PREFIX_ROOM_ID,
                PREFIX_START_DATE, PREFIX_END_DATE);

        try {
            int personalId = Integer.parseInt(argMultimap.getValue(PREFIX_PERSONAL_ID).orElse(""));
            int roomId = Integer.parseInt(argMultimap.getValue(PREFIX_ROOM_ID).orElse(""));
            LocalDate startDate = LocalDate.parse(argMultimap.getValue(PREFIX_START_DATE).orElse(""),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate endDate = LocalDate.parse(argMultimap.getValue(PREFIX_END_DATE).orElse(""),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            return new CheckInCommand(personalId, roomId, startDate, endDate);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckInCommand.MESSAGE_USAGE), e);
        } catch (DateTimeParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckInCommand.MESSAGE_USAGE), e);
        }



    }
}