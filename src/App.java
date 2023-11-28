import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Load the FXML file
            Parent root = FXMLLoader.load(getClass().getResource("loginpage.fxml"));
    
            // Create the scene
            Scene scene = new Scene(root, 600, 400);
    
            // Set the scene to the stage
            primaryStage.setTitle("Travel/Tourism Management System Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Insert dummy user data
    private static void insertDummyUser() {
        // Replace the JDBC_URL, JDBC_USER, and JDBC_PASSWORD with your actual database connection details
        dbhandler.insertUser("dummy@example.com", "Dummy User", 30, "1992-05-20", "Admin", "987654321012345", "987-654-3210");
        System.out.println("Dummy user inserted!");
    }
}
