package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;
import javafx.util.StringConverter;

import Models.UserService;
import Models.dbhandler;

public class SignupController {

    @FXML
    private VBox signupBox;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private DatePicker dobDatePicker;

    @FXML
    private TextField userTypeField;

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

    @FXML
    private void initialize() {
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
    }

    @FXML
    private void handleSignupButtonAction(ActionEvent event) {
        String email = emailField.getText();
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String dateOfBirth = dobDatePicker.getValue().toString();
        String userType = userTypeField.getText();
        String cnic = cnicField.getText();
        String phoneNumber = phoneField.getText();
        String password = passwordField.getText();

        dbhandler dbH = new dbhandler();

        dbH.insertUser(email, name, age, dateOfBirth, userType, cnic, phoneNumber, password);
    }

    @FXML
    private void handleLoginLinkAction() {
        // Implement the logic to navigate to the login page
    }
}
