package Models;

import java.time.LocalDate;

public class Booking {
    private String email;
    private LocalDate date;
    private Payment payment;
    private int trip_ID;

    public Booking(String email, LocalDate date, Payment payment, int trip_ID) {
        this.email = email;
        this.date = date;
        this.payment = payment;
        this.trip_ID = trip_ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public int getTrip_ID() {
        return trip_ID;
    }

    public void setTrip_ID(int trip_ID) {
        this.trip_ID = trip_ID;
    }

    
}
