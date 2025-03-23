// src/services/OrderService.java
package services;

import daos.OrderDAO;
import entities.Order;
import java.io.IOException;
import java.util.List;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();

    public List<Order> getAllOrders() throws IOException {
        return orderDAO.getAllOrders();
    }

    public void saveOrder(Order order) throws IOException {
        orderDAO.saveOrder(order);
    }

    public void updateOrder(Order updatedOrder) throws IOException {
        Order existingOrder = null;
        for (Order order : orderDAO.getAllOrders()) {
            if (order.getOrderId().equals(updatedOrder.getOrderId())) {
                existingOrder = order;
                break;
            }
        }

        if (existingOrder != null) {
            if (updatedOrder.getDescription() != null && !updatedOrder.getDescription().trim().isEmpty()) {
                existingOrder.setDescription(updatedOrder.getDescription());
            }
            if (updatedOrder.getCustomerPhone() != null && !updatedOrder.getCustomerPhone().trim().isEmpty()) {
                existingOrder.setCustomerPhone(updatedOrder.getCustomerPhone());
            }
            if (updatedOrder.getWarehouseId() != null) {
                existingOrder.setWarehouseId(updatedOrder.getWarehouseId());
            }
            if (updatedOrder.getDriverId() != null) {
                existingOrder.setDriverId(updatedOrder.getDriverId());
            }
            if (updatedOrder.getDeliveryStatus() != null && !updatedOrder.getDeliveryStatus().trim().isEmpty()) {
                existingOrder.setDeliveryStatus(updatedOrder.getDeliveryStatus());
            }
            orderDAO.updateOrder(existingOrder);
        }
    }
}