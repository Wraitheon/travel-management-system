package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import Models.ChatCellFactory;
import Models.ChatMessage;

public class ChatWindowController {

    @FXML
    private ListView<ChatMessage> chatListView;

    @FXML
    private TextArea messageTextArea;

    @FXML
    private Button sendButton;

    @FXML
    private void initialize() {
        // Initialize any necessary components or logic
        chatListView.setCellFactory(param -> new ChatCellFactory());
    }

    @FXML
    private void sendMessage() {
        String messageText = messageTextArea.getText().trim();
        if (!messageText.isEmpty()) {
            ChatMessage newMessage = new ChatMessage(messageText);
            chatListView.getItems().add(newMessage);
            messageTextArea.clear();
        }
    }
}
