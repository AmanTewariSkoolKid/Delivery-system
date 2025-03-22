package controllers;

import entities.User;
import services.UserService;

import java.io.IOException;
import java.util.List;

public class UserController {
    private UserService userService = new UserService();

    public List<User> getAllUsers() throws IOException {
        return userService.getAllUsers();
    }
    public void createUser(User user) throws IOException{
        userService.saveUser(user);
    }
}