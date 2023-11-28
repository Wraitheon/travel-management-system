import java.math.BigDecimal;
import java.sql.*;


public class dbhandler {
    
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
    
    public static void insertUser(String email, String name, int age, String dateOfBirth, String userType, String cnic, String phoneNumber, String password) {
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
    public static void insertReview(String user_email, String reviewedUserEmail, int rating, String comment, Date reviewDate) {
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
            preparedStatement.setDate(5, reviewDate);

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
