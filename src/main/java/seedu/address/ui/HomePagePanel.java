package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.booking.Booking;

public class HomePagePanel extends UiPart<Region> {
    private static final String FXML = "HomePagePanel.fxml";

    @FXML
    private ListView recentBookingsView;

    /**
     * Creates a HomePagePanel
     */
    public HomePagePanel(ObservableList<Booking> bookingList) {
        super(FXML);
        SortedList<Booking> sortedBookingList = new SortedList<>(bookingList, (o1, o2)
                -> o2.getStartDate().compareTo(o1.getStartDate()));
        recentBookingsView.setItems(sortedBookingList);
        recentBookingsView.setCellFactory(listView -> new RecentBookingCell());
    }

    class RecentBookingCell extends ListCell<Booking> {
        @Override
        protected void updateItem(Booking booking, boolean empty) {
            if (getIndex() > 2) {
                setGraphic(null);
                setText(null);
                return;
            }

            super.updateItem(booking, empty);
            if (empty || booking == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new Label(booking.toString()));
            }
        }
    }
}
