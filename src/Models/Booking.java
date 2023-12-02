package Models;

import java.time.LocalDate;

import Controllers.Factory;

public class Booking {
    private String email;
    private LocalDate date;
    private double discount;
    private Payment payment;
    private int trip_ID;

    public Booking(String email, LocalDate date, int trip_ID, double discount) {
        this.email = email;
        this.date = date;
        this.discount = discount;
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

    public void makePayment(double amount) {
        payment = Factory.createPayment(amount, LocalDate.now(), "Online");
    }

    public void addToDB(){
        int booking_id = dbhandler.insertBooking(trip_ID, email, discount, date);
        payment.addToDB(booking_id);
    }

    public int getTrip_ID() {
        return trip_ID;
    }

    public void setTrip_ID(int trip_ID) {
        this.trip_ID = trip_ID;
    }

    
}