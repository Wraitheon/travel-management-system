package Models;

import java.util.List;

public class Traveller extends User{
    LoyaltyProgram loyaltyPoints;
    List<Booking> bookings;

    public int getLoyaltyPoints() {
        return loyaltyPoints.getPoints();
    }

   public void fetchLoyaltyProgram() {
        dbhandler db = new dbhandler();
        
        loyaltyPoints = new LoyaltyProgram(db.getPoints(getEmail()));
   }

   public void redeemPoints(int points){
        loyaltyPoints.redeemPoints(points, getEmail());
   }

   public void addPoints(int points){
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
}
