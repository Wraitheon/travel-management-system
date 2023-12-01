package Controllers;

import java.io.IOException;

import Models.dbhandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class LoginController {
    private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink signupLink;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        String enteredEmail = email.getText();
        String enteredPassword = password.getText();
                                        System.out.println(enteredEmail);


        // // Replace the following line with your database query logic
        boolean isValidCredentials = checkCredentialsFromDatabase(enteredEmail, enteredPassword);
        String navLink = getDashBoardLink(enteredEmail);
                                System.out.println(navLink);
        if (isValidCredentials && !"".equals(navLink)) {
            // Navigate to another page

            EmailController.email = enteredEmail;
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(navLink));
            Parent anotherPage = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(anotherPage, 600, 400);
            stage.setScene(scene);
            stage.show();
        } else {
            // Show an alert for incorrect login credentials
            showAlert("Login Failed", "Incorrect email or password. Please try again.");
        }
    }
    private String getDashBoardLink(String email){
        dbhandler db = new dbhandler();
         String userType = db.getUserType(email);
                                         System.out.println(userType);

        if ("Travel Agency".equals(userType)) {
            return NavigationLink.agencyDashboard;
        } else if ("Tour Guide".equals(userType)) {
            return NavigationLink.agencyDashboard;
        } else if ("Tourist".equals(userType)) {
            return NavigationLink.touristDashboard;
        }
        
        return "";

    }
    private boolean checkCredentialsFromDatabase(String email, String password) {
        // Replace this with your actual database query logic
        // Return true if there is a match, false otherwise
        dbhandler db = new dbhandler();

        return db.authenticateUser(email, password);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void handleSignupLinkAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Screens/signuppage.fxml"));	
		root = loader.load();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root, 600, 400);
		stage.setScene(scene);
		stage.show();
    }
}
