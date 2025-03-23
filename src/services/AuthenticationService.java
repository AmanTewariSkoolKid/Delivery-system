// src/services/AuthenticationService.java
package services;

import daos.UserDAO;
import entities.User;

public class AuthenticationService {
    private UserDAO userDAO = new UserDAO();

    public User authenticate(String username, String password) {
        return userDAO.findUserByCredentials(username, password);
    }
}