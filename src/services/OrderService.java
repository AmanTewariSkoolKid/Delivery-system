package services;

import daos.OrderDAO;
import entities.Order;
import java.util.List;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();

    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    public void saveOrder(Order order) {
        orderDAO.saveOrder(order);
    }

    public void updateOrder(Order updatedOrder) {
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
            if (updatedOrder.getUserId() != null) {
                existingOrder.setUserId(updatedOrder.getUserId());
            }
            if (updatedOrder.getStatus() != null && !updatedOrder.getStatus().trim().isEmpty()) {
                existingOrder.setStatus(updatedOrder.getStatus());
            }
            orderDAO.updateOrder(existingOrder);
        }
    }
}