package Models;

import java.time.LocalDate;

public class GuideBooking {
    String bookedby_email;
    LocalDate date;
    int noOfDays;

    public String getBookedby_email() {
        return bookedby_email;
    }

    public void setBookedby_email(String bookedby_email) {
        this.bookedby_email = bookedby_email;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public GuideBooking(String bookedby_email, LocalDate date, int noOfDays) {
        this.bookedby_email = bookedby_email;
        this.date = date;
        this.noOfDays = noOfDays;
    }

    public Boolean checkAvailability(LocalDate newdate, int days){

        for(int i = 0; i < noOfDays; i++){
            for(int j = 0; j < days; j++){
                if (date.plusDays(i).isEqual(newdate.plusDays(j))){
                    return false;
                }
            }
        }

        return true;
    }
    
    //getter seeters
}
