package Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Activity extends ItineraryItem {
    private String ActivityName;
    private int id;
    

    private String activityDescription;
    private double cost;

    public Activity(int id,String activityName, String activityDescription, double cost, LocalDateTime  dateTime) {
        super(dateTime);
        ActivityName = activityName;
        if (id == -1){
            this.id = dbhandler.countActivities()+1;
        } else {
            this.id = id;
        }
        this.activityDescription = activityDescription;
        this.cost = cost;
    }
    public int getId() {
        return id;
    }
    public String getActivityName() {
        return ActivityName;
    }

    public void setActivityName(String activityName) {
        ActivityName = activityName;
    }

   

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
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

            return ActivityName + ", " + activityDescription + " at " +  formattedDateTime;
        } else {
            return ActivityName + ", " + activityDescription;
        }
        
        
    }
    public void finalizeItem(int itineraryID){
       

        dbhandler.addActivity(itineraryID, ActivityName, getDateTime(), activityDescription, cost);
        }
    

    //generate all getter setter
}
