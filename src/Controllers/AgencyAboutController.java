package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;

import Models.Review;
import Models.TravelAgency;

public class AgencyAboutController {

    TravelAgency travelAgency =  AgencyDashBoardController.travelAgency;

    @FXML
    private Label agencyNameLabel;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private ProgressBar ratingsProgressBar;

    @FXML
    private Label rating;

    @FXML
    private TableView<Review> userCommentsTable;

    

    @FXML
    public void initialize() {
        // Initialize the controller, if needed
        rating.setText("" + travelAgency.avgRating());
        setProgressBarProgress(travelAgency.avgRating()/10.0);

        agencyNameLabel.setText(travelAgency.getName());      
        phoneNumberLabel.setText(travelAgency.getPhoneNumber());


        initializeTableColumns();
        // Example data, you can replace this with your actual data
        
        travelAgency.fetchReviews();
        List<Review> reviews = travelAgency.getReviews();
        populateTable(reviews);
    }

    public void setProgressBarProgress(double progress) {
        ratingsProgressBar.setProgress(progress);
    }

    private void initializeTableColumns() {

        

        TableColumn<Review, String> userEmailColumn = new TableColumn<>("User Email");
        userEmailColumn.setCellValueFactory(new PropertyValueFactory<>("traveller_email"));

        TableColumn<Review, String> commentColumn = new TableColumn<>("Comment");
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));

        TableColumn<Review, Double> ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

        TableColumn<Review, LocalDate> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));


        userCommentsTable.getColumns().addAll(userEmailColumn, commentColumn, ratingColumn, dateColumn);

        userCommentsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
    }

    private void populateTable(List<Review> comments) {
        userCommentsTable.getItems().addAll(comments);
    }


        // Add necessary methods and logic for your controller
     NavBarController NBC = new NavBarController();
    @FXML
    private void handleYourTrips(ActionEvent event) {
        // Handle "Your Trips" button action
        NBC.Navigate(event, NavigationLink.yourTrip);
    }

    
    @FXML
    private void handleAddTrips(ActionEvent event) {
        // Handle "Add Trips" button action
       
       NBC.Navigate(event, NavigationLink.addTrip);
        
    }

     @FXML
    private void handleHome(ActionEvent event) {
        // Handle "Add Trips" button action
       
        NBC.Navigate(event, NavigationLink.agencyDashboard);

        
    }

    @FXML
    private void handleBookings(ActionEvent event) {
        // Handle "Bookings" button action
    }

    @FXML
    private void handleAbout(ActionEvent event) {
        // Handle "About" button action
        NBC.Navigate(event, NavigationLink.agencyAbout);
    }
    // Additional methods for handling user interactions or other actions

}
