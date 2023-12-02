package Models;

import java.math.BigDecimal;
import java.util.Date;

public class BookingTable {
    private String userName;
    private String tripDestination;
    private BigDecimal tripPrice;
    private int tripNumberOfDays;
    private Date bookingDate;
    private Date paymentDate;
    private BigDecimal amount;
    private String paymentMethod;
    private BigDecimal discount;
    private BigDecimal remainingPrice;

    // Assuming a constructor to initialize the object
    public BookingTable(String userName, String tripDestination, BigDecimal tripPrice, int tripNumberOfDays,
                         Date bookingDate, Date paymentDate, BigDecimal amount, String paymentMethod, BigDecimal discount,
                         BigDecimal remainingPrice) {
        this.userName = userName;
        this.tripDestination = tripDestination;
        this.tripPrice = tripPrice;
        this.tripNumberOfDays = tripNumberOfDays;
        this.bookingDate = bookingDate;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.discount = discount;
        this.remainingPrice = remainingPrice;
    }

    // Getters and setters (you can generate these in your IDE or write them manually)

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTripDestination() {
        return tripDestination;
    }

    public void setTripDestination(String tripDestination) {
        this.tripDestination = tripDestination;
    }

    public BigDecimal getTripPrice() {
        return tripPrice;
    }

    public void setTripPrice(BigDecimal tripPrice) {
        this.tripPrice = tripPrice;
    }

    public int getTripNumberOfDays() {
        return tripNumberOfDays;
    }

    public void setTripNumberOfDays(int tripNumberOfDays) {
        this.tripNumberOfDays = tripNumberOfDays;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal getRemainingPrice() {
        return remainingPrice;
    }

    public void setRemainingPrice(BigDecimal remainingPrice) {
        this.remainingPrice = remainingPrice;
    }
    // Other methods as needed
}
