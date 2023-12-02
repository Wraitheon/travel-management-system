package Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Models.Accomodation;
import Models.Destination;
import Models.Restaurants;
import Models.Transportation;
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

    private int totalCost;
    @FXML 
    private TextField daysTextField;

    @FXML
    private ComboBox<Destination> destinationComboBox;
    private int destination_id = 0;


    @FXML
    private VBox dynamicComboBoxesRestaurant;

    @FXML
    private VBox dynamicComboBoxesDinner;

    @FXML
    private VBox dynamicComboBoxesAccomodation;

    @FXML
    private VBox dynamicComboBoxesTransportation;
    
    @FXML
    private Label costLabel;

    private int days;
    
    // @FXML
    // private ComboBox<Restaurants> resComboBox;

    // @FXML
    // private ComboBox<Accomodation> accommoComboBox;

    List<ComboBox<Restaurants>> restaurantsComboxes = new ArrayList<ComboBox<Restaurants>>(); 
    List<ComboBox<Accomodation>> accomodationComboBoxes = new ArrayList<ComboBox<Accomodation>>();    
    List<ComboBox<Transportation>> transportationComboBoxes = new ArrayList<ComboBox<Transportation>>();


    
    private List<Transportation> transportations = Arrays.asList(   Factory.createTransportation(1, "Air", null),
                                                                    Factory.createTransportation(2, "Coaster", null),
                                                                    Factory.createTransportation(3, "Bus", null),
                                                                    Factory.createTransportation(4, "Car", null));
                    
  
    public void initialize() {
        costLabel.setText("Estimated Cost " + 0);
        // Call this method to dynamically add ComboBox elements
        destinations = dbhandler.getDestinations();
        destinationComboBox.setItems(FXCollections.observableArrayList(destinations));
    

        daysTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Handle the Enter key press
                handleEnterKeyPress();
            }
        });
    }
    private void  handleEnterKeyPress(){
        if (daysTextField.getText().equals("")){
            return;
        }

        if (destinationComboBox.getValue() == null){
            
            AlertController.showAlert("Error", "Select Destination");

            return;
        }

        String daysText = daysTextField.getText();
        int days = (int) Double.parseDouble(daysText);
         if (days > 10){
            AlertController.showAlert("Error", "Maximum of 10 Days ALlowed");
            return;
        }
        restaurantsComboxes = new ArrayList<ComboBox<Restaurants>>();
        accomodationComboBoxes = new ArrayList<ComboBox<Accomodation>>();
        transportationComboBoxes = new ArrayList<ComboBox<Transportation>>();

        costLabel.setText("Estimated Cost " + 0);

        this.days = days;
        addDynamicComboBoxesRestaurant();
        addDynamicComboBoxesAccomodation();
        addDynamicComboBoxesTransportation();
    }


    @FXML
    private void handleDestinationChange(ActionEvent event){
        destination_id = destinationComboBox.getValue().getDestination_ID();
        fetchData();
        costLabel.setText("Estimated Cost " + 0);

        restaurantsComboxes = new ArrayList<ComboBox<Restaurants>>();
        accomodationComboBoxes = new ArrayList<ComboBox<Accomodation>>();
        transportationComboBoxes = new ArrayList<ComboBox<Transportation>>();

        dynamicComboBoxesRestaurant.getChildren().clear();
        dynamicComboBoxesDinner.getChildren().clear();
        dynamicComboBoxesAccomodation.getChildren().clear();
        dynamicComboBoxesTransportation.getChildren().clear();
        // itineraryItems = FXCollections.observableArrayList();
        // refreshListView();
        // fetchData();
    }

      private void fetchData(){
       
        restaurants = dbhandler.getRestaurantsForDestination(destination_id);
        //resComboBox.setItems(FXCollections.observableArrayList(restaurants));
        
        accomodations = dbhandler.getAccommodationsForDestination(destination_id);
        //accommoComboBox.setItems(FXCollections.observableArrayList(accomodations));
    }


    private void addDynamicComboBoxesAccomodation() {

        if (dynamicComboBoxesAccomodation.getChildren() != null) {
            dynamicComboBoxesAccomodation.getChildren().clear();
        }

        for (int i = 0; i < days - 1; i++) { 
            ComboBox<Accomodation> comboBox1 = new ComboBox<Accomodation>(FXCollections.observableArrayList(accomodations));   

             comboBox1.valueProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("ComboBox 1 selected: " + newValue);
                calculateCost();
            });

            comboBox1.setPromptText("Select Motel For Night "  + (i+1));       
            dynamicComboBoxesAccomodation.getChildren().add(comboBox1); 

            accomodationComboBoxes.add(comboBox1);
        }
    }

    private void addDynamicComboBoxesTransportation() {

    if (dynamicComboBoxesTransportation.getChildren() != null) {
        dynamicComboBoxesTransportation.getChildren().clear();
    }

    for (int i = 0; i < 2; i++) { 
        ComboBox<Transportation> comboBox2 = new ComboBox<Transportation>(FXCollections.observableArrayList(transportations));   

        comboBox2.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("ComboBox 2 selected: " + newValue);
            calculateCost();
        });
        if (i==0) {
            comboBox2.setPromptText("Select Transportation For Going ");  
        } else {
            comboBox2.setPromptText("Select Transportation For Return ");  

        }
        dynamicComboBoxesTransportation.getChildren().add(comboBox2); 

        transportationComboBoxes.add(comboBox2);
    }
}


    private void addDynamicComboBoxesRestaurant() {

        if (dynamicComboBoxesRestaurant.getChildren() != null) {
            dynamicComboBoxesRestaurant.getChildren().clear();
            dynamicComboBoxesDinner.getChildren().clear();
        }


        // Example data for ComboBox options
       
        // Create and add ComboBox elements dynamically
        for (int i = 0; i < days; i++) {
            System.out.println(restaurants.size());
            ComboBox<Restaurants> comboBox1 = new ComboBox<Restaurants>(FXCollections.observableArrayList(restaurants));        
            ComboBox<Restaurants> comboBox2 = new ComboBox<Restaurants>(FXCollections.observableArrayList(restaurants));

            comboBox1.valueProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("ComboBox 1 selected: " + newValue);
                calculateCost();
            });

            comboBox2.valueProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("ComboBox 2 selected: " + newValue);
                calculateCost();
            });

            comboBox1.setPromptText("Select BreakFast "  + (i+1));       
            comboBox2.setPromptText("Select Dinner " + (i+1));

            dynamicComboBoxesRestaurant.getChildren().add(comboBox1);            
            dynamicComboBoxesDinner.getChildren().add(comboBox2);

            restaurantsComboxes.add(comboBox1);   
            restaurantsComboxes.add(comboBox2);


        }
    }

    private void calculateCost() {
        totalCost = 0;
        for(var combox : restaurantsComboxes) {
            if (combox.getValue() != null){
                totalCost += combox.getValue().getCost();

            }
        }
        for(var combox : accomodationComboBoxes) {
            if (combox.getValue() != null){
                totalCost += combox.getValue().getCost();

            }
        }
        for(var combox : transportationComboBoxes) {
            if (combox.getValue() != null){
                totalCost += combox.getValue().getCost(destination_id);              
            }
        }
        costLabel.setText("Estimated Cost " + totalCost);
        System.out.println(totalCost);
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
