package Models;

import java.time.LocalDate;

public class Trip {
    private int trip_ID;
    private double price;
    private LocalDate trip_Date;
    private int noOfDays;
    private Itinerary itinerary;
    
    public Trip(int trip_ID, double price, LocalDate trip_Date, int noOfDays) {
        this.trip_ID = trip_ID;
        this.price = price;
        this.trip_Date = trip_Date;
        this.noOfDays = noOfDays;
    }
    

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public LocalDate getTrip_Date() {
        return trip_Date;
    }
    public void setTrip_Date(LocalDate trip_Date) {
        this.trip_Date = trip_Date;
    }
    public int getN0OfDays() {
        return noOfDays;
    }
    public void setN0OfDays(int n0OfDays) {
        noOfDays = n0OfDays;
    }
    
    //getter setters
}
