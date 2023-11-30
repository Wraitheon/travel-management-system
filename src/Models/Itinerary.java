package Models;

import java.util.*;
import java.util.List;

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
    
    //getter setter
  
    
   




}
