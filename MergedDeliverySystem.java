/**
 * Delivery System Application
 * A comprehensive delivery management system with user authentication, order tracking, and warehouse management.
 * The application follows a multi-tier architecture with entities, data access objects, services, and controllers.
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Entities
class User {
    private Long userId;
    private String username;
    private String password;
    private String role;
    private String phoneNumber;
    private String name;

    public User() {}

    public User(Long userId, String username, String password, String role, String phoneNumber, String name) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

class Order {
    private Long orderId;
    private String description;
    private String customerPhone;
    private Long warehouseId;
    private Long driverId;
    private String deliveryStatus;

    public Order() {}

    public Order(Long orderId, String description, String customerPhone, Long warehouseId, Long driverId, String deliveryStatus) {
        this.orderId = orderId;
        this.description = description;
        this.customerPhone = customerPhone;
        this.warehouseId = warehouseId;
        this.driverId = driverId;
        this.deliveryStatus = deliveryStatus;
    }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }
    public Long getWarehouseId() { return warehouseId; }
    public void setWarehouseId(Long warehouseId) { this.warehouseId = warehouseId; }
    public Long getDriverId() { return driverId; }
    public void setDriverId(Long driverId) { this.driverId = driverId; }
    public String getDeliveryStatus() { return deliveryStatus; }
    public void setDeliveryStatus(String deliveryStatus) { this.deliveryStatus = deliveryStatus; }
}

class Warehouse {
    private Long warehouseId;
    private String warehouseName;
    private String location;
    private String contactPerson;
    private String contactPhone;

    public Warehouse() {}

    public Warehouse(Long warehouseId, String warehouseName, String location, String contactPerson, String contactPhone) {
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.location = location;
        this.contactPerson = contactPerson;
        this.contactPhone = contactPhone;
    }

    public Long getWarehouseId() { return warehouseId; }
    public void setWarehouseId(Long warehouseId) { this.warehouseId = warehouseId; }
    public String getWarehouseName() { return warehouseName; }
    public void setWarehouseName(String warehouseName) { this.warehouseName = warehouseName; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
}

// DAOs
class UserDAO {
    private List<User> users = new ArrayList<>();

    public UserDAO() { populateUsers(); }

    public List<User> getAllUsers() { return users; }
    public void saveUser(User user) { users.add(user); }
    public User findUserByCredentials(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private void populateUsers() {
        users.add(new User(1L, "admin", "password", "admin", "123-456-7890", "Admin User"));
        users.add(new User(2L, "aman", "aman", "admin", "123-456-7890", "Aman Tewari"));
    }
}

class OrderDAO {
    private List<Order> orders = new ArrayList<>();

    public OrderDAO() { populateOrders(); }

    public List<Order> getAllOrders() { return orders; }
    public void saveOrder(Order order) { orders.add(order); }
    public void updateOrder(Order order) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderId().equals(order.getOrderId())) {
                orders.set(i, order);
                break;
            }
        }
    }

    private void populateOrders() {
        orders.add(new Order(1L, "Laptop", "123-456-7890", 1L, 1L, "Delivered"));
        orders.add(new Order(2L, "Phone", "098-765-4321", 2L, 2L, "In Transit"));
    }
}

class WarehouseDAO {
    private List<Warehouse> warehouses = new ArrayList<>();

    public WarehouseDAO() { populateWarehouses(); }

    public List<Warehouse> getAllWarehouses() { return warehouses; }
    public void saveWarehouse(Warehouse warehouse) { warehouses.add(warehouse); }

    private void populateWarehouses() {
        warehouses.add(new Warehouse(1L, "Warehouse A", "Location A", "Contact A", "100-200-3000"));
        warehouses.add(new Warehouse(2L, "Warehouse B", "Location B", "Contact B", "400-500-6000"));
    }
}

// Services
class AuthenticationService {
    private UserDAO userDAO = new UserDAO();

    public User authenticate(String username, String password) {
        return userDAO.findUserByCredentials(username, password);
    }
}

class OrderService {
    private OrderDAO orderDAO = new OrderDAO();

    public List<Order> getAllOrders() { return orderDAO.getAllOrders(); }
    public void saveOrder(Order order) { orderDAO.saveOrder(order); }
    public void updateOrder(Order updatedOrder) {
        for (Order order : orderDAO.getAllOrders()) {
            if (order.getOrderId().equals(updatedOrder.getOrderId())) {
                if (updatedOrder.getDescription() != null && !updatedOrder.getDescription().isEmpty()) {
                    order.setDescription(updatedOrder.getDescription());
                }
                if (updatedOrder.getCustomerPhone() != null && !updatedOrder.getCustomerPhone().isEmpty()) {
                    order.setCustomerPhone(updatedOrder.getCustomerPhone());
                }
                if (updatedOrder.getWarehouseId() != null) {
                    order.setWarehouseId(updatedOrder.getWarehouseId());
                }
                if (updatedOrder.getDriverId() != null) {
                    order.setDriverId(updatedOrder.getDriverId());
                }
                if (updatedOrder.getDeliveryStatus() != null && !updatedOrder.getDeliveryStatus().isEmpty()) {
                    order.setDeliveryStatus(updatedOrder.getDeliveryStatus());
                }
                orderDAO.updateOrder(order);
                break;
            }
        }
    }
}

class WarehouseService {
    private WarehouseDAO warehouseDAO = new WarehouseDAO();

    public List<Warehouse> getAllWarehouses() { return warehouseDAO.getAllWarehouses(); }
    public void saveWarehouse(Warehouse warehouse) { warehouseDAO.saveWarehouse(warehouse); }
}

// Controllers
class AuthController {
    private AuthenticationService authService = new AuthenticationService();

    public User authenticate(String username, String password) {
        return authService.authenticate(username, password);
    }
}

class AdminDashboardGUI extends JFrame {
    private WarehouseDAO warehouseDAO = new WarehouseDAO();
    private OrderDAO orderDAO = new OrderDAO();

    public AdminDashboardGUI(User adminUser) {
        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome " + adminUser.getUsername());
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBounds(300, 50, 300, 30);
        add(welcomeLabel);

        JButton allStockButton = new JButton("All warehouse list");
        allStockButton.setBounds(200, 200, 150, 40);
        allStockButton.addActionListener(e -> showAllWarehousesTable());
        add(allStockButton);

        JButton showAllOrdersButton = new JButton("Show All Orders Details");
        showAllOrdersButton.setBounds(300, 300, 200, 40);
        showAllOrdersButton.addActionListener(e -> showAllOrdersDetails());
        add(showAllOrdersButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(20, 520, 80, 30);
        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Logged out.");
            dispose();
            new LoginGUI();
        });
        add(logoutButton);

        setVisible(true);
    }

    private void showAllWarehousesTable() {
        List<Warehouse> warehouses = warehouseDAO.getAllWarehouses();
        String[] columnNames = {"Warehouse ID", "Name", "Location", "Contact Person", "Contact Phone"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Warehouse warehouse : warehouses) {
            model.addRow(new Object[]{
                    warehouse.getWarehouseId(),
                    warehouse.getWarehouseName(),
                    warehouse.getLocation(),
                    warehouse.getContactPerson(),
                    warehouse.getContactPhone()
            });
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 300));
        JOptionPane.showMessageDialog(this, scrollPane, "All Warehouses", JOptionPane.PLAIN_MESSAGE);
    }

    private void showAllOrdersDetails() {
        List<Order> orders = orderDAO.getAllOrders();
        String[] columnNames = {"Order ID", "Description", "Customer Phone", "Warehouse ID", "Driver ID", "Delivery Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Order order : orders) {
            model.addRow(new Object[]{
                    order.getOrderId(),
                    order.getDescription(),
                    order.getCustomerPhone(),
                    order.getWarehouseId(),
                    order.getDriverId(),
                    order.getDeliveryStatus()
            });
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 500));
        JOptionPane.showMessageDialog(this, scrollPane, "All Orders Details", JOptionPane.PLAIN_MESSAGE);
    }
}

class LoginGUI extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private AuthController authController = new AuthController();

    public LoginGUI() {
        setTitle("Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel emailLabel = new JLabel("E-Mail:");
        emailLabel.setBounds(50, 100, 100, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 100, 200, 30);
        add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 150, 100, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 150, 200, 30);
        add(passwordField);

        JButton signInButton = new JButton("Sign in");
        signInButton.setBounds(150, 200, 100, 30);
        signInButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            User authenticatedUser = authController.authenticate(email, password);

            if (authenticatedUser != null) {
                JOptionPane.showMessageDialog(this, "Authentication successful: " + authenticatedUser.getUsername());
                if ("admin".equals(authenticatedUser.getRole())) {
                    dispose();
                    new AdminDashboardGUI(authenticatedUser);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Authentication failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(signInButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginGUI::new);
    }
}