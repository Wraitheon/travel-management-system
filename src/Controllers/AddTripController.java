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
import Models.OdysseyHub;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddTripController {    
    
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

    private int destination_id = 0;

    
    
    @FXML
    private ComboBox<String> transportationComboBox;
    
    List<String> transportations = Arrays.asList("Air", "Coaster", "Bus", "Car", "Jeep");
    
    //fecth iti list
    //update iti list
    //add act
   
    public void initialize() {
        transportationComboBox.setItems(FXCollections.observableArrayList(transportations));
        destinationComboBox.setItems(FXCollections.observableArrayList(OdysseyHub.getSystem().fetchDestinations()));
    }

    @FXML
    private void handleDestinationChange(ActionEvent event){


        destination_id = destinationComboBox.getValue().getDestination_ID();
        itineraryItems = FXCollections.observableArrayList();
        refreshListView();
        fetchData();
    }

    private void fetchData(){

        List<ItineraryItem> list = OdysseyHub.getSystem().fetchItineraryItems(destination_id);

        List<Restaurants> restaurants = new ArrayList<Restaurants>();
        List<Accomodation> accomodations = new ArrayList<Accomodation>();
        for(var item: list){
            if (item instanceof Restaurants){
                restaurants.add((Restaurants)item);
            } else {
                accomodations.add((Accomodation)item);
            }
        }

        resComboBox.setItems(FXCollections.observableArrayList(restaurants));

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
            AlertController.showAlert("Error", "Destination is missing");
            return;
        }
         if ("".equals(activityNameTextField.getText()) || activityCostTextField.getText().equals("")) {
            AlertController.showAlert("Error", "Information is missing");
            return;
        }
    
        LocalDate selectedDate = activityDatePicker.getValue();
        LocalDateTime selectedDateTime = LocalDateTime.of(selectedDate, LocalTime.of(activityHourSpinner.getValue(), activityMinuteSpinner.getValue()));

       
        String activityCostText = activityCostTextField.getText();
        double cost = Double.parseDouble(activityCostText);

        OdysseyHub.getSystem().addActivity(activityNameTextField.getText(),
        activityDescriptionTextField.getText(), cost, selectedDateTime);



       itineraryItems = FXCollections.observableArrayList(OdysseyHub.getSystem().getItineraryItems());
        
    
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
                AlertController.showAlert("Error", "Destination is missing");
                return;
            }
        if (checkDate(transportationDatePicker.getValue())){
            return;
        }

                LocalDate selectedDate = transportationDatePicker.getValue();
        LocalDateTime selectedDateTime = LocalDateTime.of(selectedDate, LocalTime.of(transportationHourSpinner.getValue(), transportationMinuteSpinner.getValue()));

        OdysseyHub.getSystem().add_itineraryItems(transportations.indexOf(transportationComboBox.getValue()) + 1, selectedDateTime, "transportation");

            itineraryItems = FXCollections.observableArrayList(OdysseyHub.getSystem().getItineraryItems());

            refreshListView();

    }

    //---------------------------------Accommodation---------------------------------------------------



    @FXML
    private DatePicker accommodationDatePicker;

    @FXML
    private Spinner<Integer> accommodationHourSpinner;

    @FXML
    private Spinner<Integer> accommodationMinuteSpinner;

    @FXML
    private void handleAccommodationChange(ActionEvent event) {}

    @FXML
    private void handleAddAccommodation(ActionEvent event) {


        if (checkDate(accommodationDatePicker.getValue())){
            return;
        }

        LocalDate selectedDate = accommodationDatePicker.getValue();
        LocalDateTime selectedDateTime = LocalDateTime.of(selectedDate, LocalTime.of(accommodationHourSpinner.getValue(), accommodationMinuteSpinner.getValue()));

        if (accommoComboBox.getValue() != null) {
            System.out.println(accommoComboBox.getValue());

            OdysseyHub.getSystem().add_itineraryItems(accommoComboBox.getValue().getId(), selectedDateTime, "accommodation");

           itineraryItems = FXCollections.observableArrayList(OdysseyHub.getSystem().getItineraryItems());
        }
       

        accommodationDatePicker.setValue(LocalDate.now());
        refreshListView();
    }
   

    //--------------------------------Restaurant-----------------------------------------------------------



    @FXML
    private DatePicker resdatePicker;

    @FXML
    private Spinner<Integer> hourSpinner;
    @FXML
    private Spinner<Integer> minuteSpinner;
    



     @FXML
    private void handleResChange(ActionEvent event) { }

     @FXML
    private void handleAddRestaurant(ActionEvent event) {
                
                if (checkDate(resdatePicker.getValue())){
                    return;
                }
                
                LocalDate selectedDate = resdatePicker.getValue();
                LocalDateTime selectedDateTime = LocalDateTime.of(selectedDate, LocalTime.of(hourSpinner.getValue(), minuteSpinner.getValue()));

                if (resComboBox.getValue() != null){
                    System.out.println(resComboBox.getValue());

                    OdysseyHub.getSystem().add_itineraryItems(resComboBox.getValue().getRestaurant_id(), selectedDateTime, "restaurant");



                    itineraryItems = FXCollections.observableArrayList(OdysseyHub.getSystem().getItineraryItems());
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
            OdysseyHub.getSystem().setItineraryItems(itineraryItems);
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
        
        if (daysTextField.getText().equals("")){
            AlertController.showAlert("Error", "Number of days missing, Kindly Enter a numebr of days");

            return true;
        }

        String daysText = daysTextField.getText();
        int days = (int) Double.parseDouble(daysText);

        if (date == null || datePicker.getValue() ==null) {
            AlertController.showAlert("Date not Selected", "Kindly Enter a date");
            return true;
        } else if (date.isAfter(datePicker.getValue().plusDays(days))|| date.isBefore(datePicker.getValue())) {
            AlertController.showAlert("Irrelevant Date", "Kindly Enter a date with in Trip dates");
            return true;
        }

        return false;
    }

   
    // ----------------------ADD TRip To DB --------------------------------

    @FXML
    private void handleAddTripToDB(ActionEvent event){

        //----------------------Checks ----------------------------------
       
        if (datePicker.getValue() == null || "".equals(costTextField.getText()) || "".equals(daysTextField.getText())) {
            AlertController.showAlert("Error", "Information is missing");

            return;
        }
         if (datePicker.getValue().isBefore(LocalDate.now())){
            AlertController.showAlert("Error", "Trip cannot be in past");

            return;
        }

        if (destination_id == 0){
                        AlertController.showAlert("Error", "Destination is missing");
                        return;
        }


        System.out.println(EmailController.email);
        
        String costText = costTextField.getText();
        double cost = Double.parseDouble(costText);

        String daysText = daysTextField.getText();
        int days = (int) Double.parseDouble(daysText);


        //System call
        OdysseyHub.getSystem().finaliseTrip(datePicker.getValue(), days, cost);


        AlertController.showConfirmation("SuccessFul", "YourTrip has been added SuccessFully");

        NBC.Navigate(event, NavigationLink.agencyDashboard);


    }


    //------------------------------------------------------------------------------------------------------

    
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
    // Other methods...
}
