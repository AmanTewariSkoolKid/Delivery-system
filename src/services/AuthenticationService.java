// src/services/AuthenticationService.java
package services;

import daos.UserDAO;
import entities.User;

/**
 * Service class for handling user authentication.
 */
public class AuthenticationService {
    private UserDAO userDAO = new UserDAO();

    /**
     * Authenticates a user based on their username and password.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The authenticated user, or null if authentication fails.
     */
    public User authenticate(String username, String password) {
        return userDAO.findUserByCredentials(username, password);
    }
}