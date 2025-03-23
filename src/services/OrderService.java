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
    public void updateOrder(Order order)throws IOException{
        orderDAO.updateOrder(order);
    }
}