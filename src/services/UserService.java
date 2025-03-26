package services;

import daos.UserDAO;
import entities.User;
import java.io.IOException;
import java.util.List;

/**
 * Service class for managing user-related operations.
 * This class acts as a bridge between the controller and DAO layers,
 * providing business logic for user management.
 */
public class UserService {
    private UserDAO userDAO = new UserDAO();

    /**
     * Retrieves all users from the data source.
     * Delegates the call to the DAO layer.
     * 
     * @return List of all users.
     * @throws IOException if an I/O error occurs.
     */
    public List<User> getAllUsers() throws IOException {
        return userDAO.getAllUsers();
    }

    /**
     * Saves a new user to the data source.
     * Delegates the call to the DAO layer.
     * 
     * @param user The user to be saved.
     * @throws IOException if an I/O error occurs.
     */
    public void saveUser(User user) throws IOException {
        userDAO.saveUser(user);
    }
}