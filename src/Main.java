// src/Main.java
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

public class Main {
    public static void main(String[] args) {
        OrderController orderController = new OrderController();
        UserController userController = new UserController();
        WarehouseController warehouseController = new WarehouseController();
        AuthController authController = new AuthController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            // Example of order actions with user input.
            System.out.println("--- Order Actions ---");
            System.out.println("Current Orders (orderId, description, customerPhone, warehouseId, driverId, deliveryStatus):");
            List<Order> orders = orderController.getAllOrders();
            for (Order order : orders) {
                System.out.println(order.getOrderId() + ", " + order.getDescription() + ", " + order.getCustomerPhone() + ", " + order.getWarehouseId() + ", " + order.getDriverId() + ", " + order.getDeliveryStatus());
            }

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

            Order newOrder = new Order(orderId, description, customerPhone, warehouseId, driverId, deliveryStatus);
            orderController.createOrder(newOrder);

            System.out.println("\nUpdated Orders:");
            orders = orderController.getAllOrders();
            for (Order order : orders) {
                System.out.println(order.getOrderId() + ", " + order.getDescription() + ", " + order.getCustomerPhone() + ", " + order.getWarehouseId() + ", " + order.getDriverId() + ", " + order.getDeliveryStatus());
            }

            orderController.updateOrder(); // Corrected to call updateOrder without arguments

            System.out.println("\nUpdated Orders after update:");
            orders = orderController.getAllOrders();
            for (Order order : orders) {
                System.out.println(order.getOrderId() + ", " + order.getDescription() + ", " + order.getCustomerPhone() + ", " + order.getWarehouseId() + ", " + order.getDriverId() + ", " + order.getDeliveryStatus());
            }

            // Example of user actions with user input.
            System.out.println("\n--- User Actions ---");
            System.out.println("Current Users (userId, username, password, role, phoneNumber, name):");
            List<User> users = userController.getAllUsers();
            for (User user : users) {
                System.out.println(user.getUserId() + ", " + user.getUsername() + ", " + user.getPassword() + ", " + user.getRole() + ", " + user.getPhoneNumber() + ", " + user.getName());
            }

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

            User newUser = new User(userId, username, password, role, phoneNumber, name);
            userController.createUser(newUser);

            System.out.println("\nUpdated Users:");
            users = userController.getAllUsers();
            for (User user : users) {
                System.out.println(user.getUserId() + ", " + user.getUsername() + ", " + user.getPassword() + ", " + user.getRole() + ", " + user.getPhoneNumber() + ", " + user.getName());
            }

            // Example of warehouse actions with user input.
            System.out.println("\n--- Warehouse Actions ---");
            System.out.println("Current Warehouses (warehouseId, warehouseName, location, contactPerson, contactPhone):");
            List<Warehouse> warehouses = warehouseController.getAllWarehouses();
            for (Warehouse warehouse : warehouses) {
                System.out.println(warehouse.getWarehouseId() + ", " + warehouse.getWarehouseName() + ", " + warehouse.getLocation() + ", " + warehouse.getContactPerson() + ", " + warehouse.getContactPhone());
            }

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

            Warehouse newWarehouse = new Warehouse(warehouseIdInput, warehouseName, location, contactPerson, contactPhone);
            warehouseController.createWarehouse(newWarehouse);

            System.out.println("\nUpdated Warehouses:");
            warehouses = warehouseController.getAllWarehouses();
            for (Warehouse warehouse : warehouses) {
                System.out.println(warehouse.getWarehouseId() + ", " + warehouse.getWarehouseName() + ", " + warehouse.getLocation() + ", " + warehouse.getContactPerson() + ", " + warehouse.getContactPhone());
            }

            // Example of authentication with user input.
            System.out.println("\n--- Authentication ---");
            System.out.print("Enter username: ");
            String authUsername = reader.readLine();
            System.out.print("Enter password: ");
            String authPassword = reader.readLine();

            User authenticatedUser = authController.authenticate(authUsername, authPassword);
            if (authenticatedUser != null) {
                System.out.println("\nAuthentication successful: " + authenticatedUser.getUsername());
            } else {
                System.out.println("\nAuthentication failed.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}