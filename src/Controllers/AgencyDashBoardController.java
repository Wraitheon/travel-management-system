package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
public class AgencyDashBoardController {

    

    

    // @FXML
    // private Label titleLabel;

    public void initData(String email) {
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
