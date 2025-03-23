//src/controllers/AuthController.java
package controllers;

import entities.User;
import services.AuthenticationService;

public class AuthController {
    private AuthenticationService authService = new AuthenticationService();

    public User authenticate(String username, String password) {
        return authService.authenticate(username, password);
    }
}