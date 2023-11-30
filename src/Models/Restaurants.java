package Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Restaurants extends ItineraryItem {
    private int restaurant_id;
   
    private String name;
    private double cost;
    
    
    public Restaurants(int restaurant_id, LocalDateTime  dateTime, String name, double cost) {
        super(dateTime);
        this.restaurant_id = restaurant_id;
        this.name = name;
        this.cost = cost;
    }
     public int getRestaurant_id() {
        return restaurant_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    @Override
    public String toString() {
        
        if (getDateTime() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");

            // Format the LocalDateTime using the formatter
            String formattedDateTime = getDateTime().format(formatter);

            return name + " at " +  formattedDateTime;
        } else {
            return name;
        }
    }
    

    //getter setters

}
