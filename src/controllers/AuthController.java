package controllers;

import entities.User;
import services.AuthenticationService;

import java.io.IOException;

public class AuthController {
    private AuthenticationService authService = new AuthenticationService();

    public User authenticate(String username, String password) throws IOException {
        return authService.authenticate(username, password);
    }
}