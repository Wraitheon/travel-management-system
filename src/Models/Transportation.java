package Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transportation extends ItineraryItem{
   
    private int id;
    private String mode; 
  
    public Transportation(int id, String mode, LocalDateTime  dateTime) {
        super(dateTime);
        this.mode = mode;
        this.id = id;
    }
    public String getMode() {
        return mode;
    }
     public int getId() {
        return id;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }
    public int getCost(int dest_id){
        
        return (int) dbhandler.getTransportationCost(dest_id, id);
    }
 
    @Override
    public String toString() {
         if (getDateTime() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");

            // Format the LocalDateTime using the formatter
            String formattedDateTime = getDateTime().format(formatter);

            return "Travel by " + mode + ", at " +  formattedDateTime;
        } 
        return "Travel by " + mode;

    }

    public void addToDb(int itineraryID){
    

        dbhandler.insertItineraryTransportation(id, itineraryID, getDateTime());
    }

    // create getter setter
    
}
