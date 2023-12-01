
package Models;

import java.time.LocalDate;

import javafx.beans.property.*;

public class AllTripViewTable {
    private final IntegerProperty trip_ID;
    private final StringProperty travelAgencyName; // Changed from "destination" to "travelAgencyName"
    private final DoubleProperty price;
    private final ObjectProperty<LocalDate> trip_Date;
    private final IntegerProperty noOfDays;

    public AllTripViewTable(int trip_ID, String travelAgencyName, double price, LocalDate trip_Date, int noOfDays) {
        this.trip_ID = new SimpleIntegerProperty(trip_ID);
        this.travelAgencyName = new SimpleStringProperty(travelAgencyName); // Updated property
        this.price = new SimpleDoubleProperty(price);
        this.trip_Date = new SimpleObjectProperty<>(trip_Date);
        this.noOfDays = new SimpleIntegerProperty(noOfDays);
    }

    public int getTrip_ID() {
        return trip_ID.get();
    }

    public IntegerProperty trip_IDProperty() {
        return trip_ID;
    }

    public void setTrip_ID(int trip_ID) {
        this.trip_ID.set(trip_ID);
    }


    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public LocalDate getTrip_Date() {
        return trip_Date.get();
    }

    public ObjectProperty<LocalDate> trip_DateProperty() {
        return trip_Date;
    }

    public void setTrip_Date(LocalDate trip_Date) {
        this.trip_Date.set(trip_Date);
    }

    public int getNoOfDays() {
        return noOfDays.get();
    }

    public IntegerProperty noOfDaysProperty() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays.set(noOfDays);
    }
    
    public String getTravelAgencyName() {
        return travelAgencyName.get();
    }

    public StringProperty travelAgencyNameProperty() {
        return travelAgencyName;
    }

    public void setTravelAgencyName(String travelAgencyName) {
        this.travelAgencyName.set(travelAgencyName);
    }
}
