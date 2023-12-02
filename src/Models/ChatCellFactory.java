package Models;

import javafx.scene.control.ListCell;
import javafx.scene.text.Text;

public class ChatCellFactory extends ListCell<ChatMessage> {
    
    @Override
    protected void updateItem(ChatMessage item, boolean empty) {
        super.updateItem(item, empty);
        
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
            getStyleClass().remove("list-cell");
        } else {
            setText(item.getMessage());
            getStyleClass().add("list-cell");
        }
    }
}
