package daos;

import entities.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private List<Order> orders = new ArrayList<>();

    public OrderDAO() {
        populateOrders();
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public void saveOrder(Order order) {
        orders.add(order);
    }

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
        orders.add(new Order(3L, "Tablet", "555-123-4567", 1L, 3L, "Pending"));
        orders.add(new Order(4L, "Desktop", "123-456-7890", 2L, 4L, "Delivered"));
        orders.add(new Order(5L, "Smartwatch", "123-456-7890", 1L, 5L, "Delivered"));
        orders.add(new Order(6L, "Headphones", "123-456-7890", 2L, 6L, "Delivered"));
        orders.add(new Order(7L, "Keyboard", "123-456-7890", 1L, 7L, "Delivered"));
        orders.add(new Order(8L, "Mouse", "123-456-7890", 2L, 8L, "Delivered"));
        orders.add(new Order(9L, "Monitor", "123-456-7890", 1L, 9L, "Delivered"));
        orders.add(new Order(10L, "Printer", "123-456-7890", 2L, 10L, "Delivered"));
    }
}