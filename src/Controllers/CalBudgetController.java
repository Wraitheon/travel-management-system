package Controllers;

import java.util.Arrays;
import java.util.List;

import Models.Accomodation;
import Models.Destination;
import Models.Restaurants;
import Models.dbhandler;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class CalBudgetController {

    private List<Restaurants> restaurants;
    private List<Accomodation> accomodations;    
    private List<Destination> destinations;

    

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
      
    private void fetchData(){
        dbhandler db = new dbhandler();
        restaurants = db.getRestaurantsForDestination(destination_id);
        resComboBox.setItems(FXCollections.observableArrayList(restaurants));

        accomodations = db.getAccommodationsForDestination(destination_id);
        accommoComboBox.setItems(FXCollections.observableArrayList(accomodations));
    }
    @FXML
    public void initialize() {
        dbhandler db = new dbhandler();
        // Call this method to dynamically add ComboBox elements
        destinations = db.getDestinations();
        transportationComboBox.setItems(FXCollections.observableArrayList(transportations));
        destinationComboBox.setItems(FXCollections.observableArrayList(destinations));
        addDynamicComboBoxes();
    }

    private void addDynamicComboBoxes() {
        // Example data for ComboBox options
        String[] options = {"Option 1", "Option 2", "Option 3"};

        // Create and add ComboBox elements dynamically
        for (int i = 0; i < 3; i++) {
            ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(options));
            comboBox.setPromptText("Select an option");
            dynamicComboBoxesContainer.getChildren().add(comboBox);
        }
    }
}
