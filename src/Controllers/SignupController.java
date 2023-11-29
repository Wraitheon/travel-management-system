package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Date;

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
    private TextField dobField;

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

    private final UserService userService = new UserService();

    @FXML
    private void initialize() {
        // Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup.fxml"));

        // Set the controller for the FXML file
        fxmlLoader.setController(this);

        try {
            // Load the FXML file
            signupBox = fxmlLoader.load();

            // Set the CSS file for styling
            signupBox.getStylesheets().add(getClass().getResource("src\\Screens\\styles.css").toExternalForm());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSignupButtonAction( ActionEvent event ) {
        String email = emailField.getText();
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String dateOfBirth = dobField.getText();
        String userType = userTypeField.getText();
        String cnic = cnicField.getText();
        String phoneNumber = phoneField.getText();
        String password = passwordField.getText();

        //User newUser = new User(email, name, age, dateOfBirth, userType, cnic, phoneNumber, password);

        dbhandler dbH = new dbhandler();

        dbH.insertUser(email, name, age, dateOfBirth, userType, cnic, phoneNumber, password);
    }

    @FXML
    private void handleLoginLinkAction() {
        // Implement the logic to navigate to the login page
    }
}
