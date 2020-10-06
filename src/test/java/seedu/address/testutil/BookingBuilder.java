package seedu.address.testutil;

import seedu.address.model.booking.Booking;

import java.time.LocalDate;

public class BookingBuilder {

    public static final Integer DEFAULT_ID = 1;
    public static final String DEFAULT_ROOM_ID = "1001";
    public static final String DEFAULT_PERSON_ID = "1";
    public static final String DEFAULT_START_DATE = "2020-10-20";
    public static final String DEFAULT_END_DATE = "2020-10-25";
    public static final boolean DEFAULT_IS_ACTIVE = false;

    private Integer id;
    private Integer roomId;
    private Integer personId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;

    /**
     * Creates a {@code BookingBuilder} with the default details.
     */
    public BookingBuilder() {
        id = DEFAULT_ID;
        roomId = Integer.parseInt(DEFAULT_ROOM_ID);
        personId = Integer.parseInt(DEFAULT_PERSON_ID);
        startDate = LocalDate.parse(DEFAULT_START_DATE);
        endDate = LocalDate.parse(DEFAULT_END_DATE);
        isActive = DEFAULT_IS_ACTIVE;
    }

    /**
     * Initializes the BookingBuilder with the data of {@code personToCopy}.
     */
    public BookingBuilder(Booking bookingToCopy) {
        id = bookingToCopy.getId();
        roomId = bookingToCopy.getRoomId();
        personId = bookingToCopy.getPersonId();
        startDate = bookingToCopy.getStartDate();
        endDate = bookingToCopy.getEndDate();
        isActive = bookingToCopy.isActive();
    }

    /**
     * Sets the roomId of the {@code Booking} that we are building.
     */
    public BookingBuilder withRoomId(String roomId) {
        this.roomId = Integer.parseInt(roomId);
        return this;
    }

    /**
     * Sets the {@code personId} of the {@code Booking} that we are building.
     */
    public BookingBuilder withPersonId(String personId) {
        this.personId = Integer.parseInt(personId);
        return this;
    }

    /**
     * Sets the {@code startDate} of the {@code Booking} that we are building.
     */
    public BookingBuilder withStartDate(String StartDate) {
        this.startDate = LocalDate.parse(StartDate);
        return this;
    }

    /**
     * Sets the {@code endDate} of the {@code Booking} that we are building.
     */
    public BookingBuilder withEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate);
        return this;
    }

    /**
     * Sets the {@code isActive} of the {@code Booking} that we are building.
     */
    public BookingBuilder withIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    /**
     * Sets the {@code id} of the {@code Booking} that we are building.
     */
    public BookingBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public Booking build() {
        return new Booking(roomId, personId, startDate, endDate, isActive, id);
    }

}
