package Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

         if (getDateTime() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");

            // Format the LocalDateTime using the formatter
            String formattedDateTime = getDateTime().format(formatter);

            return name + ", " + location + " on " +  formattedDateTime;
        } else {
            return name + ", " + location;
        }
    }
    public void addToDb(int itineraryID){
        dbhandler db = new dbhandler();

        db.insertItineraryAccommodation(itineraryID, id, getDateTime());
    }
    
   


    //create getter setter for this class
    
}
