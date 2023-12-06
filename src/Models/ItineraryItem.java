package Models;

import java.time.LocalDateTime;

public class ItineraryItem {
    private LocalDateTime  dateTime;

    public LocalDateTime  getDateTime() {
        return dateTime;
    }

    

    public void setDateTime(LocalDateTime  dateTime) {
        this.dateTime = dateTime;
    }

    public ItineraryItem(LocalDateTime  dateTime) {
        this.dateTime = dateTime;
    }
    //getter setter

    public void finalizeItem(int itineraryID){
    
    }

    @Override
    public String toString() {
        return "ItineraryItem [dateTime=" + dateTime + "]";
    }
}
