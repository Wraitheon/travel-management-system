package Models;

public class User {

    private String email;
    private String name;
    private int age;
    private String dateOfBirth;
    private String userType;
    private String cnic;
    private String phoneNumber;
    private String password;

    public User(String email, String name, int age, String dateOfBirth, String userType, String cnic, String phoneNumber, String password) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.userType = userType;
        this.cnic = cnic;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void insertToDB(){
        dbhandler.insertUser(email, name, age, dateOfBirth, userType, cnic, phoneNumber, password);
    }
}
