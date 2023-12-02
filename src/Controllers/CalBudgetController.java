package Controllers;

import java.util.Arrays;
import java.util.List;

import Models.Accomodation;
import Models.Destination;
import Models.Restaurants;
import Models.dbhandler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

public class CalBudgetController {

    private List<Restaurants> restaurants;
    private List<Accomodation> accomodations;    
    private List<Destination> destinations;

    @FXML 
    private TextField daysTextField;

    @FXML
    private ComboBox<Destination> destinationComboBox;
    private int destination_id = 0;


    @FXML
    private VBox dynamicComboBoxesContainer;

    @FXML
    private VBox dynamicLabelsContainer;
   
    
    @FXML
    private ComboBox<Restaurants> resComboBox;

    @FXML
    private ComboBox<Accomodation> accommoComboBox;

    @FXML
    private ComboBox<String> transportationComboBox;
    private List<String> transportations = Arrays.asList("Air", "Coaster", "Bus", "Car");
      
  
    public void initialize() {
        dbhandler db = new dbhandler();
        // Call this method to dynamically add ComboBox elements
        destinations = db.getDestinations();
        //transportationComboBox.setItems(FXCollections.observableArrayList(transportations));
        destinationComboBox.setItems(FXCollections.observableArrayList(destinations));
        addDynamicComboBoxes();

        daysTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Handle the Enter key press
                handleEnterKeyPress();
            }
        });
    }
    private void  handleEnterKeyPress(){
        
    }
    @FXML
    private void handleDestinationChange(ActionEvent event){
        destination_id = destinationComboBox.getValue().getDestination_ID();
        fetchData();
        addDynamicComboBoxes();
        
        // itineraryItems = FXCollections.observableArrayList();
        // refreshListView();
        // fetchData();
    }

      private void fetchData(){
        dbhandler db = new dbhandler();
        restaurants = db.getRestaurantsForDestination(destination_id);
        //resComboBox.setItems(FXCollections.observableArrayList(restaurants));

        accomodations = db.getAccommodationsForDestination(destination_id);
        //accommoComboBox.setItems(FXCollections.observableArrayList(accomodations));
    }
    

    private void addDynamicComboBoxes() {
        // Example data for ComboBox options
        String[] options = {"Option 1", "Option 2", "Option 3"};
        fetchData();
        // Create and add ComboBox elements dynamically
        for (int i = 0; i < 3; i++) {
            System.out.println(restaurants.size());
            ComboBox<Restaurants> comboBox = new ComboBox<Restaurants>(FXCollections.observableArrayList(restaurants));
            comboBox.setPromptText("Select an option");
            dynamicComboBoxesContainer.getChildren().add(comboBox);
        }
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
        //NBC.Navigate(event, NavigationLink.touristViewTrips);    
    }
     @FXML
    private void handleBooking(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.touristBookings);    
    }


    @FXML
    private void handleReview(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.touristReview);    }
}
