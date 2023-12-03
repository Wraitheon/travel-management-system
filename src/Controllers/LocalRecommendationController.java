package Controllers;


import java.util.ArrayList;
import java.util.List;

import Models.Accomodation;
import Models.Destination;
import Models.Restaurants;
import Models.Transportation;
import Models.dbhandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.VBox;

public class LocalRecommendationController {

    @FXML
    private VBox root;

    @FXML
    private ComboBox<Destination> destinationComboBox;

    @FXML
    private ListView<String> itineraryListView;

    private int destination_id;
    private List<Destination> destinations;

    public LocalRecommendationController() {
        // Initialization, if needed
    }

    @FXML
    private void initialize() {
        // Initialize the controller, if needed
        // For example, you can set items in the destinationComboBox
        destinations = dbhandler.getDestinations();
        destinationComboBox.setItems(FXCollections.observableArrayList(destinations));

        // Customize the appearance of the ListView cells as needed
        itineraryListView.setCellFactory(TextFieldListCell.forListView());
    }

    @FXML
    private void handleDestinationChange(ActionEvent event){
        destination_id = destinationComboBox.getValue().getDestination_ID();
       
        ObservableList<String> landmarks = dbhandler.getLandmarksForDestination(destination_id);
        itineraryListView.setItems(landmarks);
        
    }

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
        NBC.Navigate(event, NavigationLink.localRecommendation);    
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
