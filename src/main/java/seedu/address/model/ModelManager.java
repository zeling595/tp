package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.booking.Booking;
import seedu.address.model.person.Person;
import seedu.address.model.room.Room;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final RoomBook roomBook;
    private final BookingBook bookingBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs,
                        ReadOnlyRoomBook roomBook, ReadOnlyBookingBook bookingBook) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.roomBook = new RoomBook(roomBook);
        this.bookingBook = new BookingBook(bookingBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());

        // Initialize the nextAvailableId of Person class so that each new person gets a unique id
        Integer nextAvailableId = this.addressBook.getPersonList().stream()
                .mapToInt(Person::getId).max().orElse(0) + 1;
        Person.setNextAvailableId(nextAvailableId);

        // Initialize the nextAvailableId of Person class so that each new person gets a unique id
        Integer nextAvailableIdBooking = this.bookingBook.getBookingList().stream()
                .mapToInt(Booking::getId).max().orElse(0) + 1;
        Booking.setNextAvailableId(nextAvailableIdBooking);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new RoomBook(), new BookingBook());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public boolean hasPersonWithId(Integer id) {
        requireNonNull(id);
        return addressBook.hasPersonWithId(id);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== RoomBook ===================================================================================

    @Override
    public void addRoom(Room r) {
        this.roomBook.addRoom(r);
    }

    @Override
    public void setRooms(List<Room> rooms) {
        this.roomBook.setRooms(rooms);
    }

    @Override
    public void resetData(ReadOnlyRoomBook newData) {
        this.roomBook.resetData(newData);
    }

    @Override
    public void resetData(ReadOnlyBookingBook newData) {
        this.bookingBook.resetData(newData);
    }

    @Override
    public boolean hasRoom(int roomId) {
        return this.roomBook.hasRoom(roomId);
    }

    @Override
    public Room getRoom(int roomId) {
        return this.roomBook.getRoom(roomId);
    }

    @Override
    public ReadOnlyRoomBook getRoomBook() {
        return this.roomBook;
    }

    //=========== BookingBook ===================================================================================

    @Override
    public void addBooking(Booking b) {
        this.bookingBook.addBooking(b);
    }

    @Override
    public void setBookings(List<Booking> bookings) {
        this.bookingBook.setBookings(bookings);
    }

    @Override
    public Booking getBooking(int roomId) {
        return this.bookingBook.getBooking(roomId);
    }

    @Override
    public ReadOnlyBookingBook getBookingBook() {
        return this.bookingBook;
    }

    @Override
    public void setBookingInactive(int roomId) {
        this.bookingBook.setBookingInactive(roomId);
    }


    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && bookingBook.equals(other.bookingBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
