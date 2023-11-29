package Models;




public class UserService {

    private final dbhandler dbhandler = new dbhandler();

    public boolean createUser(User user) {
        if (doesUserExist(user.getEmail())) {
            return false; // User already exists
        }

        dbhandler.insertUser(
                user.getEmail(),
                user.getName(),
                user.getAge(),
                user.getDateOfBirth(),
                user.getUserType(),
                user.getCnic(),
                user.getPhoneNumber(),
                user.getPassword()
        );

        return true; // User created successfully
    }

    private boolean doesUserExist(String email) {
        return dbhandler.doesUserExist(email);
    }
}
