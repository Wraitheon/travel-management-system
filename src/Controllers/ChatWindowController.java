package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.List;

import Models.ChatCellFactory;
import Models.ChatMessage;
import Models.dbhandler;

public class ChatWindowController {

    @FXML
    private ListView<ChatMessage> chatListView;

    @FXML
    private TextArea messageTextArea;

    @FXML
    private Button sendButton;

    @FXML
    private ComboBox<Integer> tripComboBox;

 

    @FXML
    private void initialize() {
        chatListView.setCellFactory(param -> new ChatCellFactory());

        // Populate the ComboBox with trip IDs
        populateTripComboBox();

        // Set listener for ComboBox selection change
        tripComboBox.setOnAction(e -> handleComboBoxSelection());
    }

    @FXML
    private void sendMessage() {
        String messageText = messageTextArea.getText().trim();
        Integer selectedTripID = tripComboBox.getValue();
        String senderEmail = EmailController.email;

        if (selectedTripID != null && !messageText.isEmpty()) {
            // Insert the message into the database
            dbhandler.insertChatMessage(selectedTripID, senderEmail, messageText);

            // Update the UI by querying the database for all messages for the selected trip
            updateChatListView(selectedTripID);

            // Clear the message text area
            messageTextArea.clear();
        }
    }

    private void populateTripComboBox() {
        TouristDashBoardController.traveller.fetchBooking();
        List<Integer> tripIDs = TouristDashBoardController.traveller.getTripIDs();
        tripComboBox.getItems().addAll(tripIDs);
    }

    private void handleComboBoxSelection() {
        Integer selectedTripID = tripComboBox.getValue();

        if (selectedTripID != null) {
            // Update the UI by querying the database for all messages for the selected trip
            updateChatListView(selectedTripID);
        } else {
            // Handle the case when the selectedTripID is null
            chatListView.getItems().clear();
        }
    }

    private void updateChatListView(int tripID) {
        // Query the database to get all messages for the selected trip
        List<ChatMessage> messages = dbhandler.getChatMessagesForTrip(tripID);

        // Update the chatListView
        chatListView.getItems().setAll(messages);
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
        NBC.Navigate(event, NavigationLink.localRecommendation);    
    }

     @FXML
    private void handleChat(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.chat);    
    }

     @FXML
    private void handleGuideBooking(ActionEvent event) {
        NBC.Navigate(event, NavigationLink.guideBookings);    
    }
}