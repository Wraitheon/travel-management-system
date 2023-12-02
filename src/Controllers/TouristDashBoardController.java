package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Models.Traveller;
import Models.User;
import Models.dbhandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class TouristDashBoardController implements Initializable{

    public static Traveller traveller;

    

    // @FXML
    // private Label titleLabel;

    // public void initData(String email) {
    //     userEmail = email;
    // }

    // @FXML
    // public void initialize() {
    //     titleLabel.setText("Hello from JavaFX Controller!");
    // }

    

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
        //NBC.Navigate(event, NavigationLink.touristViewTrips);    
    }
     @FXML
    private void handleBookTourGuide(ActionEvent event) {
        //NBC.Navigate(event, NavigationLink.touristViewTrips);    
    }
     @FXML
    private void handleBooking(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.touristBookings);    
    }


    @FXML
    private void handleReview(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.touristReview);    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         dbhandler db = new dbhandler();

        User user = db.getUserByEmail(EmailController.email);

        if (user instanceof Traveller) {
            traveller = (Traveller) user;
            // Now you can use traveller-specific methods or properties
        }
    }
}