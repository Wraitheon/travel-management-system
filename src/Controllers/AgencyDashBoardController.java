package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import javafx.util.StringConverter;

import Models.UserService;
import Models.dbhandler;
public class AgencyDashBoardController {

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
    }

    
    

       

}
