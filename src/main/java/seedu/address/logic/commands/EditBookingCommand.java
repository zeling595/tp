package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.model.Model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;

public class EditBookingCommand extends Command {
    public static final String COMMAND_WORD = "editBooking";

    // can edit room id, start date, end date
    // cannot edit person id
    // at least one field is edited
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the booking identified "
            + "by the index number used in the displayed booking list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_ROOM_ID + "ROOM ID] "
            + "[" + PREFIX_START_DATE + "START DATE] "
            + "[" + PREFIX_END_DATE + "END DATE] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ROOM_ID + "2103 "
            + PREFIX_START_DATE + "2020-12-25";

    public static final String MESSAGE_EDIT_BOOKING_SUCCESS = "Edited Booking: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_BOOKING = "This booking already exists in the booking book.";
    public static final String MESSAGE_CONFLICTING_BOOKING =
            "This booking conflicts with another booking in the booking book.";

    private final Index index;
    private final EditBookingCommand.EditBookingDescriptor editBookingDescriptor;

    /**
     * @param index of the person in the filtered booking list to edit
     * @param editBookingDescriptor details to edit the booking with
     */
    public EditBookingCommand(Index index, EditBookingCommand.EditBookingDescriptor editBookingDescriptor) {
        requireNonNull(index);
        requireNonNull(editBookingDescriptor);

        this.index = index;
        this.editBookingDescriptor = new EditBookingCommand.EditBookingDescriptor(editBookingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Booking> lastShownList = model.getFilteredBookingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
        }

        Booking bookingToEdit = lastShownList.get(index.getZeroBased());
        Booking editedBooking = createEditedBooking(bookingToEdit, editBookingDescriptor);

        // duplicate booking
        if (!bookingToEdit.equals(editedBooking) && model.hasBooking(editedBooking)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOKING);
        }

        // conflicting booking
        boolean hasConflict = false;
        hasConflict = model.getFilteredBookingList().stream().anyMatch(booking -> editedBooking.hasConflict(booking));
        if (!bookingToEdit.equals(editedBooking) && hasConflict) {
            throw new CommandException(MESSAGE_CONFLICTING_BOOKING);
        }

        model.setBooking(bookingToEdit, editedBooking);
        model.updateFilteredBookingList(PREDICATE_SHOW_ALL_BOOKINGS);
        return new CommandResult(String.format(MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditBookingCommand)) {
            return false;
        }

        // state check
        EditBookingCommand e = (EditBookingCommand) other;
        return index.equals(e.index)
                && editBookingDescriptor.equals(e.editBookingDescriptor);
    }

    /**
     * Creates and returns a {@code Booking} with the details of {@code bookingToEdit}
     * edited with {@code editBookingDescriptor}.
     */
    private static Booking createEditedBooking(Booking bookingToEdit,
                                               EditBookingCommand.EditBookingDescriptor editBookingDescriptor) {
        assert bookingToEdit != null;

        Integer roomId = editBookingDescriptor.getRoomId().orElse(bookingToEdit.getRoomId());
        int personId = bookingToEdit.getPersonId(); // person id cannot be modified
        LocalDate startDate = editBookingDescriptor.getStartDate().orElse(bookingToEdit.getStartDate());
        LocalDate endDate = editBookingDescriptor.getEndDate().orElse(bookingToEdit.getEndDate());
        boolean isActive = bookingToEdit.isActive(); // isActive cannot be modified
        int id = bookingToEdit.getId(); // id cannot be modified

        return new Booking(roomId, personId, startDate, endDate, isActive, id);
    }

    /**
     * Stores the details to edit the booking with. Each non-empty field value will replace the
     * corresponding field value of the booking.
     */
    public static class EditBookingDescriptor {
        private Integer roomId;
        private LocalDate startDate;
        private LocalDate endDate;

        public EditBookingDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditBookingDescriptor(EditBookingCommand.EditBookingDescriptor toCopy) {
            setRoomId(toCopy.roomId);
            setStartDate(toCopy.startDate);
            setEndDate(toCopy.endDate);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(roomId, startDate, endDate);
        }

        public void setRoomId(Integer roomId) {
            this.roomId = roomId;
        }

        public Optional<Integer> getRoomId() {
            return Optional.ofNullable(roomId);
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public Optional<LocalDate> getStartDate() {
            return Optional.ofNullable(startDate);
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }

        public Optional<LocalDate> getEndDate() {
            return Optional.ofNullable(endDate);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditBookingCommand.EditBookingDescriptor)) {
                return false;
            }

            // state check
            EditBookingCommand.EditBookingDescriptor e = (EditBookingCommand.EditBookingDescriptor) other;

            return getRoomId().equals(e.getRoomId())
                    && getStartDate().equals(e.getStartDate())
                    && getEndDate().equals(e.getEndDate());
        }
    }
}
