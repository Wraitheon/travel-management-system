package Models;

import java.util.List;

public class Itinerary {
 
    private int iti_ID;
    private List<Restaurants> restaurants;
    private List<Accomodation> accomodations;
    private List<Activity> activities;
    private List<Transportation> transportations;
    
    public Itinerary(int iti_ID, List<Restaurants> restaurants, List<Accomodation> accomodations,
            List<Activity> activities, List<Transportation> transportations) {
        this.iti_ID = iti_ID;
        this.restaurants = restaurants;
        this.accomodations = accomodations;
        this.activities = activities;
        this.transportations = transportations;
    }
    public int getIti_ID() {
        return iti_ID;
    }
    public void setIti_ID(int iti_ID) {
        this.iti_ID = iti_ID;
    }
    public List<Restaurants> getRestaurants() {
        return restaurants;
    }
    public void setRestaurants(List<Restaurants> restaurants) {
        this.restaurants = restaurants;
    }
    public List<Accomodation> getAccomodations() {
        return accomodations;
    }
    public void setAccomodations(List<Accomodation> accomodations) {
        this.accomodations = accomodations;
    }
    public List<Activity> getActivities() {
        return activities;
    }
    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
    public List<Transportation> getTransportations() {
        return transportations;
    }
    public void setTransportations(List<Transportation> transportations) {
        this.transportations = transportations;
    }
    
    //getter setters



}
