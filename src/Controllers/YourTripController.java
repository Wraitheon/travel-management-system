package Controllers;


import java.io.IOException;
import java.util.List;

import Models.TripTable;
import Models.dbhandler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class YourTripController {

    @FXML
    private TableView<TripTable> tripTableView; // Replace YourDataModel with the actual model class

    @FXML
    private TableColumn<TripTable, String> tripIdColumn; // Adjust types based on your data model
    @FXML
    private TableColumn<TripTable, String> destinationColumn;
    @FXML
    private TableColumn<TripTable, String> tripDateColumn;
    @FXML
    private TableColumn<TripTable, String> pricesColumn;
    @FXML
    private TableColumn<TripTable, String> numOfDaysColumn;


    @FXML
    private void initialize() {
        // Set up your columns (you may need to adjust these based on your data model)
       
        List<TripTable> tripList = dbhandler.getTripDataForUser(EmailController.email);
    
        TableColumn<TripTable, Number> tripIdColumn = new TableColumn<>("Trip ID");
        tripIdColumn.setCellValueFactory(cellData -> cellData.getValue().trip_IDProperty());
    
        TableColumn<TripTable, String> destinationColumn = new TableColumn<>("Destination ID");
        destinationColumn.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());
    
        TableColumn<TripTable, Number> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
    
        TableColumn<TripTable, String> tripDateColumn = new TableColumn<>("Trip Date");
        tripDateColumn.setCellValueFactory(cellData -> cellData.getValue().trip_DateProperty().asString());
    
        TableColumn<TripTable, Number> noOfDaysColumn = new TableColumn<>("Number of Days");
        noOfDaysColumn.setCellValueFactory(cellData -> cellData.getValue().noOfDaysProperty());
    
        tripTableView.getColumns().addAll(tripIdColumn, destinationColumn, priceColumn, tripDateColumn, noOfDaysColumn);
        tripTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

    
        //Set the data to the table
        tripTableView.setItems(FXCollections.observableArrayList(tripList));


        tripTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Check for a double-click
                TripTable selectedTrip = tripTableView.getSelectionModel().getSelectedItem();
                if (selectedTrip != null) {
                    // Perform navigation logic here (e.g., load another FXML)
                    navigateToTripDetails(event, selectedTrip);
                }
            }
        });
    }

    private void navigateToTripDetails(MouseEvent event, TripTable selectedTrip) {
    // Implement your navigation logic here, e.g., load another FXML
    // You can use FXMLLoader and Parent to load the new scene
    // Example:
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(NavigationLink.tripDetails));
        Parent root = loader.load();
        Stage stage;
        // Access the controller of the new page
        TripDetailsController controller = loader.getController();
        
        // Pass data to the controller if needed
        controller.setTripDest(selectedTrip);

        // Create a new scene
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1000, 800);
            stage.setScene(scene);
            stage.show();
    } catch (IOException e) {
        e.printStackTrace(); // Handle the exception appropriately
    }
}
    







    // Add necessary methods and logic for your controller
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

