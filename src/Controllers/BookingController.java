package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.BigDecimalStringConverter;

import java.math.BigDecimal;
import java.util.List;

import Models.Booking;
import Models.Payment;
import Models.dbhandler;

public class BookingController {

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
    private TableView<Payment> paymentTableView;

    @FXML
    private TableColumn<Payment, Integer> paymentIdColumn;

    @FXML
    private TableColumn<Payment, Integer> bookingIdPaymentColumn;

    @FXML
    private TableColumn<Payment, String> paymentDateColumn;

    @FXML
    private TableColumn<Payment, BigDecimal> amountColumn;

    @FXML
    private TableColumn<Payment, String> paymentMethodColumn;

    private TableColumn<Booking, Integer> paymentBookingIdColumn;

    @FXML
    private void initialize() {
        // Initialize columns (assuming you have defined them in FXML)
        bookingIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        tripIdColumn.setCellValueFactory(new PropertyValueFactory<>("tripId"));
        userEmailColumn.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        bookingDateColumn.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));

        paymentIdColumn.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        paymentBookingIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        paymentDateColumn.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        paymentMethodColumn.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
    }

}