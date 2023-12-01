package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class TouristDashBoardController {

    private String userEmail;
    private Stage stage;

    

    // @FXML
    // private Label titleLabel;

    public void initData(String email) {
        userEmail = email;
    }

    // @FXML
    // public void initialize() {
    //     titleLabel.setText("Hello from JavaFX Controller!");
    // }

    @FXML
    private Button gotToAddTrip;

    NavBarController NBC = new NavBarController();
    @FXML
    private void handleViewTrips(ActionEvent event) {
        // NBC.Navigate(event, NavigationLink.touristViewTrips);    
    }

     @FXML
    private void handleHome(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.touristDashboard);    
    }

    @FXML
    private void handleCalBudget(ActionEvent event) {
        //NBC.Navigate(event, NavigationLink.touristViewTrips);    
    }
     @FXML
    private void handleBookTourGuide(ActionEvent event) {
        //NBC.Navigate(event, NavigationLink.touristViewTrips);    
    }
     @FXML
    private void handleBooking(ActionEvent event) {
        //NBC.Navigate(event, NavigationLink.touristViewTrips);    
    }


    @FXML
    private void handleReview(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.touristReview);    }
}