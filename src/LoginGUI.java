import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.AuthController;
import entities.User;

public class LoginGUI extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel imageLabel;

    private AuthController authController;

    public LoginGUI() {
        authController = new AuthController();

        setTitle("Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Load background image (replace with your image path)
        ImageIcon imageIcon = new ImageIcon("login_background.png"); // Replace with your image path
        Image image = imageIcon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0, 0, 600, 400);
        add(imageLabel);

        // Panel for login components
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));
        loginPanel.setBounds(50, 200, 300, 100);
        loginPanel.setOpaque(false);
        imageLabel.add(loginPanel);

        JLabel emailLabel = new JLabel("E-Mail:");
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton signInButton = new JButton("Sign in");

        loginPanel.add(emailLabel);
        loginPanel.add(emailField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel());
        loginPanel.add(signInButton);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                User authenticatedUser = authController.authenticate(email, password);

                if (authenticatedUser != null) {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Authentication successful: " + authenticatedUser.getUsername());
                    if ("admin".equals(authenticatedUser.getRole())) {
                        dispose(); // Close LoginGUI
                        new AdminDashboardGUI(authenticatedUser); //Open AdminDashboardGUI
                    } else {
                        openMainApplication();
                    }

                } else {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Authentication failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    private void openMainApplication() {
        JOptionPane.showMessageDialog(LoginGUI.this, "Main App would open here");
        // Replace this with your actual main application opening logic.
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginGUI();
            }
        });
    }
}