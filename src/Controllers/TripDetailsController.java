package Controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import Models.ItineraryItem;
import Models.Trip;
import Models.TripTable;


public class TripDetailsController implements Initializable {

    private TripTable tripdest;
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
    private ListView<ItineraryItem> itineraryListView;

    private ObservableList<ItineraryItem> itineraryItems = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize your controller (if needed)
    }

    public void setTripDest(TripTable trip){
        this.tripdest = trip;

        this.trip = new Trip(tripdest.getTrip_ID(), tripdest.getPrice(), tripdest.getTrip_Date(), tripdest.getNoOfDays());

         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

            // Format the LocalDateTime using the formatter
        String formattedDateTime = trip.getTrip_Date().format(formatter);
        
        setTripDetails(trip.getDestination(), trip.getPrice(), formattedDateTime, trip.getNoOfDays());

        this.trip.fetchItinerary();

        setItineraryItems(this.trip.getItinerary().getItineraryItems());
    } 

    public void setTripDetails( String destination, double price, String tripDate, int noOfDays) {
        
        destinationLabel.setText("Destination: " + destination);
        priceLabel.setText("Price: " + price);
        tripDateLabel.setText("Trip Date: " + tripDate);
        noOfDaysLabel.setText("Number of Days: " + noOfDays);
    }

    public void setItineraryItems(List<ItineraryItem> items) {
        itineraryItems.setAll(items);
        itineraryListView.setItems(itineraryItems);
    }
    NavBarController NBC = new NavBarController();

    @FXML
    private void handleBack(ActionEvent event) {
        // Handle the back button action here
        System.out.println("Back button clicked");
        NBC.Navigate(event, NavigationLink.yourTrip);
        // You can navigate to the previous page or perform any other action
    }
}

