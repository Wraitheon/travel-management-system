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
            Parent root = FXMLLoader.load(getClass().getResource("Screens/signuppage.fxml"));
    
            // Create the scene
            Scene scene = new Scene(root, 600, 400);
            scene.getStylesheets().add(getClass().getResource("Screens/styles.css").toExternalForm());
    
            // Set the scene to the stage
            primaryStage.setTitle("OdysseyHub");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

   
}
