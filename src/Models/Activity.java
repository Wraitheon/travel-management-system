package Models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Activity extends ItineraryItem {
    private String ActivityName;
    private int id;
    

    private String activityDescription;
    private double cost;

    public Activity(int id,String activityName, String activityDescription, double cost, LocalDateTime  dateTime) {
        super(dateTime);
        ActivityName = activityName;
        this.id = id;
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
        return "Activity [dateTime= " + getDateTime() + "ActivityName=" + ActivityName + ", activityDescription="
                + activityDescription + ", cost=" + cost + "]";
    }

    

    //generate all getter setter
}
