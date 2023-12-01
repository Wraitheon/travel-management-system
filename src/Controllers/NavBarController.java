package Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavBarController {
    Stage stage;

    public void Navigate(ActionEvent event, String url){
         FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        try {
            Parent addTripParent = loader.load();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(addTripParent, 1200, 800);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // public void NavigateWithData(ActionEvent event, String url, var controller, var parameter){
    //      FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
    //     try {
    //         Parent addTripParent = loader.load();

    //         controller = loader.getController();

    //         controller.initData(parameter);

    //         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    //         Scene scene = new Scene(addTripParent);
    //         stage.setScene(scene);
    //         stage.show();

    //     } catch (IOException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
    // }
}
