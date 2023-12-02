package Controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import Models.*;

public class Factory {
    private Factory(){}

    public static TourGuide createTourGuide(String email, String name, int age, String dateOfBirth, String userType, String cnic,
    String phoneNumber, String password){
        return new TourGuide(email, name, age, dateOfBirth, userType, cnic, phoneNumber, password);
    }

    public static TravelAgency creatTravelAgency(String email, String name, int age, String dateOfBirth, String userType, String cnic,
    String phoneNumber, String password){
        return new TravelAgency(email, name, age, dateOfBirth, userType, cnic, phoneNumber, password);
    }

    public static Traveller creatTraveller(String email, String name, int age, String dateOfBirth, String userType, String cnic,
    String phoneNumber, String password){
        return new Traveller(email, name, age, dateOfBirth, userType, cnic, phoneNumber, password);
    }

    public static Transportation createTransportation(int id, String mode, LocalDateTime time){
        return new Transportation(id, mode, time);
    }

    public static User createUser(String email, String name, int age, String dateOfBirth, String userType, String cnic, String phoneNumber, String password) {
        return new User(email, name, age, dateOfBirth, userType, cnic, phoneNumber, password);
    }

    public static Itinerary creatItinerary(int trip_Id){
        return new Itinerary(trip_Id);
    }
     public static Itinerary creatItinerary(List<ItineraryItem> items){
        return new Itinerary(items);
    }

    public static LoyaltyProgram createLoyaltyProgram(int points){
        return new LoyaltyProgram(points);
    }

    public static Payment createPayment( double amount , LocalDate date ,String method){
        return new Payment(amount, date, method);
    }


    public static Trip createTrip(double cost, LocalDate date, int days, Itinerary iti){
        return new Trip(cost, date, days, iti);
    }

    public static GuideBooking createGuideBooking(String email, LocalDate date, int days){
        return new GuideBooking(email, date, days);
    }
    

}
