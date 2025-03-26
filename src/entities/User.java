package entities;

public class User {
    // Unique identifier for the user
    private Long userId;

    // Username of the user
    private String username;

    // Password for authentication
    private String password;

    // Role of the user (e.g., admin, customer)
    private String role;

    // Phone number of the user
    private String phoneNumber;

    // Full name of the user
    private String name;

    // Default constructor
    public User() {}

    // Parameterized constructor to initialize all fields
    public User(Long userId, String username, String password, String role, String phoneNumber, String name) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    // Getter and setter for userId
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // Getter and setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and setter for role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Getter and setter for phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}