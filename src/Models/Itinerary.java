package Models;

import java.time.LocalDateTime;
import java.util.*;

import Controllers.Factory;

public class Itinerary {
    private List<ItineraryItem> itineraryItems;
    private int iti_ID;

    Itinerary(){
        itineraryItems = new ArrayList<ItineraryItem>();
        iti_ID = 0;
    }

    public Itinerary(List<ItineraryItem> itineraryItems, int iti_ID) {
        this.itineraryItems = itineraryItems;
        this.iti_ID = iti_ID;
    }
    public List<ItineraryItem> getItineraryItems() {
        sort();
        return itineraryItems;
    }
    public void setItineraryItems(List<ItineraryItem> itineraryItems) {
        this.itineraryItems = itineraryItems;
    }
    public int getIti_ID() {
        return iti_ID;
    }
    public void setIti_ID(int iti_ID) {
        this.iti_ID = iti_ID;
    }
    public void addRestaurants(Restaurants res){
        itineraryItems.add(res);
    }
    public void addAccom(Accomodation accom){
        itineraryItems.add(accom);
    }
    public void addActivity(Activity act){
        itineraryItems.add(act);
    }
    public void addTransport(Transportation trans){
        itineraryItems.add(trans);
    }
    private void sort(){
        Collections.sort(itineraryItems, Comparator.comparing(ItineraryItem::getDateTime));
    }
    public Itinerary(List<ItineraryItem> itineraryItems) {
        this.itineraryItems = itineraryItems;
    }
    

    public void finaliseItinerary(int trip_ID){
       


        int itineraryID  = dbhandler.addItinerary(trip_ID);

        
        
        for(var item : itineraryItems) {
            if (item instanceof Activity){
                item.finalizeItem(trip_ID);

            } else {
                item.finalizeItem(itineraryID);

            }
        }
        

    }

    public List<ItineraryItem> fetchItineraryItems(int destination_id){
        List<ItineraryItem> items = new ArrayList<ItineraryItem>();
        items.addAll(dbhandler.getRestaurantsForDestination(destination_id));
        items.addAll(dbhandler.getAccommodationsForDestination(destination_id));
        return items;
    }


    public void addItineraryItem(int id, LocalDateTime time, String type){

        ItineraryItem itineraryItem = Factory.createItineraryItem(id, time,type);

        itineraryItems.add(itineraryItem);
    }

    private List<Restaurants> getRestaurants(){
        List<Restaurants> res = new ArrayList<>();

        for (var itineraryItem : itineraryItems) {

            if (itineraryItem instanceof Restaurants){
                res.add((Restaurants)itineraryItem);
            }
        }


        return res;
    }

    private List<Transportation> getTransportsList(){
        List<Transportation> list = new ArrayList<>();

        for (var itineraryItem : itineraryItems) {

            if (itineraryItem instanceof Transportation){
                list.add((Transportation)itineraryItem);
            }
        }


        return list;
    }

    private List<Accomodation> getAccommodations(){
        List<Accomodation> list = new ArrayList<>();

        for (var itineraryItem : itineraryItems) {

            if (itineraryItem instanceof Accomodation){
                list.add((Accomodation)itineraryItem);
            }
        }

        return list;
    }
    private List<Activity> getActivities(){
        List<Activity> list = new ArrayList<>();

        for (var itineraryItem : itineraryItems) {

            if (itineraryItem instanceof Activity){
                list.add((Activity)itineraryItem);
            }
        }

        return list;
    }
    //getter setter
    public Itinerary(int iti_ID) {
        this.iti_ID = iti_ID;
        this.itineraryItems = new ArrayList<ItineraryItem>();
        
    }

    public void fecthItineraryItems(){

        List<Accomodation> accomodations = dbhandler.getAccommodationForItinerary(iti_ID);
        List<Restaurants> restaurants = dbhandler.getRestaurantsForItinerary(iti_ID);
        List<Transportation> transportations = dbhandler.getTransportationForItinerary(iti_ID);

        for(var item : accomodations){
            itineraryItems.add(item);
        }
        for(var item : restaurants){
            itineraryItems.add(item);
        }
        for(var item : transportations){
            itineraryItems.add(item);
        }

    }

    
  
    
   




}
