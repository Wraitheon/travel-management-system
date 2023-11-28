import java.sql.*;

public class dbhandler {
    
    public static void insertUser(String email, String name, int age, String dateOfBirth, String userType, String cnic, String phoneNumber) {
        final String INSERT_QUERY = "INSERT INTO Users (email, name, age, date_of_birth, usertype, cnic, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(constants.url, constants.user, constants.password);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            // Set parameters for the prepared statement
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, dateOfBirth);
            preparedStatement.setString(5, userType);
            preparedStatement.setString(6, cnic);
            preparedStatement.setString(7, phoneNumber);

            // Execute the update
            preparedStatement.executeUpdate();

            System.out.println("User inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
