package Controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Models.GuideBooking;
import Models.User;
import Models.dbhandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class GuideBookingsController implements Initializable {
    

    @FXML
    private TableView<GuideBooking> bookingTableView;

    private List<GuideBooking> bookings = dbhandler.fetchGuideBookingsForTraveller(EmailController.email);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

         TableColumn<GuideBooking, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("bookedby_email"));

        TableColumn<GuideBooking, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<GuideBooking, String> daysColumn = new TableColumn<>("No Of Days");
        daysColumn.setCellValueFactory(new PropertyValueFactory<>("noOfDays"));

        // TableColumn<Review, LocalDate> dateColumn = new TableColumn<>("Date");
        // tourGuideTable.setCellValueFactory(new PropertyValueFactory<>("date"));


        bookingTableView.getColumns().addAll(nameColumn, dateColumn, daysColumn);

        bookingTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        bookingTableView.getItems().addAll(bookings);

    }


    //----------------------------------------------------------------------------------------------------------------------------------------------------

    NavBarController NBC = new NavBarController();
    @FXML
    private void handleViewTrips(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.touristViewTrips);    
    }

     @FXML
    private void handleHome(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.touristDashboard);    
    }

    @FXML
    private void handleCalBudget(ActionEvent event) {
       NBC.Navigate(event, NavigationLink.budget);    
    }
     @FXML
    private void handleBookTourGuide(ActionEvent event) {
         NBC.Navigate(event, NavigationLink.bookGuide);      
    }
     @FXML
    private void handleBooking(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.touristBookings);    
    }


    @FXML
    private void handleReview(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.touristReview);    
    }
     @FXML
    private void handleLocal(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.touristReview);    
    }

     @FXML
    private void handleChat(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.chat);    
    }

     @FXML
    private void handleGuideBooking(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.guideBookings);    
    }


}
