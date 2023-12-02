package Models;

import java.time.LocalDate;
import java.util.List;

public class TourGuide extends User{
    List<GuideBooking> bookings;

   

    public TourGuide(String email, String name, int age, String dateOfBirth, String userType, String cnic,
        String phoneNumber, String password) {
    super(email, name, age, dateOfBirth, userType, cnic, phoneNumber, password);
    
    }

    public void fetchBookings(){
        bookings = dbhandler.fetchGuideBookings(getEmail());
    }

     public List<GuideBooking> getBookings() {
        return bookings;
    }

    public void setBookings(List<GuideBooking> bookings) {
        this.bookings = bookings;
    }

    public Boolean checkAvailability(LocalDate date, int days){
        fetchBookings();
        for(var booking : bookings){
            if(!booking.checkAvailability(date, days)){
                return false;
            }
        }
        return true;
    }

    // @Override
    // public String toString() {
    //     return Name;
    // }

    



    
}