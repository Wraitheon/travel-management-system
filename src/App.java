import Controllers.AgencyDashBoardController;
import Controllers.NavigationLink;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource(NavigationLink.agencyDashboard));
    
            // Create the scene
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            AgencyDashBoardController agencyController = loader.getController();

            agencyController.initData("meow@nu.edu.pk");
            // Set the scene to the stage
            primaryStage.setTitle("Travel/Tourism Management System");
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
