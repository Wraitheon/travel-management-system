package Models;

import java.util.List;

public class TravelAgency extends User {
    private List<Review> reviews;

    public TravelAgency(String email, String name, int age, String dateOfBirth, String userType, String cnic,
            String phoneNumber, String password) {
        super(email, name, age, dateOfBirth, userType, cnic, phoneNumber, password);
        
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    //getter setters
    
}
