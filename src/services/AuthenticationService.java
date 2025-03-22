package services;

import daos.UserDAO;
import entities.User;
import java.io.IOException;
import java.util.List;

public class AuthenticationService {
    private UserDAO userDAO = new UserDAO();

    public User authenticate(String username, String password) throws IOException {
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // Authentication failed
    }
}