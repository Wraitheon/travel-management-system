package Models;

import java.time.LocalDate;

public class Activity {
    private String ActivityName;
    private LocalDate acitivityDate;
    private String activityDescription;
    private double cost;

    public Activity(String activityName, LocalDate acitivityDate, String activityDescription, double cost) {
        ActivityName = activityName;
        this.acitivityDate = acitivityDate;
        this.activityDescription = activityDescription;
        this.cost = cost;
    }

    public String getActivityName() {
        return ActivityName;
    }

    public void setActivityName(String activityName) {
        ActivityName = activityName;
    }

    public LocalDate getAcitivityDate() {
        return acitivityDate;
    }

    public void setAcitivityDate(LocalDate acitivityDate) {
        this.acitivityDate = acitivityDate;
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

    

    //generate all getter setter
}
