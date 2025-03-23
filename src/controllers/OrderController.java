package controllers;

import entities.Order;
import services.OrderService;
import java.util.List;
import java.util.Scanner;

public class OrderController {
    private OrderService orderService = new OrderService();
    private Scanner scanner = new Scanner(System.in);

    public void updateOrder() {
        System.out.println("Enter order ID to update:");
        Long orderId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter new description (leave blank to keep current):");
        String description = scanner.nextLine();

        System.out.println("Enter new customer phone (leave blank to keep current):");
        String customerPhone = scanner.nextLine();

        System.out.println("Enter new warehouse ID (leave blank to keep current):");
        Long warehouseId = null;
        String warehouseInput = scanner.nextLine();
        if (!warehouseInput.isEmpty()) {
            warehouseId = Long.parseLong(warehouseInput);
        }

        System.out.println("Enter new user ID (leave blank to keep current):");
        Long userId = null;
        String userInput = scanner.nextLine();
        if (!userInput.isEmpty()) {
            userId = Long.parseLong(userInput);
        }

        System.out.println("Enter new status (leave blank to keep current):");
        String status = scanner.nextLine();

        Order updatedOrder = new Order(orderId, description, customerPhone, warehouseId, userId, status);
        orderService.updateOrder(updatedOrder);
        System.out.println("Order updated successfully.");
    }
}