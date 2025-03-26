import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import daos.OrderDAO;
import daos.WarehouseDAO;
import entities.Order;
import entities.User;
import entities.Warehouse;

/**
 * AdminDashboardGUI class provides an interface for administrators to manage
 * warehouses and orders in the delivery system.
 */
public class AdminDashboardGUI extends JFrame {

    private WarehouseDAO warehouseDAO;
    private OrderDAO orderDAO;

    /**
     * Constructor initializes the admin dashboard with the logged-in admin user
     * @param adminUser The user with admin privileges
     */
    public AdminDashboardGUI(User adminUser) {
        this.warehouseDAO = new WarehouseDAO();
        this.orderDAO = new OrderDAO();

        // Configure the main frame
        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Welcome Label - displays logged in user's name
        JLabel welcomeLabel = new JLabel("Welcome " + adminUser.getUsername());
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(255, 140, 0));
        welcomeLabel.setBounds(300, 50, 300, 30);
        add(welcomeLabel);

        // Overview Label - provides description text
        JLabel overviewLabel = new JLabel("Here's your quick overview");
        overviewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        overviewLabel.setForeground(new Color(255, 140, 0));
        overviewLabel.setBounds(300, 90, 300, 25);
        add(overviewLabel);

        // Button to view stock for a specific warehouse
        JButton specificStockButton = new JButton("Specific warehouse stock");
        specificStockButton.setBounds(450, 200, 180, 40);
        specificStockButton.setBackground(new Color(255, 204, 204));
        specificStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWarehouseList();
            }
        });
        add(specificStockButton);

        // Button to view all warehouses
        JButton allStockButton = new JButton("All warehouse list");
        allStockButton.setBounds(200, 200, 150, 40);
        allStockButton.setBackground(new Color(255, 204, 204));
        allStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllWarehousesTable();
            }
        });
        add(allStockButton);

        // Button to view all orders in the system
        JButton showAllOrdersButton = new JButton("Show All Orders Details");
        showAllOrdersButton.setBounds(300, 300, 200, 40);
        showAllOrdersButton.setBackground(new Color(204, 255, 204)); // light green
        showAllOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllOrdersDetails();
            }
        });
        add(showAllOrdersButton);

        // Logout button to exit admin dashboard
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(20, 520, 80, 30);
        logoutButton.setForeground(Color.RED);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminDashboardGUI.this, "Logged out.");
                dispose();
                new LoginGUI();
            }
        });
        add(logoutButton);

        // Button to open order editing interface
        JButton editOrdersButton = new JButton("Edit Orders");
        editOrdersButton.setBounds(300, 350, 200, 40); // Place below Show All Orders button
        editOrdersButton.setBackground(new Color(204, 204, 255)); // Light Purple
        editOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEditOrdersWindow();
            }
        });
        add(editOrdersButton);

        setVisible(true);
    }

    /**
     * Displays a dropdown list of all warehouses for selection
     */
    private void showWarehouseList() {
        // Retrieve all warehouses from database
        List<Warehouse> warehouses = warehouseDAO.getAllWarehouses();
        String[] warehouseNames = new String[warehouses.size()];
        for (int i = 0; i < warehouses.size(); i++) {
            warehouseNames[i] = warehouses.get(i).getWarehouseName();
        }

        // Show dropdown selection dialog
        String selectedWarehouse = (String) JOptionPane.showInputDialog(
                AdminDashboardGUI.this,
                "Select a warehouse:",
                "Warehouse Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                warehouseNames,
                warehouseNames[0]);

        // If user selects a warehouse, display its orders
        if (selectedWarehouse != null) {
            // Find warehouse ID based on selected name
            long selectedWarehouseId = warehouses.stream()
                    .filter(w -> w.getWarehouseName().equals(selectedWarehouse))
                    .findFirst()
                    .map(Warehouse::getWarehouseId)
                    .orElse(0L);

            showOrdersByWarehouse(selectedWarehouseId);
        }
    }

    /**
     * Displays all orders associated with a specific warehouse
     * @param warehouseId ID of the warehouse to show orders for
     */
    private void showOrdersByWarehouse(long warehouseId) {
        // Filter orders by warehouse ID
        List<Order> orders = orderDAO.getAllOrders().stream()
                .filter(order -> order.getWarehouseId() == warehouseId)
                .collect(Collectors.toList());

        // Display message if no orders found
        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(AdminDashboardGUI.this, 
                    "No orders found for Warehouse ID " + warehouseId, 
                    "No Data", 
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Create table with order data
        String[] columnNames = {"Order ID", "Description", "Customer Phone", "Warehouse ID", "Driver ID", "Delivery Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Add all orders to the table model
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

        // Display table in a dialog
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 500));
        JOptionPane.showMessageDialog(AdminDashboardGUI.this, scrollPane, 
                "Orders for Warehouse ID " + warehouseId, 
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Displays a table of all warehouses in the system
     */
    private void showAllWarehousesTable() {
        // Retrieve warehouse data
        List<Warehouse> warehouses = warehouseDAO.getAllWarehouses();
        String[] columnNames = {"Warehouse ID", "Name", "Location", "Contact Person", "Contact Phone"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Populate table with warehouse data
        for (Warehouse warehouse : warehouses) {
            model.addRow(new Object[]{
                    warehouse.getWarehouseId(),
                    warehouse.getWarehouseName(),
                    warehouse.getLocation(),
                    warehouse.getContactPerson(),
                    warehouse.getContactPhone()
            });
        }

        // Create scrollable table view
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 300));
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        // Add "Show All" button if there are many warehouses
        if (warehouses.size() > 5) {
            JButton showAllButton = new JButton("Show All");
            showAllButton.addActionListener(e -> showAllWarehousesInScrollableTable(warehouses));
            panel.add(showAllButton);
        }

        // Display in dialog
        JOptionPane.showMessageDialog(AdminDashboardGUI.this, panel, "All Warehouses", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Shows all warehouses in a larger scrollable table view
     * @param warehouses List of warehouses to display
     */
    private void showAllWarehousesInScrollableTable(List<Warehouse> warehouses) {
        String[] columnNames = {"Warehouse ID", "Name", "Location", "Contact Person", "Contact Phone"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Add all warehouses to model
        for (Warehouse warehouse : warehouses) {
            model.addRow(new Object[]{
                    warehouse.getWarehouseId(),
                    warehouse.getWarehouseName(),
                    warehouse.getLocation(),
                    warehouse.getContactPerson(),
                    warehouse.getContactPhone()
            });
        }

        // Create larger scrollable view
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 500));
        JOptionPane.showMessageDialog(AdminDashboardGUI.this, scrollPane, "All Warehouses (Scrollable)", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Displays a table with details of all orders in the system
     */
    private void showAllOrdersDetails() {
        // Get all orders from database
        List<Order> orders = orderDAO.getAllOrders();
        String[] columnNames = {"Order ID", "Description", "Customer Phone", "Warehouse ID", "Driver ID", "Delivery Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Add each order to the table
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

        // Display orders in scrollable table
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 500));
        JOptionPane.showMessageDialog(AdminDashboardGUI.this, scrollPane, "All Orders Details", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Opens a new window for editing order details
     */
    private void openEditOrdersWindow() {
        // Create and display the order editing interface
        EditOrdersGUI editOrdersGUI = new EditOrdersGUI(orderDAO);
        editOrdersGUI.setVisible(true);
    }
}