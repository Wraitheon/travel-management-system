package Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import Controllers.Factory;

public class OdysseyHub {
    private List<Destination> destinations;
    private Trip trip;
    private static OdysseyHub system;
    private OdysseyHub(){}


    public static OdysseyHub getSystem(){
        if (system == null){
            system = new OdysseyHub();
        }
        return system;
    }

    public List<Destination> fetchDestinations() {
        if (destinations == null){
            destinations = dbhandler.getDestinations();
        }

        return destinations;
    }

     public List<ItineraryItem> fetchItineraryItems(int destination_id){
        if (trip == null){
            trip = new Trip(destination_id);
        } else {
            trip.setDestination_id(0);
            trip.setItinerary(new Itinerary());
        }

        return trip.fetchItineraryItems(destination_id);
    }

    public void add_itineraryItems(int id, LocalDateTime time, String type){
        trip.addItineraryItem(id, time, type);
    }

    public void finaliseTrip(LocalDate date, int days, double cost){
        trip.finaliseTrip(date, days, cost);
    }

    public List<ItineraryItem> getItineraryItems(){
        return trip.getItineraryItems();
    }

    public void setItineraryItems(List<ItineraryItem> items){
        trip.setItineraryItems(items);
    }

    public void addActivity(String name, String desc, double cost, LocalDateTime time){
        trip.addActivity(Factory.createActivity(name, desc, cost, time));
    }




}
