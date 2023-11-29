package Models;

import java.time.LocalDate;

public class Transportation {
    private String mode;
    private LocalDate departureDate;
    private LocalDate arivalDate; 
    private double cost;
    public Transportation(String mode, LocalDate departureDate, LocalDate arivalDate, double cost) {
        this.mode = mode;
        this.departureDate = departureDate;
        this.arivalDate = arivalDate;
        this.cost = cost;
    }
    public Transportation(String mode, double cost) {
        this.mode = mode;
        this.cost = cost;
    }
    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }
    public LocalDate getDepartureDate() {
        return departureDate;
    }
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
    public LocalDate getArivalDate() {
        return arivalDate;
    }
    public void setArivalDate(LocalDate arivalDate) {
        this.arivalDate = arivalDate;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }

    // create getter setter
    
}
