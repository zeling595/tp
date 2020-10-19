package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.booking.Booking;
import seedu.address.model.roomservice.RoomService;

public class JsonAdaptedRoomService {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "RoomService's %s field is missing!";

    private final Integer bookingId;

    /**
     * Constructs a {@code JsonAdaptedRoomService} with the given booking details.
     */
    @JsonCreator
    public JsonAdaptedRoomService(@JsonProperty("bookingId") Integer bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Converts a given {@code RoomService} into this class for Jackson use.
     */
    public JsonAdaptedRoomService(RoomService source) {
        bookingId = source.getBookingId();
    }

    /**
     * Converts this Jackson-friendly adapted booking object into the model's {@code RoomService} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted booking.
     */
    public Booking toModelType() throws IllegalValueException {

        if (bookingId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "bookingId"));
        }
        return new RoomService(bookingId);
    }
}
