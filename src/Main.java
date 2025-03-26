// src/Main.java
// This is the main entry point for the Delivery System application
// It demonstrates the use of various controllers to manage orders, users, warehouses, and authentication
import controllers.AuthController;
import controllers.OrderController;
import controllers.UserController;
import controllers.WarehouseController;
import entities.Order;
import entities.User;
import entities.Warehouse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Main class that provides a command-line interface to interact with the delivery system.
 * Demonstrates CRUD operations for orders, users, and warehouses, plus authentication.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize controllers for managing different entities
        OrderController orderController = new OrderController();
        UserController userController = new UserController();
        WarehouseController warehouseController = new WarehouseController();
        AuthController authController = new AuthController();
        // Create reader for console input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            // ===== ORDER MANAGEMENT SECTION =====
            System.out.println("--- Order Actions ---");
            
            // Display all existing orders
            System.out.println("Current Orders (orderId, description, customerPhone, warehouseId, driverId, deliveryStatus):");
            List<Order> orders = orderController.getAllOrders();
            for (Order order : orders) {
                System.out.println(order.getOrderId() + ", " + order.getDescription() + ", " + order.getCustomerPhone() + ", " + order.getWarehouseId() + ", " + order.getDriverId() + ", " + order.getDeliveryStatus());
            }

            // Collect input for creating a new order
            System.out.println("\nEnter new order details:");
            System.out.print("orderId: ");
            long orderId = Long.parseLong(reader.readLine());
            System.out.print("description: ");
            String description = reader.readLine();
            System.out.print("customerPhone: ");
            String customerPhone = reader.readLine();
            System.out.print("warehouseId: ");
            long warehouseId = Long.parseLong(reader.readLine());
            System.out.print("driverId: ");
            long driverId = Long.parseLong(reader.readLine());
            System.out.print("deliveryStatus: ");
            String deliveryStatus = reader.readLine();

            // Create and add the new order
            Order newOrder = new Order(orderId, description, customerPhone, warehouseId, driverId, deliveryStatus);
            orderController.createOrder(newOrder);

            // Display updated order list after creation
            System.out.println("\nUpdated Orders:");
            orders = orderController.getAllOrders();
            for (Order order : orders) {
                System.out.println(order.getOrderId() + ", " + order.getDescription() + ", " + order.getCustomerPhone() + ", " + order.getWarehouseId() + ", " + order.getDriverId() + ", " + order.getDeliveryStatus());
            }

            // Update an existing order
            orderController.updateOrder();

            // Display order list after update
            System.out.println("\nUpdated Orders after update:");
            orders = orderController.getAllOrders();
            for (Order order : orders) {
                System.out.println(order.getOrderId() + ", " + order.getDescription() + ", " + order.getCustomerPhone() + ", " + order.getWarehouseId() + ", " + order.getDriverId() + ", " + order.getDeliveryStatus());
            }

            // ===== USER MANAGEMENT SECTION =====
            System.out.println("\n--- User Actions ---");
            
            // Display all existing users
            System.out.println("Current Users (userId, username, password, role, phoneNumber, name):");
            List<User> users = userController.getAllUsers();
            for (User user : users) {
                System.out.println(user.getUserId() + ", " + user.getUsername() + ", " + user.getPassword() + ", " + user.getRole() + ", " + user.getPhoneNumber() + ", " + user.getName());
            }

            // Collect input for creating a new user
            System.out.println("\nEnter new user details:");
            System.out.print("userId: ");
            long userId = Long.parseLong(reader.readLine());
            System.out.print("username: ");
            String username = reader.readLine();
            System.out.print("password: ");
            String password = reader.readLine();
            System.out.print("role: ");
            String role = reader.readLine();
            System.out.print("phoneNumber: ");
            String phoneNumber = reader.readLine();
            System.out.print("name: ");
            String name = reader.readLine();

            // Create and add the new user
            User newUser = new User(userId, username, password, role, phoneNumber, name);
            userController.createUser(newUser);

            // Display updated user list
            System.out.println("\nUpdated Users:");
            users = userController.getAllUsers();
            for (User user : users) {
                System.out.println(user.getUserId() + ", " + user.getUsername() + ", " + user.getPassword() + ", " + user.getRole() + ", " + user.getPhoneNumber() + ", " + user.getName());
            }

            // ===== WAREHOUSE MANAGEMENT SECTION =====
            System.out.println("\n--- Warehouse Actions ---");
            
            // Display all existing warehouses
            System.out.println("Current Warehouses (warehouseId, warehouseName, location, contactPerson, contactPhone):");
            List<Warehouse> warehouses = warehouseController.getAllWarehouses();
            for (Warehouse warehouse : warehouses) {
                System.out.println(warehouse.getWarehouseId() + ", " + warehouse.getWarehouseName() + ", " + warehouse.getLocation() + ", " + warehouse.getContactPerson() + ", " + warehouse.getContactPhone());
            }

            // Collect input for creating a new warehouse
            System.out.println("\nEnter new warehouse details:");
            System.out.print("warehouseId: ");
            long warehouseIdInput = Long.parseLong(reader.readLine());
            System.out.print("warehouseName: ");
            String warehouseName = reader.readLine();
            System.out.print("location: ");
            String location = reader.readLine();
            System.out.print("contactPerson: ");
            String contactPerson = reader.readLine();
            System.out.print("contactPhone: ");
            String contactPhone = reader.readLine();

            // Create and add the new warehouse
            Warehouse newWarehouse = new Warehouse(warehouseIdInput, warehouseName, location, contactPerson, contactPhone);
            warehouseController.createWarehouse(newWarehouse);

            // Display updated warehouse list
            System.out.println("\nUpdated Warehouses:");
            warehouses = warehouseController.getAllWarehouses();
            for (Warehouse warehouse : warehouses) {
                System.out.println(warehouse.getWarehouseId() + ", " + warehouse.getWarehouseName() + ", " + warehouse.getLocation() + ", " + warehouse.getContactPerson() + ", " + warehouse.getContactPhone());
            }

            // ===== AUTHENTICATION SECTION =====
            System.out.println("\n--- Authentication ---");
            
            // Collect login credentials
            System.out.print("Enter username: ");
            String authUsername = reader.readLine();
            System.out.print("Enter password: ");
            String authPassword = reader.readLine();

            // Attempt authentication
            User authenticatedUser = authController.authenticate(authUsername, authPassword);
            if (authenticatedUser != null) {
                System.out.println("\nAuthentication successful: " + authenticatedUser.getUsername());
                // You can add further actions here based on the authenticated user's role.
                System.out.println("Role: " + authenticatedUser.getRole());
            } else {
                System.out.println("\nAuthentication failed.");
            }

        } catch (IOException e) {
            // Handle input/output exceptions
            e.printStackTrace();
        }
    }
}