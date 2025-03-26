import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.AuthController;
import entities.User;

public class LoginGUI extends JFrame {

    // GUI components for user input and display
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel imageLabel;

    // Controller for authentication logic
    private AuthController authController;

    public LoginGUI() {
        // Initialize the authentication controller
        authController = new AuthController();

        // Set up the main frame properties
        setTitle("Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Load and set the background image
        ImageIcon imageIcon = new ImageIcon("login_background.png"); // Replace with your image path
        Image image = imageIcon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0, 0, 600, 400);
        add(imageLabel);

        // Create a panel for login components
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2)); // Grid layout for labels and fields
        loginPanel.setBounds(50, 200, 300, 100); // Position and size of the panel
        loginPanel.setOpaque(false); // Make the panel transparent
        imageLabel.add(loginPanel);

        // Add email and password fields with labels
        JLabel emailLabel = new JLabel("E-Mail:");
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton signInButton = new JButton("Sign in");

        // Add components to the login panel
        loginPanel.add(emailLabel);
        loginPanel.add(emailField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel()); // Empty label for spacing
        loginPanel.add(signInButton);

        // Add action listener for the sign-in button
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve user input
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Authenticate the user
                User authenticatedUser = authController.authenticate(email, password);

                if (authenticatedUser != null) {
                    // Show success message and navigate based on user role
                    JOptionPane.showMessageDialog(LoginGUI.this, "Authentication successful: " + authenticatedUser.getUsername());
                    if ("admin".equals(authenticatedUser.getRole())) {
                        dispose(); // Close LoginGUI
                        new AdminDashboardGUI(authenticatedUser); // Open AdminDashboardGUI
                    } else {
                        openMainApplication();
                    }
                } else {
                    // Show error message for failed authentication
                    JOptionPane.showMessageDialog(LoginGUI.this, "Authentication failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Make the frame visible
        setVisible(true);
    }

    // Placeholder method for opening the main application
    private void openMainApplication() {
        JOptionPane.showMessageDialog(LoginGUI.this, "Main App would open here");
        // Replace this with your actual main application opening logic.
    }

    public static void main(String[] args) {
        // Launch the Login GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginGUI();
            }
        });
    }
}