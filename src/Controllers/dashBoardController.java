package Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class dashBoardController {
    private Stage stage;

     @FXML
    private void handleYourTrips(ActionEvent event) {
        // Handle "Your Trips" button action
    }

    @FXML
    private void handleAddTrips(ActionEvent event, String userEmail) {
        // Handle "Add Trips" button action
        FXMLLoader loader = new FXMLLoader(getClass().getResource(NavigationLink.addTrip));
        try {
            Parent addTripParent = loader.load();
           
            AddTripController addTripController = loader.getController();

            addTripController.initData(userEmail);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(addTripParent, 600, 400);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }

     @FXML
    private void handleHome(ActionEvent event, String userEmail) {
        // Handle "Add Trips" button action
        FXMLLoader loader = new FXMLLoader(getClass().getResource(NavigationLink.agencyDashboard));
        try {
            Parent addTripParent = loader.load();
           
            AgencyDashBoardController agencyDashBoardController = loader.getController();

            agencyDashBoardController.initData(userEmail);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(addTripParent, 600, 400);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
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
