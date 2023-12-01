package Models;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Payment {
    private double ammount;
    private LocalDate date;
    private String method;
    public Payment(double ammount, LocalDate date, String method) {
        this.ammount = ammount;
        this.date = date;
        this.method = method;
    }
    public Payment(int paymentId, int bookingId, Date paymentDate, BigDecimal amount, String paymentMethod) {
    }
    public double getAmmount() {
        return ammount;
    }
    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    
    //getter setters
}
