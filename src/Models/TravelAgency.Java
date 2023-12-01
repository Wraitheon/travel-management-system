package Models;

import java.util.List;

public class TravelAgency extends User {
    private List<Review> reviews;
    private List<Trip> trips;
   

    private int rating;

    public TravelAgency(String email, String name, int age, String dateOfBirth, String userType, String cnic,
            String phoneNumber, String password) {
        super(email, name, age, dateOfBirth, userType, cnic, phoneNumber, password);
        
        dbhandler db = new dbhandler();

        rating = 0;
        trips = db.getTripsByUserEmail(getEmail());
        reviews = db.getReviewsForTravelAgency(getEmail());
        
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    //getter setters
    
}
