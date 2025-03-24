// src/daos/UserDAO.java
package daos;

import entities.User;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private List<User> users = new ArrayList<>();

    public UserDAO() {
        populateUsers();
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void saveUser(User user) {
        users.add(user);
    }

    public User findUserByCredentials(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // User not found
    }

    private void populateUsers() {
        users.add(new User(1L, "admin", "password", "admin", "123-456-7890", "Admin User"));
        users.add(new User(2L, "user1", "pass123", "user", "987-654-3210", "Regular User"));
        users.add(new User(3L, "user2", "secure", "user", "555-123-4567", "Another User"));
        users.add(new User(4L, "user3", "password", "user", "123-456-7890", "Yet Another User"));
    }
}