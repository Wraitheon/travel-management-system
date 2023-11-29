package Models;

import java.time.LocalDate;

public class Restaurants {
    private String name;
    private LocalDate scheduledTime;
    private double cost;
    public Restaurants(String name, LocalDate scheduledTime, double cost) {
        this.name = name;
        this.scheduledTime = scheduledTime;
        this.cost = cost;
    }
    public Restaurants(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getScheduledTime() {
        return scheduledTime;
    }
    public void setScheduledTime(LocalDate scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    @Override
    public String toString() {
        return "Restaurants [name=" + name + ", scheduledTime=" + scheduledTime + ", cost=" + cost + "]";
    }

    //getter setters

}
