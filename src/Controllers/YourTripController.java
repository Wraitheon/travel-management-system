package Controllers;


import java.util.List;

import Models.Trip;
import Models.dbhandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;


public class YourTripController {

    @FXML
    private TableView<Trip> tripTableView; // Replace YourDataModel with the actual model class

    @FXML
    private TableColumn<Trip, String> tripIdColumn; // Adjust types based on your data model
    @FXML
    private TableColumn<Trip, String> destinationIdColumn;
    @FXML
    private TableColumn<Trip, String> tripDateColumn;
    @FXML
    private TableColumn<Trip, String> pricesColumn;
    @FXML
    private TableColumn<Trip, String> numOfDaysColumn;


    @FXML
    private void initialize() {
        // Set up your columns (you may need to adjust these based on your data model)
        // dbhandler db = new dbhandler();
        // List<Trip> tripList = db.getTripsByUserEmail(EmailController.email);
    
        // TableColumn<Trip, Number> tripIdColumn = new TableColumn<>("Trip ID");
        // tripIdColumn.setCellValueFactory(cellData -> cellData.getValue().trip_IDProperty());
    
        // TableColumn<Trip, Number> destinationIdColumn = new TableColumn<>("Destination ID");
        // destinationIdColumn.setCellValueFactory(cellData -> cellData.getValue().destination_idProperty());
    
        // TableColumn<Trip, Number> priceColumn = new TableColumn<>("Price");
        // priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
    
        // TableColumn<Trip, String> tripDateColumn = new TableColumn<>("Trip Date");
        // tripDateColumn.setCellValueFactory(cellData -> cellData.getValue().trip_DateProperty().asString());
    
        // TableColumn<Trip, Number> noOfDaysColumn = new TableColumn<>("Number of Days");
        // noOfDaysColumn.setCellValueFactory(cellData -> cellData.getValue().noOfDaysProperty());
    
        // tripTableView.getColumns().addAll(tripIdColumn, destinationIdColumn, priceColumn, tripDateColumn, noOfDaysColumn);
    
        // Set the data to the table
        // tripTableView.setItems(FXCollections.observableArrayList(tripList));
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
    }
}

