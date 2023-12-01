package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ReviewController {

    @FXML
    private TextArea reviewTextArea;

    @FXML
    private Button submitReviewButton;

    @FXML
    private void initialize() {
    
    }

    @FXML
    private void handleSubmitReview() {
        String reviewText = reviewTextArea.getText();
        
        // Add your logic here to handle the submitted review
        System.out.println("Review submitted: " + reviewText);

        // You can clear the TextArea or perform other actions after submitting the review
        reviewTextArea.clear();
    }

    // Add any other methods or event handlers as needed

}
