package Models;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Payment {
    private int paymentId;
    private int bookingId;
    private LocalDate paymentDate;
    private double amount;
    private String paymentMethod;

    public Payment (int paymentId, int bookingId, LocalDate paymentDate, double amount, String paymentMethod) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }    

    public Payment(int paymentId, int bookingId, Date paymentDate, BigDecimal amount, String paymentMethod) {
    }
    public Payment(LocalDate paymentDate2, double paymentAmount, String paymentMethod2) {
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public int getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
