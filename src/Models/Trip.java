package Models;

import java.time.LocalDate;
import java.util.List;

import Controllers.EmailController;

public class Trip {
    private int trip_ID;
    private int destination_id;
   

    private double price;
    private LocalDate trip_Date;
    private int noOfDays;
    private Itinerary itinerary;
    
    public Itinerary getItinerary() {
        return itinerary;
    }
     public int getDestination_id() {
        return destination_id;
    }


    public void setDestination_id(int destination_id) {
        this.destination_id = destination_id;
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


    public Trip(int trip_ID, int destination_id, double price, LocalDate trip_Date, int noOfDays) {
        this.trip_ID = trip_ID;
        this.destination_id = destination_id;
        this.price = price;
        this.trip_Date = trip_Date;
        this.noOfDays = noOfDays;
    }

    public void fetchItinerary(){
        dbhandler db = new dbhandler();

        itinerary = new Itinerary(db.getItineraryIdForTrip(trip_ID));
        fetchActivities(trip_ID);
        itinerary.fecthItineraryItems();

    }

    private void fetchActivities(int trip_ID){
        dbhandler db = new dbhandler();

        List<Activity> list = db.getActivitiesForTrip(trip_ID);

        for(var item : list){
                    itinerary.addActivity(item);
        }

    }

    
    //getter setters
}
