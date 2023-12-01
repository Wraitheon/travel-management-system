package Models;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;





public class dbhandler {

    public List<Booking> getBookings() {
        List<Booking> bookings = new ArrayList<>();

        String query = "SELECT * FROM Booking";

        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int bookingId = resultSet.getInt("booking_id");
                int tripId = resultSet.getInt("trip_id");
                String userEmail = resultSet.getString("user_email");
                Date bookingDate = resultSet.getDate("booking_date");

                Booking booking = new Booking(bookingId, tripId, userEmail, bookingDate);
                bookings.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return bookings;
    }

    public List<Payment> getPayments() {
        List<Payment> payments = new ArrayList<>();

        String query = "SELECT * FROM Payment";

        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int paymentId = resultSet.getInt("payment_id");
                int bookingId = resultSet.getInt("booking_id");
                Date paymentDate = resultSet.getDate("payment_date");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                String paymentMethod = resultSet.getString("payment_method");

                Payment payment = new Payment(paymentId, bookingId, paymentDate, amount, paymentMethod);
                payments.add(payment);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return payments;
    }

    public List<Review> getReviewsForTravelAgency(String travelAgencyEmail) {
        List<Review> reviews = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);

            String sql = "SELECT * FROM Review WHERE reviewed_user_email = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, travelAgencyEmail);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String userEmail = resultSet.getString("user_email");
                    int rating = resultSet.getInt("rating");
                    String comment = resultSet.getString("comment");
                    LocalDate reviewDate = resultSet.getDate("review_date").toLocalDate();

                    Review review = new Review( userEmail, rating, comment, reviewDate);
                    reviews.add(review);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return reviews;
    }

    public List<TravelAgency> getTravelAgencies() {
        List<TravelAgency> travelAgencies = new ArrayList<>();

        try {
            // Open connection
           Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);

            // SQL query to fetch Travel Agencies
            String sql = "SELECT * FROM Users WHERE usertype = 'Travel Agency'";
            
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Execute the query
                ResultSet resultSet = statement.executeQuery();

                // Process the result set
                while (resultSet.next()) {
                    String email = resultSet.getString("email");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String dateOfBirth = resultSet.getString("date_of_birth");
                    String userType = resultSet.getString("usertype");
                    String cnic = resultSet.getString("cnic");
                    String phoneNumber = resultSet.getString("phone_number");
                    String password = resultSet.getString("password");

                    // Create TravelAgency object and add to the list
                    TravelAgency travelAgency = new TravelAgency(email, name, age, dateOfBirth, userType, cnic, phoneNumber, password);
                    travelAgencies.add(travelAgency);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } 

        return travelAgencies;
    }

    public List<Restaurants> getRestaurantsForItinerary(int itineraryId) {
        String sql = "SELECT r.restaurant_id, r.restaurant_name, r.cost, ir.scheduledTime " +
                     "FROM Restaurants r " +
                     "JOIN ItineraryRestaurants ir ON r.restaurant_id = ir.restaurant_id " +
                     "WHERE ir.itinerary_id = ?";
        List<Restaurants> restaurantList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, itineraryId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int restaurantId = resultSet.getInt("restaurant_id");
                    String restaurantName = resultSet.getString("restaurant_name");
                    double cost = resultSet.getDouble("cost");
                    LocalDateTime scheduledTime = resultSet.getTimestamp("scheduledTime").toLocalDateTime();

                    Restaurants restaurant = new Restaurants(restaurantId, scheduledTime, restaurantName, cost);
                    restaurantList.add(restaurant);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return restaurantList;
    }

    public List<Accomodation> getAccommodationForItinerary(int itineraryId) {
        String sql = "SELECT a.accommodation_id, a.location, a.motel_name, a.cost, ia.check_in_date " +
                     "FROM Accommodation a " +
                     "JOIN ItineraryAccommodation ia ON a.accommodation_id = ia.accommodation_id " +
                     "WHERE ia.itinerary_id = ?";
        List<Accomodation> accommodationList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, itineraryId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int accommodationId = resultSet.getInt("accommodation_id");
                    String location = resultSet.getString("location");
                    String motelName = resultSet.getString("motel_name");
                    double cost = resultSet.getDouble("cost");
                    LocalDateTime checkInDate = resultSet.getTimestamp("check_in_date").toLocalDateTime();

                    Accomodation accommodation = new Accomodation(accommodationId, location, motelName, cost, checkInDate);
                    accommodationList.add(accommodation);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return accommodationList;
    }

    public List<Transportation> getTransportationForItinerary(int itineraryId) {
        String sql = "SELECT t.transportation_id, t.mode_of_transport, it.departure_date " +
                     "FROM Transportation t " +
                     "JOIN ItineraryTransportation it ON t.transportation_id = it.transportation_id " +
                     "WHERE it.itinerary_id = ?";
        List<Transportation> transportationList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, itineraryId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int transportationId = resultSet.getInt("transportation_id");
                    String modeOfTransport = resultSet.getString("mode_of_transport");
                    LocalDateTime departureDate = resultSet.getTimestamp("departure_date").toLocalDateTime();

                    Transportation transportation = new Transportation(transportationId, modeOfTransport, departureDate);
                    transportationList.add(transportation);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return transportationList;
    }


    public List<Activity> getActivitiesForTrip(int tripId) {
        String sql = "SELECT a.activity_id, a.activity_name, a.activity_description, a.cost, a.activity_date " +
                     "FROM Activities a " +
                     "JOIN Trip t ON a.trip_id = t.trip_id " +
                     "WHERE t.trip_id = ?";
        List<Activity> activities = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, tripId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int activityId = resultSet.getInt("activity_id");
                    String activityName = resultSet.getString("activity_name");
                    String activityDescription = resultSet.getString("activity_description");
                    double cost = resultSet.getDouble("cost");
                    LocalDateTime dateTime = resultSet.getTimestamp("activity_date").toLocalDateTime();

                    Activity activity = new Activity(activityId, activityName, activityDescription, cost, dateTime);
                    activities.add(activity);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return activities;
    }

    public int getItineraryIdForTrip(int tripId) {
        String sql = "SELECT itinerary_id FROM Itinerary WHERE trip_id = ?";
        int itineraryId = -1; // Default value if not found

        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, tripId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    itineraryId = resultSet.getInt("itinerary_id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return itineraryId;
    }

    public List<TripTable> getTripDataForUser(String userEmail) {
        List<TripTable> tripDataList = new ArrayList<>();
    
        String sql = "SELECT t.trip_id AS trip_ID, d.destination_name AS destination, t.prices AS price, t.trip_date AS trip_Date, t.number_of_days AS noOfDays FROM Trip t JOIN Destinations d ON t.destination_id = d.destination_id WHERE t.user_email = ?";
    
        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    
            preparedStatement.setString(1, userEmail);
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int trip_ID = resultSet.getInt("trip_ID");
                    String destination = resultSet.getString("destination");
                    double price = resultSet.getDouble("price");
                    LocalDate trip_Date = resultSet.getDate("trip_Date").toLocalDate();
                    int noOfDays = resultSet.getInt("noOfDays");
    
                    TripTable tripTable = new TripTable(trip_ID, destination, price, trip_Date, noOfDays);
                    tripDataList.add(tripTable);
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    
        return tripDataList;
    }
    

    public List<Trip> getTripsByUserEmail(String userEmail) {
        List<Trip> trips = new ArrayList<>();
        String query = "SELECT * FROM Trip WHERE user_email = ?";

        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userEmail);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    
                    
                    int tripId = resultSet.getInt("trip_id");
                    System.out.println(tripId);
                    int destinationId = resultSet.getInt("destination_id");
                    String tripDate = resultSet.getString("trip_date");
                    double prices = resultSet.getDouble("prices");
                    int numberOfDays = resultSet.getInt("number_of_days");

                    // Define the date format based on your database date format
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    // Parse the string to LocalDate
                    LocalDate localDate = LocalDate.parse(tripDate, formatter);

                    Trip trip = new Trip(tripId, destinationId, prices, localDate, numberOfDays);
                    trips.add(trip);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return trips;
    }


    public void insertItineraryAccommodation(int itineraryId, int accommodationId, LocalDateTime checkInDateTime) {
        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password)) {
            String sql = "INSERT INTO ItineraryAccommodation (itinerary_id, accommodation_id, check_in_date) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, itineraryId);
                preparedStatement.setInt(2, accommodationId);
                preparedStatement.setTimestamp(3, Timestamp.valueOf(checkInDateTime));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
    
    

    public void insertItineraryTransportation(int transportationId, int itineraryId, LocalDateTime departureDateTime) {

        System.out.println(transportationId);
        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password)) {
            String sql = "INSERT INTO ItineraryTransportation (transportation_id, itinerary_id, departure_date) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, transportationId);
                preparedStatement.setInt(2, itineraryId);
                preparedStatement.setTimestamp(3, Timestamp.valueOf(departureDateTime));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
    
    

    public void insertItineraryRestaurant(int restaurantId, int itineraryId, LocalDateTime scheduledTime) {
    try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password)) {
        String sql = "INSERT INTO ItineraryRestaurants (restaurant_id, itinerary_id, scheduledTime) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, restaurantId);
            preparedStatement.setInt(2, itineraryId);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(scheduledTime));
            preparedStatement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Handle the exception appropriately
    }
}


    public int countItinerary() {
        int count = 0;
    
        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password)) {
            String sql = "SELECT COUNT(*) FROM Itinerary";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    
        return count;
    }
    

    public int addItinerary(int tripId) {
        int generatedItineraryId = -1; // Default value if the insertion fails
    
        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password)) {
            String sql = "INSERT INTO Itinerary (trip_id) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, tripId);
                preparedStatement.executeUpdate();
    
                // Retrieve the auto-generated keys (itinerary_id)
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedItineraryId = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    
        return generatedItineraryId;
    }
    
    

    public int totalTripCount() {
        int tripCount = 0;
    
        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password)) {
            String sql = "SELECT COUNT(*) FROM Trip";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    tripCount = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    
        return tripCount;
    }
    
    public int addTrip(String userEmail, int destinationId, LocalDate tripDate, double prices, int numberOfDays) {
        int generatedTripId = -1; // Default value if the insertion fails

        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password)) {
            String sql = "INSERT INTO Trip (user_email, destination_id, trip_date, prices, number_of_days) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, userEmail);
                preparedStatement.setInt(2, destinationId);
                preparedStatement.setDate(3, Date.valueOf(tripDate));
                preparedStatement.setDouble(4, prices);
                preparedStatement.setInt(5, numberOfDays);

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    // Retrieve the auto-generated keys (trip_id)
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            generatedTripId = generatedKeys.getInt(1);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return generatedTripId;
    }
    

    public void addActivity(int tripId, String activityName, LocalDateTime activityDateTime, String activityDescription, double cost) {
        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password)) {
            String sql = "INSERT INTO Activities (trip_id, activity_name, activity_date, activity_description, cost) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, tripId);
                preparedStatement.setString(2, activityName);
                preparedStatement.setTimestamp(3, Timestamp.valueOf(activityDateTime));
                preparedStatement.setString(4, activityDescription);
                preparedStatement.setDouble(5, cost);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public int countActivities() {
        int count = 0;
    
        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password)) {
            String sql = "SELECT COUNT(*) FROM Activities";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    
        return count;
    }

    public int countAccommodations() {
        try {
            // Establish a connection
            Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
    
            // Create a statement
            Statement statement = connection.createStatement();
    
            // Define the SQL query
            String sql = "SELECT COUNT(*) FROM Accommodation";
    
            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery(sql);
    
            // Process the result set
            if (resultSet.next()) {
                int rowCount = resultSet.getInt(1);
                return rowCount;
            }
    
            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int countRestaurants(){
        try {
            // Establish a connection
            Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);

            // Create a statement
            Statement statement = connection.createStatement();

            // Define the SQL query
            String sql = "SELECT COUNT(*) FROM restaurants";

            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery(sql);

            // Process the result set
            if (resultSet.next()) {
                int rowCount = resultSet.getInt(1);
                return rowCount;
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addRestaurant(int destinationId, String restaurantName, double cost) {
        String sql = "INSERT INTO Restaurants (destination_id, restaurant_name, cost) VALUES (?, ?, ?)";

        try (
            Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, destinationId);
            preparedStatement.setString(2, restaurantName);
            preparedStatement.setDouble(3, cost);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public void addAccommodation(int destinationId, String location, String motelName, double cost) {
        String query = "INSERT INTO Accommodation (destination_id, location, motel_name, cost) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, destinationId);
            preparedStatement.setString(2, location);
            preparedStatement.setString(3, motelName);
            preparedStatement.setDouble(4, cost);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed (e.g., log or display an error message)
        }
    }

    public List<Restaurants> getRestaurantsForDestination(int destinationId) {
        List<Restaurants> restaurants = new ArrayList<>();
    
        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password)) {
            String sql = "SELECT restaurant_id, restaurant_name, cost FROM Restaurants WHERE destination_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, destinationId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int restaurantId = resultSet.getInt("restaurant_id");
                    String restaurantName = resultSet.getString("restaurant_name");
                    double cost = resultSet.getDouble("cost");
    
                    // Assuming you have a constructor in the Restaurant class
                    Restaurants restaurant = new Restaurants(restaurantId, null, restaurantName, cost);
    
                    restaurants.add(restaurant);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    
        return restaurants;
    }
    
    

    public List<Accomodation> getAccommodationsForDestination(int destinationId) {
        List<Accomodation> accommodations = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password)) {
            String sql = "SELECT accommodation_id, location, motel_name, cost FROM Accommodation WHERE destination_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, destinationId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("accommodation_id");
                    String location = resultSet.getString("location");
                    String motelName = resultSet.getString("motel_name");
                    double cost = resultSet.getDouble("cost");

                    // Assuming you have a constructor in the Accommodation class
                    Accomodation accommodation = new Accomodation(id, location, motelName, cost, null);

                    accommodations.add(accommodation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return accommodations;
    }

    

    public List<Activity> getActivities() {
        List<Activity> activities = new ArrayList<>();
    
        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password)) {
            String sql = "SELECT * cost FROM Activities";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("activity_id");
                    String activityName = resultSet.getString("activity_name");
                    String activityDescription = resultSet.getString("activity_description");
                    double cost = resultSet.getDouble("cost");
    
                    // Assuming you have a constructor in the Activity class
                    Activity activity = new Activity(id, activityName, activityDescription, cost, null);
    
                    activities.add(activity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    
        return activities;
    }
    
    

    public List<Destination> getDestinations() {
        List<Destination> destinations = new ArrayList<>();
    
        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             Statement statement = connection.createStatement()) {
    
            ResultSet resultSet = statement.executeQuery("SELECT destination_id, destination_name FROM Destinations");
    
            while (resultSet.next()) {
                int id = resultSet.getInt("destination_id");
                String name = resultSet.getString("destination_name");
                destinations.add(new Destination(id, name));
            }
    
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    
        return destinations;
    }


    public String getUserType(String userEmail) {
        String query = "SELECT usertype FROM Users WHERE email = ?";
        
        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userEmail);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String result = resultSet.getString("usertype");
                    return result;
                } else {
                    return null; // User not found
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
            return null; // Assume retrieval failed on error
        }
    }
    
    public boolean authenticateUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        
        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // true if there is at least one result (email and password match)
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
            return false; // Assume authentication failed on error
        }
    }
    
    public static boolean doesUserExist(String email) {
        final String SELECT_QUERY = "SELECT * FROM Users WHERE email = ?";
        
        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
    
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            return resultSet.next(); // If next() is true, the user exists; otherwise, it doesn't
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Handle the exception appropriately based on your application's requirements
        }
    }
    
    public void insertUser(String email, String name, int age, String dateOfBirth, String userType, String cnic, String phoneNumber, String password) {
        final String INSERT_QUERY = "INSERT INTO Users (email, name, age, date_of_birth, usertype, cnic, phone_number, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Check if the user already exists
        if (doesUserExist(email)) {
            System.out.println("User with email " + email + " already exists.");
            return; // Exit the method without attempting to insert
        }
    
        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
    
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, dateOfBirth);
            preparedStatement.setString(5, userType);
            preparedStatement.setString(6, cnic);
            preparedStatement.setString(7, phoneNumber);
            preparedStatement.setString(8, password); // Added password parameter
    
            // Execute the update
            preparedStatement.executeUpdate();
    
            System.out.println("User inserted successfully.");
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     private static boolean doesDestinationExist(String destinationName) {
        final String CHECK_EXISTENCE_QUERY = "SELECT COUNT(*) FROM Destinations WHERE destination_name = ?";
        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EXISTENCE_QUERY)) {

            preparedStatement.setString(1, destinationName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

     // Function to check if a destination with the given ID exists
    private static boolean doesDestinationExist(int destinationId) {
        final String CHECK_EXISTENCE_QUERY = "SELECT COUNT(*) FROM Destinations WHERE destination_id = ?";
        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EXISTENCE_QUERY)) {

            preparedStatement.setInt(1, destinationId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Function to insert a new destination into the Destinations table
    public static void insertDestination(String destinationName) {
        final String INSERT_QUERY = "INSERT INTO Destinations (destination_name) VALUES (?)";

        // Check if the destination already exists
        if (doesDestinationExist(destinationName)) {
            System.out.println("Destination with name " + destinationName + " already exists.");
            return; // Exit the method without attempting to insert
        }

        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setString(1, destinationName);

            // Execute the update
            preparedStatement.executeUpdate();

            System.out.println("Destination inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static boolean doesTripExist(int tripId) {
        final String CHECK_EXISTENCE_QUERY = "SELECT COUNT(*) FROM Trip WHERE trip_id = ?";
        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EXISTENCE_QUERY)) {

            preparedStatement.setInt(1, tripId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Function to insert a new trip into the Trip table
    public static void insertTrip(int destinationId, String user_email, Date tripDate, BigDecimal prices, int numberOfDays) {
        final String INSERT_QUERY = "INSERT INTO Trip (destination_id, user_email, trip_date, prices, number_of_days) VALUES (?, ?, ?, ?, ?)";

        // Check if the destination and user exist
        if (!doesDestinationExist(destinationId)) {
            System.out.println("Destination with ID " + destinationId + " does not exist.");
            return; // Exit the method without attempting to insert
        }

        if (!doesUserExist(user_email)) {
            System.out.println("User with email " + user_email + " does not exist.");
            return; // Exit the method without attempting to insert
        }

        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, destinationId);
            preparedStatement.setString(2, user_email);
            preparedStatement.setDate(3, tripDate);
            preparedStatement.setBigDecimal(4, prices);
            preparedStatement.setInt(5, numberOfDays);

            // Execute the update
            preparedStatement.executeUpdate();

            // Retrieve the auto-generated trip_id
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int tripId = generatedKeys.getInt(1);
                    System.out.println("Trip inserted successfully with ID: " + tripId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to insert a new review into the Review table
    public static void insertReview(String user_email, String reviewedUserEmail, int rating, String comment, LocalDate reviewDate) {
        final String INSERT_QUERY = "INSERT INTO Review (user_email, reviewed_user_email, rating, comment, review_date) VALUES (?, ?, ?, ?, ?)";

        // Check if the users exist
        if (!doesUserExist(user_email)) {
            System.out.println("User with email " + user_email + " does not exist.");
            return; // Exit the method without attempting to insert
        }

        if (!doesUserExist(reviewedUserEmail)) {
            System.out.println("User with email " + reviewedUserEmail + " does not exist.");
            return; // Exit the method without attempting to insert
        }

        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, user_email);
            preparedStatement.setString(2, reviewedUserEmail);
            preparedStatement.setInt(3, rating);
            preparedStatement.setString(4, comment);
            preparedStatement.setDate(5, Date.valueOf(reviewDate));

            // Execute the update
            preparedStatement.executeUpdate();

            // Retrieve the auto-generated review_id
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int reviewId = generatedKeys.getInt(1);
                    System.out.println("Review inserted successfully with ID: " + reviewId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to insert a new booking into the Booking table
    public static void insertBooking(int tripId, String user_email, Date bookingDate) {
        final String INSERT_QUERY = "INSERT INTO Booking (trip_id, user_email, booking_date) VALUES (?, ?, ?)";

        // Check if the trip and user exist
        if (!doesTripExist(tripId)) {
            System.out.println("Trip with ID " + tripId + " does not exist.");
            return; // Exit the method without attempting to insert
        }

        if (!doesUserExist(user_email)) {
            System.out.println("User with email " + user_email + " does not exist.");
            return; // Exit the method without attempting to insert
        }

        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, tripId);
            preparedStatement.setString(2, user_email);
            preparedStatement.setDate(3, bookingDate);

            // Execute the update
            preparedStatement.executeUpdate();

            // Retrieve the auto-generated booking_id
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int bookingId = generatedKeys.getInt(1);
                    System.out.println("Booking inserted successfully with ID: " + bookingId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
