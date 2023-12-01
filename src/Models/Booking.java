package Models;

import java.sql.Date;
import java.time.LocalDate;

public class Booking {
    private String email;
    private LocalDate date;
    private Payment payment;
    private int trip_ID;

    public Booking(int bookingId, int tripId, String userEmail, Date bookingDate) {
        this.email = userEmail;
        this.date = bookingDate.toLocalDate(); // Convert Date to LocalDate
        this.payment = null; // Set the default value for payment
        this.trip_ID = tripId;
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
