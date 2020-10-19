package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.BookingBuilder;

public class BookingMatchesStartDatePredicateTest {
    LocalDate firstStartDate = LocalDate.of(2020, 10, 20);
    LocalDate secondStartDate = LocalDate.of(2020, 10, 25);

    @Test
    public void equals() {

        BookingMatchesStartDatePredicate firstPredicate = new BookingMatchesStartDatePredicate(firstStartDate);
        BookingMatchesStartDatePredicate secondPredicate = new BookingMatchesStartDatePredicate(secondStartDate);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BookingMatchesStartDatePredicate firstPredicateCopy = new BookingMatchesStartDatePredicate(firstStartDate);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_bookingMatchesStartDate_returnsTrue() {
        // One keyword
        BookingMatchesStartDatePredicate predicate = new BookingMatchesStartDatePredicate(firstStartDate);
        assertTrue(predicate.test(new BookingBuilder().withStartDate(firstStartDate).build()));
    }

    @Test
    public void test_bookingDoesNotMatchStartDate_returnsFalse() {
        // Zero keywords
        BookingMatchesStartDatePredicate predicate = new BookingMatchesStartDatePredicate(firstStartDate);
        assertFalse(predicate.test(new BookingBuilder().withStartDate(secondStartDate).build()));

    }
}
