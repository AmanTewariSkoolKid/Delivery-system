// src/controllers/OrderController.java
package controllers;

import entities.Order;
import services.OrderService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class OrderController {
    private OrderService orderService = new OrderService();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public List<Order> getAllOrders() throws IOException {
        return orderService.getAllOrders();
    }

    public void createOrder(Order order) throws IOException {
        orderService.saveOrder(order);
    }

    public void updateOrder() throws IOException {
        System.out.print("orderId to update: ");
        long orderId = Long.parseLong(reader.readLine());

        System.out.print("description (leave blank to keep current): ");
        String description = reader.readLine();

        System.out.print("customerPhone (leave blank to keep current): ");
        String customerPhone = reader.readLine();

        System.out.print("warehouseId (leave blank to keep current): ");
        String warehouseIdInput = reader.readLine();
        Long warehouseId = warehouseIdInput.isEmpty() ? null : Long.parseLong(warehouseIdInput);

        System.out.print("driverId (leave blank to keep current): ");
        String driverIdInput = reader.readLine();
        Long driverId = driverIdInput.isEmpty() ? null : Long.parseLong(driverIdInput);

        System.out.print("deliveryStatus (1: delivered, 2: in transit, 3: pending, leave blank to keep current): ");
        String deliveryStatusInput = reader.readLine();
        String deliveryStatus = deliveryStatusInput.isEmpty() ? null : getDeliveryStatus(deliveryStatusInput);

        if (deliveryStatus == null && !deliveryStatusInput.isEmpty()){
            System.out.println("Invalid delivery status. Please use 1, 2 or 3.");
            return;
        }

        Order updatedOrder = new Order(orderId, description, customerPhone, warehouseId, driverId, deliveryStatus);
        orderService.updateOrder(updatedOrder);
        System.out.println("Order updated successfully.");
    }

    private String getDeliveryStatus(String input) {
        switch (input) {
            case "1":
                return "delivered";
            case "2":
                return "in transit";
            case "3":
                return "pending";
            default:
                return null;
        }
    }
}