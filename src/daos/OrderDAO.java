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
    }
}