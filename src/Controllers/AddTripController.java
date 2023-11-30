package Controllers;


import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;



import Models.dbhandler;
import Models.Destination;
import Models.Restaurants;
import Models.Accomodation;
import Models.Transportation;
import Models.Activity;
import Models.Itinerary;
import Models.ItineraryItem;







import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddTripController {

    private String userEmail;
    private Stage stage;

    // @FXML
    // private Label titleLabel;

    public void initData(String email) {
        userEmail = email;
    }

    
    
    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<Destination> destinationComboBox;

    @FXML
    private ComboBox<Restaurants> resComboBox;

    @FXML
    private ComboBox<Accomodation> accommoComboBox;

    @FXML
    private ComboBox<Activity> actComboBox;

    @FXML
    private ListView<ItineraryItem> itineraryListView;

    // You might want to initialize the ListView with an ObservableList
    private ObservableList<ItineraryItem> itineraryItems = FXCollections.observableArrayList();


    private  List<Destination> destinations;
    private int destination_id;

    private List<Restaurants> restaurants;
    private List<Accomodation> accomodations;
    private List<Activity> activities;
    private List<Transportation> transportations;

    private List<ItineraryItem> items;
    dbhandler db = new dbhandler();

   
    public void initialize() {
    
        
        destinations = db.getDestinations();
        destinationComboBox.setItems(FXCollections.observableArrayList(destinations));
    }

    @FXML
    private void handleDestinationChange(ActionEvent event){


        destination_id = destinationComboBox.getValue().getDestination_ID();
        restaurants = db.getRestaurantsForDestination(destination_id);
        resComboBox.setItems(FXCollections.observableArrayList(restaurants));

        // accomodations = db.getAccommodationsForDestination(destination_id);
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

                if (resdatePicker.getValue() == null || resdatePicker.getValue().isBefore(datePicker.getValue())) {
                    showAlert("Date not Selected", "Kindly add relevant date");
                    return;
                }

                if (resComboBox.getValue() != null){
                                    System.out.println(resComboBox.getValue());

                                    ItineraryItem newItem = resComboBox.getValue();
                                    LocalDate selectedDate = resdatePicker.getValue();
                                    LocalDateTime selectedDateTime = LocalDateTime.of(selectedDate, LocalTime.of(hourSpinner.getValue(), minuteSpinner.getValue())
);

                                    newItem.setDateTime(selectedDateTime);
                                    System.out.println(newItem.getDateTime());

                    itineraryItems.add(newItem);
                } else {



                }

            refreshListView();
        // Handle "Your Trips" button action
    }

    //--------------------------------------------------------------------------------------------------

    private void refreshListView() {
        // Set the items of the ListView again to trigger an update
                // System.out.println("sjhdvb");

        itineraryListView.setItems(FXCollections.observableArrayList(itineraryItems));
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    //------------------------------------------------------------------------------------------------------

    @FXML
    private void handleYourTrips(ActionEvent event) {
        // Handle "Your Trips" button action
    }
    @FXML
    private void handleHome(ActionEvent event) {
        // Handle "Add Trips" button action
        FXMLLoader loader = new FXMLLoader(getClass().getResource(NavigationLink.agencyDashboard));
        try {
            Parent addTripParent = loader.load();
           
            AgencyDashBoardController agencyDashBoardController = loader.getController();

            agencyDashBoardController.initData(userEmail);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(addTripParent, 600, 400);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }
    @FXML
    private void handleAddTrips(ActionEvent event) {
        // Handle "Add Trips" button action
        FXMLLoader loader = new FXMLLoader(getClass().getResource(NavigationLink.addTrip));
        try {
            Parent addTripParent = loader.load();
           
            AddTripController addTripController = loader.getController();

            addTripController.initData(userEmail);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(addTripParent, 600, 400);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }

    @FXML
    private void handleBookings(ActionEvent event) {
        // Handle "Bookings" button action
    }

    @FXML
    private void handleAbout(ActionEvent event) {
        // Handle "About" button action
    }

    // Other methods...
}
