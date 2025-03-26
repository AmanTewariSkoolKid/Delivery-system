package daos;

import entities.Order;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for Order entities.
 * Provides methods to perform CRUD operations on Order objects and
 * manages the in-memory storage of orders.
 */
public class OrderDAO {
    // In-memory collection to store Order objects
    private List<Order> orders = new ArrayList<>();

    /**
     * Constructor that initializes the DAO with sample order data.
     */
    public OrderDAO() {
        populateOrders();
    }

    /**
     * Retrieves all orders from the data store.
     * 
     * @return List of all Order objects
     */
    public List<Order> getAllOrders() {
        return orders;
    }

    /**
     * Saves a new order to the data store.
     * 
     * @param order The Order object to be saved
     */
    public void saveOrder(Order order) {
        orders.add(order);
    }

    /**
     * Updates an existing order in the data store.
     * Finds the order with matching orderId and replaces it.
     * 
     * @param order The Order object with updated information
     */
    public void updateOrder(Order order) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderId().equals(order.getOrderId())) {
                orders.set(i, order);
                break;
            }
        }
    }

    /**
     * Retrieves an order by its ID.
     * 
     * @param orderId The ID of the order to retrieve
     * @return The Order object if found, null otherwise
     */
    public Order getOrderById(Long orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) { // Using equals() for proper Long comparison
                return order;
            }
        }
        return null;
    }

    /**
     * Populates the data store with sample order data.
     * This method is used for demonstration and testing purposes.
     */
    private void populateOrders() {
        // Adding sample orders with different statuses and information
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