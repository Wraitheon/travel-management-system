package Models;

import java.time.LocalDate;

import Controllers.EmailController;

public class Trip {
    private int trip_ID;
    private double price;
    private LocalDate trip_Date;
    private int noOfDays;
    private Itinerary itinerary;
    
    public Itinerary getItinerary() {
        return itinerary;
    }


    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }


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


    public Trip(double price, LocalDate trip_Date, int noOfDays, Itinerary itinerary) {
        this.price = price;
        this.trip_Date = trip_Date;
        this.noOfDays = noOfDays;
        this.itinerary = itinerary;
    }


    public int getTrip_ID() {
        return trip_ID;
    }


    public void insertModelToDb(int destination_id){

        dbhandler db = new dbhandler();

        int trip_id = db.addTrip(EmailController.email, destination_id, trip_Date, price, noOfDays);

        itinerary.addToDB(trip_id);
    }

    
    //getter setters
}
