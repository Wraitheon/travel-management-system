package Models;

import java.util.*;

public class Itinerary {
    private List<ItineraryItem> itineraryItems;
    private int iti_ID;
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
    

    public void addToDB(int trip_ID){
       


        int itineraryID  = dbhandler.addItinerary(trip_ID);

        
        
        for(var item : itineraryItems) {
            if (item instanceof Activity){
                item.addToDb(trip_ID);

            } else {
                item.addToDb(itineraryID);

            }
        }
        

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
