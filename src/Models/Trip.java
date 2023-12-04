package Models;

import java.time.LocalDate;
import java.util.List;

import Controllers.EmailController;
import Controllers.Factory;

public class Trip {
    private int trip_ID;
    private int destination_id;
   

    private double price;
    private LocalDate trip_Date;
    private int noOfDays;
    private Itinerary itinerary;
    private List<ChatMessage> chatMessages;
    
    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }
    public void setChatMessages(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }
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

    public  String getDestination(){

        return dbhandler.getDestinationNameForTrip(trip_ID);
    }


    public void insertModelToDb(int destination_id){

        
        int trip_id = dbhandler.addTrip(EmailController.email, destination_id, trip_Date, price, noOfDays);

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

        itinerary = Factory.creatItinerary(dbhandler.getItineraryIdForTrip(trip_ID));
        fetchActivities(trip_ID);
        itinerary.fecthItineraryItems();

    }

    private void fetchActivities(int trip_ID){

        List<Activity> list = dbhandler.getActivitiesForTrip(trip_ID);

        for(var item : list){
                    itinerary.addActivity(item);
        }

    }
    private TravelAgency getAgency(){
        List<TravelAgency> agencies = dbhandler.getTravelAgencies();

        
        for(var agency : agencies){
            for(var trip : agency.getTrips()){
                if(trip.getTrip_ID() == trip_ID){
                    return agency;
                }
            }
        } 
        return null;
    }

    public double getAgencyRating(){
        
        return getAgency().avgRating();

    }

    
    //getter setters
}
