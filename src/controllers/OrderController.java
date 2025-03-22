package controllers;

import entities.Order;
import services.OrderService;

import java.io.IOException;
import java.util.List;

public class OrderController {
    private OrderService orderService = new OrderService();

    public List<Order> getAllOrders() throws IOException {
        return orderService.getAllOrders();
    }

    public void createOrder(Order order) throws IOException {
        orderService.saveOrder(order);
    }
    public void updateOrder(Order order)throws IOException{
        orderService.updateOrder(order);
    }
}