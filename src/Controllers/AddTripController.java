package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddTripController {

        private String userEmail;


    // @FXML
    // private Label titleLabel;

    public void initData(String email) {
        userEmail = email;
    }


    // @FXML
    // private ComboBox<String> destinationComboBox;

    // @FXML
    // private DatePicker tripDatePicker;

    // @FXML
    // private TextField numDaysTextField;

    // @FXML
    // private TextField priceTextField;

    // @FXML
    // private ListView<String> itineraryListView;

    // // ObservableList to store itinerary items
    // private ObservableList<String> itineraryItems = FXCollections.observableArrayList();

    // @FXML
    // private void initialize() {
    //     // Bind the itineraryItems to the ListView
    //     itineraryListView.setItems(itineraryItems);
    // }

    // @FXML
    // private void handleDigitInput(javafx.scene.input.KeyEvent event) {
    //     // Allow only digits in the numDaysTextField
    //     if (!event.getCharacter().matches("[0-9]")) {
    //         event.consume();
    //     }
    // }

    // @FXML
    // private void handleAddItineraryItem() {
    //     // Show an alert dialog to get the itinerary item from the user
    //     TextInputDialog dialog = new TextInputDialog();
    //     dialog.setTitle("Add Itinerary Item");
    //     dialog.setHeaderText(null);
    //     dialog.setContentText("Enter Itinerary Item:");

    //     // Get the result of the dialog
    //     dialog.showAndWait().ifPresent(item -> {
    //         // Add the itinerary item to the ObservableList
    //         itineraryItems.add(item);
    //     });
    // }

    // Other methods...
}
