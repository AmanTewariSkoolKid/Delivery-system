// src/services/OrderService.java
package services;

import daos.OrderDAO;
import entities.Order;
import java.io.IOException;
import java.util.List;

/**
 * Service class for managing order-related operations.
 * This class acts as a bridge between the controller and DAO layers,
 * providing business logic for order management.
 */
public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();

    /**
     * Retrieves all orders from the data source.
     * Delegates the call to the DAO layer.
     * 
     * @return List of all orders.
     * @throws IOException if an I/O error occurs.
     */
    public List<Order> getAllOrders() throws IOException {
        return orderDAO.getAllOrders();
    }

    /**
     * Saves a new order to the data source.
     * Delegates the call to the DAO layer.
     * 
     * @param order The order to be saved.
     * @throws IOException if an I/O error occurs.
     */
    public void saveOrder(Order order) throws IOException {
        orderDAO.saveOrder(order);
    }

    /**
     * Updates an existing order with new details.
     * Finds the order by ID and updates its fields if provided.
     * 
     * @param updatedOrder The order with updated details.
     * @throws IOException if an I/O error occurs.
     */
    public void updateOrder(Order updatedOrder) throws IOException {
        Order existingOrder = null;
        for (Order order : orderDAO.getAllOrders()) {
            if (order.getOrderId().equals(updatedOrder.getOrderId())) {
                existingOrder = order;
                break;
            }
        }

        if (existingOrder != null) {
            // Update fields only if new values are provided
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
            // Save the updated order back to the DAO
            orderDAO.updateOrder(existingOrder);
        }
    }
}