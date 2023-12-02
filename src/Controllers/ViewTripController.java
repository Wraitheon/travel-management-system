package Controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Models.AllTripViewTable;
import Models.Destination;
import Models.TravelAgency;
import Models.TripTable;
import Models.dbhandler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ViewTripController {

    @FXML
    private TableView<AllTripViewTable> allTripTableView;

    @FXML
    private TableColumn<AllTripViewTable, Integer> tripIdColumn;

    @FXML
    private TableColumn<AllTripViewTable, String> travelAgencyNameColumn;

    @FXML
    private TableColumn<AllTripViewTable, Double> priceColumn;

    @FXML
    private TableColumn<AllTripViewTable, LocalDate> tripDateColumn;

    @FXML
    private TableColumn<AllTripViewTable, Integer> noOfDaysColumn;
    
    @FXML
    private ComboBox<Destination> destinationComboBox;

    private  List<Destination> destinations;
    private  int  destination_id;

    public void initialize() {
        dbhandler db = new dbhandler();
        
        destinations = db.getDestinations();
        destinationComboBox.setItems(FXCollections.observableArrayList(destinations));


        tripIdColumn.setCellValueFactory(cellData -> cellData.getValue().trip_IDProperty().asObject());
        travelAgencyNameColumn.setCellValueFactory(cellData -> cellData.getValue().travelAgencyNameProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        tripDateColumn.setCellValueFactory(cellData -> cellData.getValue().trip_DateProperty());
        noOfDaysColumn.setCellValueFactory(cellData -> cellData.getValue().noOfDaysProperty().asObject());

                allTripTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);


        populateTable();

         allTripTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Check for a double-click
                AllTripViewTable selectedTrip = allTripTableView.getSelectionModel().getSelectedItem();
                if (selectedTrip != null) {
                    // Perform navigation logic here (e.g., load another FXML)
                    navigateToTripDetails(event, selectedTrip);
                }
            }
        });
        
    }

     private void navigateToTripDetails(MouseEvent event, AllTripViewTable selectedTrip) {
    // Implement your navigation logic here, e.g., load another FXML
    // You can use FXMLLoader and Parent to load the new scene
    // Example:
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(NavigationLink.touristTripDetails));
            Parent root = loader.load();
            Stage stage;
            // Access the controller of the new page
            TripDetailsTouristController controller = loader.getController();
            
            // Pass data to the controller if needed
            controller.setTripAgencyData(selectedTrip);

            // Create a new scene
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1000, 800);
                stage.setScene(scene);
                stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    private void populateTable(){
        List<AllTripViewTable> allTrips = new ArrayList<AllTripViewTable>();

        dbhandler db = new dbhandler();
        List<TravelAgency> agencies = db.getTravelAgencies();
        for(var agency : agencies){
            for(var trip : agency.getTrips()){
                if(trip.getDestination_id() == destination_id){
                    allTrips.add(new AllTripViewTable(trip.getTrip_ID(), agency.getName(), trip.getPrice(), trip.getTrip_Date(), trip.getN0OfDays()));
                }
            }
        } 
        
        allTripTableView.setItems(FXCollections.observableArrayList(allTrips));


        
    }

    @FXML
    private void handleDestinationChange(ActionEvent event){


        destination_id = destinationComboBox.getValue().getDestination_ID();
        populateTable();
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
        //NBC.Navigate(event, NavigationLink.touristViewTrips);    
    }
     @FXML
    private void handleBookTourGuide(ActionEvent event) {
        //NBC.Navigate(event, NavigationLink.touristViewTrips);    
    }
     @FXML
    private void handleBooking(ActionEvent event) {
        //NBC.Navigate(event, NavigationLink.touristViewTrips);    
    }


    @FXML
    private void handleReview(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.touristReview);    }
}
