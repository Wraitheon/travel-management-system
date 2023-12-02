package Controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

import Models.dbhandler;
import Models.TravelAgency;

public class ReviewController {

    @FXML
    private TextArea reviewTextArea;

    @FXML
    private Button submitReviewButton;

    @FXML
    private ComboBox<String> travelAgencyComboBox;

    @FXML
    private Slider ratingSlider;

    
    private String currentUserEmail = EmailController.email;
    NavBarController NBC = new NavBarController();

    List<TravelAgency> travelAgencies = dbhandler.getTravelAgencies();

    @FXML
    private void initialize() {
        populateTravelAgenciesComboBox();
    }

    @FXML
    private void handleSubmitReview() {
        String reviewText = reviewTextArea.getText();
        int rating = (int) ratingSlider.getValue();
        String selectedAgency = travelAgencyComboBox.getValue();

        // get the email from the travelAgencies list against the selected agency
        for (TravelAgency agency : travelAgencies) {
            if (agency.getName().equals(selectedAgency)) {
                selectedAgency = agency.getEmail();
                break;
            }
        }

        if (selectedAgency == null || selectedAgency.isEmpty()) {
            System.out.println("Please select a travel agency.");
            return;
        }

        // Get the current date and time
        LocalDate reviewDate = LocalDate.now();

        // Add your logic here to handle the submitted review
        dbhandler.insertReview(currentUserEmail, selectedAgency, rating, reviewText, reviewDate);

        // Clear and reset UI components
        reviewTextArea.clear();
        travelAgencyComboBox.getSelectionModel().clearSelection();
        travelAgencyComboBox.setPromptText("Select Travel Agency");
        ratingSlider.setValue(5.0); // Reset the slider value to the middle or any default value
    }

    private void populateTravelAgenciesComboBox() {

        for (TravelAgency agency : travelAgencies) {
            travelAgencyComboBox.getItems().add(agency.getName());
        }
    }

    @FXML
    private void handleViewTrips(ActionEvent event) {
        // NBC.Navigate(event, NavigationLink.touristViewTrips);    
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
