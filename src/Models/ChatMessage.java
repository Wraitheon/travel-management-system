package Models;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Override
    public String toString() {
        return "ChatMessage [senderEmail=" + senderEmail + ", message=" + message + ", time=" + time + "]";
    }

    // @Override
    // public String toString() {
    //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    //     LocalDateTime localDateTime = time.toLocalDateTime(); // Convert to LocalDateTime
    //     String formattedTimestamp = localDateTime.format(formatter);

        
    //     return senderEmail + " -> " + message + " (" + formattedTimestamp + ")";
    // }

}
