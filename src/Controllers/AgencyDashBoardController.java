package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Models.TravelAgency;
import Models.Traveller;
import Models.User;
import Models.dbhandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class AgencyDashBoardController implements Initializable {


    public static TravelAgency travelAgency;

    // @FXML
    // private Label titleLabel;
     @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbhandler db = new dbhandler();

        travelAgency = db.getTravelAgencyByEmail(EmailController.email);

       
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
        // Handle "About" button action
        NBC.Navigate(event, NavigationLink.agencyAbout);
    }


   
    
    

       

}
