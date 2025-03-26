import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import daos.OrderDAO;
import entities.Order;

public class EditOrdersGUI extends JFrame {

    private OrderDAO orderDAO;
    private JTable table;
    private DefaultTableModel model;

    // Constructor to initialize the GUI and load orders
    public EditOrdersGUI(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;

        // Set up the main frame
        setTitle("Edit Orders");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Add a label for the GUI title
        JLabel editOrdersLabel = new JLabel("Edit Orders Across Warehouses");
        editOrdersLabel.setFont(new Font("Arial", Font.BOLD, 20));
        editOrdersLabel.setBounds(20, 20, 300, 30);
        add(editOrdersLabel);

        // Fetch all orders from the database
        List<Order> orders = orderDAO.getAllOrders();

        // Define table columns
        String[] columnNames = {"Order ID", "Description", "Customer Phone", "Warehouse ID", "Driver ID", "Delivery Status"};
        model = new DefaultTableModel(columnNames, 0);

        // Populate the table with order data
        for (Order order : orders) {
            model.addRow(new Object[]{
                    order.getOrderId(),
                    order.getDescription(),
                    order.getCustomerPhone(),
                    order.getWarehouseId(),
                    order.getDriverId(),
                    order.getDeliveryStatus()
            });
        }

        // Create a table to display orders
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 60, 760, 400);
        add(scrollPane);

        // Add a button to save changes made to the orders
        JButton saveChangesButton = new JButton("Save Changes");
        saveChangesButton.setBounds(650, 480, 130, 30);
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges(); // Save changes when the button is clicked
            }
        });
        add(saveChangesButton);
    }

    // Method to save changes made to the orders in the table
    private void saveChanges() {
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            // Retrieve updated values from the table
            long orderId = (long) model.getValueAt(i, 0);
            String description = (String) model.getValueAt(i, 1);
            String customerPhone = (String) model.getValueAt(i, 2);
            long warehouseId = (long) model.getValueAt(i, 3);
            long driverId = (long) model.getValueAt(i, 4);
            String deliveryStatus = (String) model.getValueAt(i, 5);

            // Fetch the corresponding order from the database
            Order order = orderDAO.getOrderById(orderId);
            if (order != null) {
                // Update the order object with new values
                order.setDescription(description);
                order.setCustomerPhone(customerPhone);
                order.setWarehouseId(warehouseId);
                order.setDriverId(driverId);
                order.setDeliveryStatus(deliveryStatus);

                // Save the updated order back to the database
                orderDAO.updateOrder(order);
            }
        }
        // Show a confirmation message
        JOptionPane.showMessageDialog(this, "Changes saved successfully.");
    }
}