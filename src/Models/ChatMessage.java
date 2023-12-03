package Models;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;

import com.mysql.cj.util.StringUtils;

import Controllers.EmailController;

public class ChatMessage {

    private int messageID;
    private String senderEmail;
    private String message;
    private java.sql.Timestamp time;

    // Parameterized constructor
    public ChatMessage(int messageID, String senderEmail, String message, java.sql.Timestamp time) {
        this.messageID = messageID;
        this.senderEmail = senderEmail;
        this.message = message;
        this.time = time;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public java.sql.Timestamp getTimestamp() {
        return time;
    }

    // @Override
    // public String toString() {
    //     return "ChatMessage [senderEmail=" + senderEmail + ", message=" + message + ", time=" + time + "]";
    // }

    @Override
    public String toString() {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        LocalDateTime localDateTime = time.toLocalDateTime(); // Convert to LocalDateTime
        String formattedTimestamp = localDateTime.format(formatter);

        int emailWidth = 30;  // Adjust based on average character width
        int messageWidth = 200;  // Adjust based on average character width

        String result;
        if (!senderEmail.equals(EmailController.email)){
            result = String.format("%-" + emailWidth + "s -> %-" + messageWidth + "s %s",
                senderEmail, message, formattedTimestamp);
        } else {
            // Format the strings with right alignment using the maximum width

            // Format the strings with right alignment using the maximum width
           result = String.format("%" + 20 + "s  %" + 200 + "s <- %" + 30 + "s",
                    formattedTimestamp, message, senderEmail);
        }
        
        
        return result;
    }


}
