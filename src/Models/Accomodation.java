package Models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Accomodation extends ItineraryItem{
    private int id;
   

    private String location;
    private String name;

    private double cost;

 
    public Accomodation(int id, String location, String name, double cost, LocalDateTime  dateTime) {
        super(dateTime);
        this.id = id;
        this.location = location;
        this.name = name;
        this.cost = cost;
    }
     public int getId() {
        return id;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
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
        return "Accomodation [dateTime=" + getDateTime() + "location=" + location + ", name=" + name  + ", cost=" + cost + "]";
    }

    
   


    //create getter setter for this class
    
}
