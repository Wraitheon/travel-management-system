package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import javafx.util.StringConverter;

import Models.UserService;
import Models.dbhandler;

public class SignupController {

    @FXML
    private VBox signupBox;

    @FXML
    private ComboBox<String> userTypeComboBox;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private DatePicker dobDatePicker;

    @FXML
    private TextField cnicField;

    @FXML
    private TextField phoneField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signupButton;

    @FXML
    private Hyperlink loginLink;

    public void initialize() {
        dobDatePicker.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? date.toString() : "";
            }
    
            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty()) ? LocalDate.parse(string) : null;
            }
        });
    
        // Set the initial visibility of fields
        emailField.setVisible(true);
        nameField.setVisible(true);
        ageField.setVisible(true);
        dobDatePicker.setVisible(true);
        cnicField.setVisible(true);
        phoneField.setVisible(true);
        passwordField.setVisible(true);
    
        // Register an event handler to handle user type changes in real time
        userTypeComboBox.setOnAction(event -> handleUserTypeChange());
    
        // Set up a listener for changes in the dobDatePicker value
        dobDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int calculatedAge = calculateAge(newValue);
                ageField.setText(String.valueOf(calculatedAge));
            }
        });
    
        // Make the ageField uneditable
        ageField.setEditable(false);
    }

    @FXML
    private void handleSignupButtonAction(ActionEvent event) {
        // Mandatory fields
        String email = emailField.getText();
        String name = nameField.getText();
        String phoneNumber = phoneField.getText();
        String password = passwordField.getText();

        // Check if mandatory fields are empty
        if (email.isEmpty() || name.isEmpty() || phoneNumber.isEmpty() || password.isEmpty()) {
            // Show an alert for missing mandatory fields
            showAlert("Mandatory Fields", "Please fill in all mandatory fields.");
            return;
        }

        // Optional fields
        LocalDate dateOfBirth = dobDatePicker.getValue();
        Integer age = (dateOfBirth != null) ? calculateAge(dateOfBirth) : null;
        String userType = userTypeComboBox.getValue();
        String cnic = cnicField.getText();

        // Set optional fields to null if empty
        cnic = (cnic.isEmpty()) ? null : cnic;
        age = (ageField.getText().isEmpty()) ? null : age;
        dateOfBirth = (dobDatePicker.getValue() == null) ? null : dateOfBirth;

        dbhandler dbH = new dbhandler();
        dbH.insertUser(email, name, age, (dateOfBirth != null) ? dateOfBirth.toString() : null, userType, cnic, phoneNumber, password);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private int calculateAge(LocalDate birthdate) {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear() - birthdate.getYear();
    }

    @FXML
    private void handleLoginLinkAction() {
        // Implement the logic to navigate to the login page
    }

    @FXML
    private void handleUserTypeChange() {
        // Check the selected user type and adjust field visibility accordingly
        String selectedUserType = userTypeComboBox.getValue();

        boolean isTravelAgency = "Travel Agency".equals(selectedUserType);

        ageField.setVisible(!isTravelAgency);
        dobDatePicker.setVisible(!isTravelAgency);
        cnicField.setVisible(!isTravelAgency);
    }
}
