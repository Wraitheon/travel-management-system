package Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import Models.Review;
import Models.TourGuide;
import Models.User;
import Models.dbhandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookGuideController implements Initializable {

    @FXML
    private DatePicker datePicker;

    @FXML
    private Spinner<Integer> daysSpinner;

    private List<User> guides = dbhandler.fetchTourGuides();
    private TourGuide guide;

    @FXML
    private TableView<User> tourGuideTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableColumns();

        tourGuideTable.getItems().addAll(guides);

         tourGuideTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Selected User: " + newValue.getName());
                if (newValue instanceof TourGuide){
                    guide = (TourGuide) newValue;
                }
            }
        });
    }

    private void initializeTableColumns() {

        

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<User, String> phoneNumberColumn = new TableColumn<>("Phone Number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        // TableColumn<Review, LocalDate> dateColumn = new TableColumn<>("Date");
        // tourGuideTable.setCellValueFactory(new PropertyValueFactory<>("date"));


        tourGuideTable.getColumns().addAll(nameColumn, emailColumn, phoneNumberColumn);

        tourGuideTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

       
    }

    @FXML
    private void handleBook(ActionEvent event){
        if (guide == null){
            AlertController.showAlert("Error", "Guide Not Selected");

            return;
        }
        if (datePicker.getValue() == null){
            AlertController.showAlert("Error", "Date Not Entered");
            return;
        }
        if (!guide.checkAvailability(datePicker.getValue(), daysSpinner.getValue())){
             AlertController.showAlert("Error", "Guide Not Available");
            return;
        }

        if(guide.book(EmailController.email, datePicker.getValue(), daysSpinner.getValue())){
            AlertController.showConfirmation("SuccessFul", "Guide has been Booked Successfully");
            NBC.Navigate(event, NavigationLink.touristDashboard);
        } else {
            AlertController.showAlert("Error 404", "Unexpected Error Occured");
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
         NBC.Navigate(event, NavigationLink.bookGuide);      
    }
     @FXML
    private void handleBooking(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.touristBookings);    
    }


    @FXML
    private void handleReview(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.touristReview);    
    }
     @FXML
    private void handleLocal(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.touristReview);    
    }

     @FXML
    private void handleChat(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.chat);    
    }

     @FXML
    private void handleGuideBooking(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.bookGuide);    
    }





}
