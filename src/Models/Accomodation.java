package Models;

import java.time.LocalDate;

public class Accomodation {
    private String location;
    private String name;
    private LocalDate check_in;
    private LocalDate check_out;
    private double cost;

      public Accomodation(String location, String name, LocalDate check_in, LocalDate check_out, double cost) {
        this.location = location;
        this.name = name;
        this.check_in = check_in;
        this.check_out = check_out;
        this.cost = cost;
    }

    public Accomodation(String location, String name, double cost) {
        this.location = location;
        this.name = name;
        this.cost = cost;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getCheck_in() {
        return check_in;
    }
    public void setCheck_in(LocalDate check_in) {
        this.check_in = check_in;
    }
    public LocalDate getCheck_out() {
        return check_out;
    }
    public void setCheck_out(LocalDate check_out) {
        this.check_out = check_out;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Accomodation [location=" + location + ", name=" + name + ", check_in=" + check_in + ", check_out="
                + check_out + ", cost=" + cost + "]";
    }

    
   


    //create getter setter for this class
    
}
