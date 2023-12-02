package Models;

import java.util.List;

public class TravelAgency extends User {
    private List<Review> reviews;
    private List<Trip> trips;
   

    

    public TravelAgency(String email, String name, int age, String dateOfBirth, String userType, String cnic,
            String phoneNumber, String password) {
        super(email, name, age, dateOfBirth, userType, cnic, phoneNumber, password);
        
            
        fetchReviews();
        fetchTrips();
       
        
    }

    public void fetchTrips(){
        
         trips = dbhandler.getTripsByUserEmail(getEmail());
    }

    public void fetchReviews(){
 
        reviews = dbhandler.getReviewsForTravelAgency(getEmail());

        
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

    public double avgRating(){

        double total = 0;

        for (var review : reviews) {
            total += review.getRating();
        }

        return total/reviews.size();

    }

    //getter setters
    
}
