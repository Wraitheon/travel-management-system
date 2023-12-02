package Models;

import java.sql.Date;
import java.time.LocalDate;

public class Booking {
    private Payment p_payment;
    private Destination d_destination;
    private User u_user;

    private int booking_ID;
    private int trip_ID;
    private String email;
    private LocalDate date;

    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Booking(int booking_ID, int trip_ID, String email, LocalDate date, Payment p_payment,
            Destination d_destination, User u_user) {
        this.booking_ID = booking_ID;
        this.trip_ID = trip_ID;
        this.email = email;
        this.date = date;
        this.p_payment = p_payment;
        this.d_destination = d_destination;
        this.u_user = u_user;
    }

    public Booking() {
    }

    public Payment getP_payment() {
        return p_payment;
    }

    public void setP_payment(Payment p_payment) {
        this.p_payment = p_payment;
    }

    public Destination getD_destination() {
        return d_destination;
    }

    public void setD_destination(Destination d_destination) {
        this.d_destination = d_destination;
    }

    public User getU_user() {
        return u_user;
    }

    public void setU_user(User u_user) {
        this.u_user = u_user;
    }

    public int getBooking_ID() {
        return booking_ID;
    }

    public void setBooking_ID(int booking_ID) {
        this.booking_ID = booking_ID;
    }

    public int getTrip_ID() {
        return trip_ID;
    }

    public void setTrip_ID(int trip_ID) {
        this.trip_ID = trip_ID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTrip_ID(Trip trip) {
    }
}
