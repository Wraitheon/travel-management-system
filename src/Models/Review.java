package Models;

import java.time.LocalDate;

public class Review {
    private String traveller_email;
    private int rating;
    private String comment;
    private LocalDate date;
    public Review(String traveller_email, int rating, String comment, LocalDate date) {
        this.traveller_email = traveller_email;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }
    public String getTraveller_email() {
        return traveller_email;
    }
    public void setTraveller_email(String traveller_email) {
        this.traveller_email = traveller_email;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    // getter setter 
}
