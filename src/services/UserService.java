package services;

import daos.UserDAO;
import entities.User;
import java.io.IOException;
import java.util.List;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public List<User> getAllUsers() throws IOException {
        return userDAO.getAllUsers();
    }
    public void saveUser(User user)throws IOException{
        userDAO.saveUser(user);
    }
}