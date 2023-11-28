import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Insert Dummy User");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // Call the insertDummyUser method when the button is pressed
                insertDummyUser();
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Insert Dummy User");
        primaryStage.setScene(scene);
        primaryStage.show();
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
