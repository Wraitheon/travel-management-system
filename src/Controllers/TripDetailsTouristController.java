package Controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import Models.AllTripViewTable;
import Models.ItineraryItem;
import Models.Trip;


public class TripDetailsTouristController implements Initializable {
    private AllTripViewTable tripdest;
    private Trip trip;


    @FXML
    private Label destinationLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label tripDateLabel;

    @FXML
    private Label noOfDaysLabel;

    @FXML
    private Label agencyNameLabel;

    @FXML 
    private Label rating;

    @FXML
    private ProgressBar ratingsProgressBar;


    @FXML
    private ListView<ItineraryItem> itineraryListView;

    private ObservableList<ItineraryItem> itineraryItems = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize your controller (if needed)
    }

    public void setTripAgencyData(AllTripViewTable trip){
        this.tripdest = trip;

        this.trip = new Trip(tripdest.getTrip_ID(), tripdest.getPrice(), tripdest.getTrip_Date(), tripdest.getNoOfDays());

         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

            // Format the LocalDateTime using the formatter
        String formattedDateTime = trip.getTrip_Date().format(formatter);
        
        setTripDetails(this.trip.getDestination(), trip.getPrice(), formattedDateTime, trip.getNoOfDays(), trip.getTravelAgencyName(), this.trip.getAgencyRating());

        this.trip.fetchItinerary();

        setItineraryItems(this.trip.getItinerary().getItineraryItems());

        setProgressBarProgress(this.trip.getAgencyRating()/10.0);
    } 

    public void setTripDetails( String destination, double price, String tripDate, int noOfDays, String agency, double rate) {
        
        destinationLabel.setText("Destination: " + destination);
        agencyNameLabel.setText("Travel Agency: " + agency);
        priceLabel.setText("Price: " + price);
        tripDateLabel.setText("Trip Date: " + tripDate);
        noOfDaysLabel.setText("Number of Days: " + noOfDays);
        rating.setText("" + rate);
    }

    public void setItineraryItems(List<ItineraryItem> items) {
        itineraryItems.setAll(items);
        itineraryListView.setItems(itineraryItems);
    }


    public void setProgressBarProgress(double progress) {
        ratingsProgressBar.setProgress(progress);
    }

    private void navigateToBookTrip(ActionEvent event) {
    // Implement your navigation logic here, e.g., load another FXML
    // You can use FXMLLoader and Parent to load the new scene
    // Example:
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(NavigationLink.touristBookTrip));
            Parent root = loader.load();
            Stage stage;
            // Access the controller of the new page
            BookTripController controller = loader.getController();
            
            // Pass data to the controller if needed
            controller.setTripData(trip);

            // Create a new scene
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1000, 800);
                stage.setScene(scene);
                stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
    

    NavBarController NBC = new NavBarController();
    
     @FXML
    private void handleBook(ActionEvent event) {
        navigateToBookTrip(event);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        // Handle the back button action here
        System.out.println("Back button clicked");
        NBC.Navigate(event, NavigationLink.touristViewTrips);
        // You can navigate to the previous page or perform any other action
    }
}
