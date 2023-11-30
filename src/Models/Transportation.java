package Models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transportation extends ItineraryItem{
   
    private int id;
    private String mode; 
    private double cost;
  
    public Transportation(int id, String mode, double cost, LocalDateTime  dateTime) {
        super(dateTime);
        this.mode = mode;
        this.cost = cost;
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
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    @Override
    public String toString() {
        return "Transportation [dateTime=" + getDateTime() + ", mode=" + mode + ", cost=" + cost + "]";
    }

    // create getter setter
    
}
