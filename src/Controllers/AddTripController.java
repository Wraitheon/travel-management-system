package Controllers;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



import Models.dbhandler;
import Models.Destination;
import Models.Restaurants;
import Models.Accomodation;
import Models.Transportation;
import Models.Trip;
import Models.Activity;
import Models.Itinerary;
import Models.ItineraryItem;







import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddTripController {



    // @FXML
    // private Label titleLabel;



    
    
    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<Destination> destinationComboBox;

    @FXML
    private ComboBox<Restaurants> resComboBox;

    @FXML
    private ComboBox<Accomodation> accommoComboBox;

    @FXML 
    private TextField costTextField;

    @FXML 
    private TextField daysTextField;


    @FXML
    private ListView<ItineraryItem> itineraryListView;

    // You might want to initialize the ListView with an ObservableList
    private ObservableList<ItineraryItem> itineraryItems = FXCollections.observableArrayList();


    private  List<Destination> destinations;
    private int destination_id = 0;

    private List<Restaurants> restaurants;
    private List<Accomodation> accomodations;
    
    private List<String> transportations = Arrays.asList("Air", "Coaster", "Bus", "Car", "Jeep");
    
    @FXML
    private ComboBox<String> transportationComboBox;
    

    

   
    public void initialize() {
    
        
        destinations = dbhandler.getDestinations();
        transportationComboBox.setItems(FXCollections.observableArrayList(transportations));
        destinationComboBox.setItems(FXCollections.observableArrayList(destinations));
    }

    @FXML
    private void handleDestinationChange(ActionEvent event){


        destination_id = destinationComboBox.getValue().getDestination_ID();
        itineraryItems = FXCollections.observableArrayList();
        refreshListView();
        fetchData();
    }

    private void fetchData(){
        restaurants = dbhandler.getRestaurantsForDestination(destination_id);
        resComboBox.setItems(FXCollections.observableArrayList(restaurants));

        accomodations = dbhandler.getAccommodationsForDestination(destination_id);
        accommoComboBox.setItems(FXCollections.observableArrayList(accomodations));
    }

    //-------------------------------Activity-------------------------------------

    @FXML
    private TextField activityNameTextField;
    @FXML
    private TextField activityDescriptionTextField;
    @FXML
    private TextField activityCostTextField;
    @FXML
    private DatePicker activityDatePicker;
    @FXML
    private Spinner<Integer> activityHourSpinner;
    @FXML
    private Spinner<Integer> activityMinuteSpinner;

    @FXML
    private void handleAddActivity(ActionEvent event) {
        if (checkDate(activityDatePicker.getValue())){
            return;
        }
         if (destination_id == 0) {
            showAlert("Error", "Destination is missing");
            return;
        }
         if ("".equals(activityNameTextField.getText()) || activityCostTextField.getText().equals("")) {
            showAlert("Error", "Information is missing");
            return;
        }
    
        LocalDate selectedDate = activityDatePicker.getValue();
        LocalDateTime selectedDateTime = LocalDateTime.of(selectedDate, LocalTime.of(activityHourSpinner.getValue(), activityMinuteSpinner.getValue()));

       
        String activityCostText = activityCostTextField.getText();
        double cost = Double.parseDouble(activityCostText);

        ItineraryItem newItem = new Activity(dbhandler.countActivities() + 1, activityNameTextField.getText(),
                activityDescriptionTextField.getText(), cost, selectedDateTime);
        newItem.setDateTime(selectedDateTime);

        itineraryItems.add(newItem);

        activityNameTextField.setText("");
        activityDescriptionTextField.setText("");
        activityCostTextField.setText("");
        fetchData();
        
    
        activityDatePicker.setValue(LocalDate.now());
    
        refreshListView();
    
    }

    //===============================Transport--------------------------------------

    @FXML
    private DatePicker transportationDatePicker;

    @FXML
    private Spinner<Integer> transportationHourSpinner;

    @FXML
    private Spinner<Integer> transportationMinuteSpinner;

    @FXML
    private void handleAddTransportation(ActionEvent event){
        if (destination_id == 0){
                showAlert("Error", "Destination is missing");
                return;
            }
        if (checkDate(transportationDatePicker.getValue())){
            return;
        }

                LocalDate selectedDate = transportationDatePicker.getValue();
        LocalDateTime selectedDateTime = LocalDateTime.of(selectedDate, LocalTime.of(transportationHourSpinner.getValue(), transportationMinuteSpinner.getValue()));

        ItineraryItem newItem = new Transportation(transportations.indexOf(transportationComboBox.getValue()) + 1, transportationComboBox.getValue(), null);

            newItem.setDateTime(selectedDateTime);
            System.out.println(newItem.getDateTime());

            itineraryItems.add(newItem);

            refreshListView();

    }

    //---------------------------------Accommodation---------------------------------------------------

    @FXML
    private Label l3;

    @FXML
    private Label l4;

    @FXML
    private Label l5;

    @FXML
    private DatePicker accommodationDatePicker;

    @FXML
    private TextField accommodationName;
    @FXML
    private TextField accommodationLocation;

    @FXML
    private TextField accommodationCost;

    @FXML
    private Spinner<Integer> accommodationHourSpinner;

    @FXML
    private Spinner<Integer> accommodationMinuteSpinner;

    @FXML
    private void handleAccommodationChange(ActionEvent event) {
        if (accommoComboBox.getValue() != null) {
            l3.setVisible(false);
            l4.setVisible(false);
            l5.setVisible(false);

            accommodationName.setVisible(false);
            accommodationCost.setVisible(false);
            accommodationLocation.setVisible(false);
        } else {
            l3.setVisible(true);
            l4.setVisible(true);
            l5.setVisible(true);

            accommodationName.setVisible(true);
            accommodationCost.setVisible(true);
            accommodationLocation.setVisible(true);

        }
    }

    @FXML
    private void handleAddAccommodation(ActionEvent event) {


        if (checkDate(accommodationDatePicker.getValue())){
            return;
        }

        LocalDate selectedDate = accommodationDatePicker.getValue();
        LocalDateTime selectedDateTime = LocalDateTime.of(selectedDate, LocalTime.of(accommodationHourSpinner.getValue(), accommodationMinuteSpinner.getValue()));

        if (accommoComboBox.getValue() != null) {
            System.out.println(accommoComboBox.getValue());

            ItineraryItem newItem = new Accomodation(accommoComboBox.getValue().getId(), accommoComboBox.getValue().getLocation(), accommoComboBox.getValue().getName(), accommoComboBox.getValue().getCost(), null);

            newItem.setDateTime(selectedDateTime);
            System.out.println(newItem.getDateTime());

            itineraryItems.add(newItem);
        } else {
            if (destination_id == 0){
                showAlert("Error", "Destination is missing");
                return;
            }
            if ("".equals(accommodationName.getText()) || accommodationCost.getText().equals("")) {
                showAlert("Error", "Information is missing");
                return;
            }

            
            String accommodationCostText = accommodationCost.getText();
            double cost = Double.parseDouble(accommodationCostText);

            ItineraryItem newItem = new Accomodation(dbhandler.countAccommodations() + 1, "",accommodationName.getText(), cost,  selectedDateTime);
            newItem.setDateTime(selectedDateTime);

            dbhandler.addAccommodation(destination_id, accommodationLocation.getText(), accommodationName.getText(), cost);
            itineraryItems.add(newItem);

            accommodationName.setText("");
            accommodationCost.setText("");
            accommodationLocation.setText("");
            fetchData();
        }

        accommodationDatePicker.setValue(LocalDate.now());

        refreshListView();
    }
   

    //--------------------------------Restaurant-----------------------------------------------------------

    @FXML 
    private Label l1;

    @FXML 
    private Label l2;

    @FXML
    private DatePicker resdatePicker;

    @FXML
    private TextField resName;
    @FXML
    private TextField resCost;
    @FXML
    private Spinner<Integer> hourSpinner;
    @FXML
    private Spinner<Integer> minuteSpinner;
    



     @FXML
    private void handleResChange(ActionEvent event) {

        if (resComboBox.getValue() != null) {
            l1.setVisible(false);
            l2.setVisible(false);
            resName.setVisible(false);
            resCost.setVisible(false);
        } else {
             l1.setVisible(true);
            l2.setVisible(true);
            resName.setVisible(true);
            resCost.setVisible(true);
        }
        // Handle "Your Trips" button action
    }

     @FXML
    private void handleAddRestaurant(ActionEvent event) {
                
                if (checkDate(resdatePicker.getValue())){
                    return;
                }
                
                LocalDate selectedDate = resdatePicker.getValue();
                LocalDateTime selectedDateTime = LocalDateTime.of(selectedDate, LocalTime.of(hourSpinner.getValue(), minuteSpinner.getValue()));

                if (resComboBox.getValue() != null){
                    System.out.println(resComboBox.getValue());

                    ItineraryItem newItem = new Restaurants(resComboBox.getValue().getRestaurant_id(), null, resComboBox.getValue().getName(), resComboBox.getValue().getCost());


                    newItem.setDateTime(selectedDateTime);
                    System.out.println(newItem.getDateTime());

                    itineraryItems.add(newItem);
                } else {
                    if (destination_id == 0){
                        showAlert("Error", "Destination is missing");
                        return;
                    }
                    if ("".equals( resName.getText()) || resCost.getText().equals("")){
                        showAlert("Error", "Information is missing");

                        return;
                    }

                    
                    String resCostText = resCost.getText();
                    double cost = Double.parseDouble(resCostText);
                    
                    ItineraryItem newItem = new Restaurants(dbhandler.countRestaurants()+1, selectedDateTime, resName.getText(), cost);
                     newItem.setDateTime(selectedDateTime);

                     

                     dbhandler.addRestaurant(destination_id, resName.getText(), cost);
                     itineraryItems.add(newItem);

                     resName.setText("");
                     resCost.setText("");
                     fetchData();
                }
                resdatePicker.setValue(LocalDate.now());

            refreshListView();
        // Handle "Your Trips" button action
    }

    //--------------------------------------------------------------------------------------------------
    @FXML
    private void handleDeleteItem(ActionEvent event) {
        // Get the selected index
        int selectedIndex = itineraryListView.getSelectionModel().getSelectedIndex();
        System.out.println(selectedIndex);
        // Check if an item is selected
        if (selectedIndex >= 0) {
            // Remove the selected item
            itineraryItems.remove(selectedIndex);
        } else {
            // No item selected, show an alert or perform other actions
            System.out.println("No item selected.");
        }
        refreshListView();
    }

    private void refreshListView() {
        // Set the items of the ListView again to trigger an update
                // System.out.println("sjhdvb");
        Collections.sort(itineraryItems, Comparator.comparing(ItineraryItem::getDateTime));
        itineraryListView.setItems(FXCollections.observableArrayList(itineraryItems));
    }

    private Boolean checkDate(LocalDate date){
         String daysText = daysTextField.getText();
        int days = (int) Double.parseDouble(daysText);

        if (date == null || datePicker.getValue() ==null) {
            showAlert("Date not Selected", "Kindly Enter a date");
            return true;
        } else if (date.isAfter(datePicker.getValue().plusDays(days))|| date.isBefore(datePicker.getValue())) {
            showAlert("Irrelevant Date", "Kindly Enter a date with in Trip dates");
            return true;
        }


        return false;


    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    // ----------------------ADD TRip To DB --------------------------------

    @FXML
    private void handleAddTripToDB(ActionEvent event){

        if (datePicker.getValue().isBefore(LocalDate.now())){
            showAlert("Error", "Trip cannot be in past");

            return;
        }

        if (datePicker.getValue() == null || "".equals(costTextField.getText()) || "".equals(daysTextField.getText())) {
            showAlert("Error", "Information is missing");

            return;
        }
        if (destination_id == 0){
                        showAlert("Error", "Destination is missing");
                        return;
        }
        System.out.println(EmailController.email);
        List<ItineraryItem> items = new ArrayList<>(itineraryItems);

        Itinerary newItinerary = Factory.creatItinerary(items);

        

        String costText = costTextField.getText();
        double cost = Double.parseDouble(costText);

        String daysText = daysTextField.getText();
        int days = (int) Double.parseDouble(daysText);

        Trip newTrip = Factory.createTrip(cost, datePicker.getValue(), days, newItinerary);

        newTrip.insertModelToDb(destination_id);

        AlertController.showConfirmation("SuccessFul", "YourTrip has been added SuccessFully");

        NBC.Navigate(event, NavigationLink.agencyDashboard);


    }


    //------------------------------------------------------------------------------------------------------

    
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
           NBC.Navigate(event, NavigationLink.agencyAbout);
       }
    // Other methods...
}
