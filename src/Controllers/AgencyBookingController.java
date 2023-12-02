package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.BigDecimalStringConverter;

import java.math.BigDecimal;
import java.util.List;

import Models.Booking;
import Models.Payment;
import Models.BookingTable;
import Models.dbhandler;

public class AgencyBookingController {

    @FXML
    private TableView<Booking> bookingTableView;

    @FXML
    private TableColumn<Booking, Integer> bookingIdColumn;

    @FXML
    private TableColumn<Booking, Integer> tripIdColumn;

    @FXML
    private TableColumn<Booking, String> userEmailColumn;

    @FXML
    private TableColumn<Booking, String> bookingDateColumn;

    @FXML
    private TableView<BookingTable> paymentTableView;

    @FXML
    private TableColumn<BookingTable, String> userNameColumn;

    @FXML
    private TableColumn<BookingTable, String> tripDestinationColumn;

    @FXML
    private TableColumn<BookingTable, BigDecimal> tripPriceColumn;

    @FXML
    private TableColumn<BookingTable, Integer> tripNumberOfDaysColumn;

    @FXML
    private TableColumn<BookingTable, String> bookingDatePaymentTableColumn;

    @FXML
    private TableColumn<BookingTable, String> paymentDateColumn;

    @FXML
    private TableColumn<BookingTable, BigDecimal> amountColumn;

    @FXML
    private TableColumn<BookingTable, String> paymentMethodColumn;

    @FXML
    private TableColumn<BookingTable, BigDecimal> discountColumn;

    @FXML
    private TableColumn<BookingTable, BigDecimal> remainingPriceColumn;

    private TableColumn<Booking, Integer> paymentBookingIdColumn;

    private List<BookingTable> bookingTableList;
    NavBarController NBC = new NavBarController();

    @FXML
    private void initialize() {

        bookingTableList = dbhandler.getBookingTableData(EmailController.email);

        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        tripDestinationColumn.setCellValueFactory(new PropertyValueFactory<>("tripDestination"));
        tripPriceColumn.setCellValueFactory(new PropertyValueFactory<>("tripPrice"));
        tripNumberOfDaysColumn.setCellValueFactory(new PropertyValueFactory<>("tripNumberOfDays"));
        bookingDatePaymentTableColumn.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));
        paymentDateColumn.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        paymentMethodColumn.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));
        remainingPriceColumn.setCellValueFactory(new PropertyValueFactory<>("remainingPrice"));

        // Set the items in the paymentTableView
        paymentTableView.getItems().addAll(bookingTableList);
        paymentTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
    }

    @FXML
    private void handleYourTrips(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.yourTrip);
    }

    
    @FXML
    private void handleAddTrips(ActionEvent event) {
       NBC.Navigate(event, NavigationLink.addTrip);
    }

     @FXML
    private void handleHome(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.agencyDashboard);
    }

    @FXML
    private void handleBookings(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.agencyBooking);
    }

    @FXML
    private void handleAbout(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.agencyAbout);
    }
}
