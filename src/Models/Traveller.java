package Models;

import java.util.ArrayList;
import java.util.List;

import Controllers.Factory;

public class Traveller extends User{
    LoyaltyProgram loyaltyPoints;
    List<Booking> bookings;

    public int getLoyaltyPoints() {
        return loyaltyPoints.getPoints();
    }

   public void fetchLoyaltyProgram() {
       
        
        loyaltyPoints = Factory.createLoyaltyProgram(dbhandler.getPoints(getEmail()));
   }

   public void redeemPoints(int points){
        loyaltyPoints.redeemPoints(points, getEmail());
   }

   public void addPoints(int points){
     System.out.println(points);
        loyaltyPoints.addPoints(points, getEmail());
   }

   public Boolean discountEligbility(){
        return loyaltyPoints.eligibleForDiscount();
   }

    public Traveller(String email, String name, int age, String dateOfBirth, String userType, String cnic,
            String phoneNumber, String password) {
        super(email, name, age, dateOfBirth, userType, cnic, phoneNumber, password);
        //TODO Auto-generated constructor stub
    }

    public void fetchBooking(){
        dbhandler db = new dbhandler();
        bookings = db.getBookings(getEmail());
    }

     public List<Booking> getBookings() {
          return bookings;
     }

     public List<Integer> getTripIDs(){
          List<Integer> tripIDs = new ArrayList<Integer>();
          for(Booking booking: bookings){
               tripIDs.add(booking.getTrip_ID());
          }
          return tripIDs;
     }
}
