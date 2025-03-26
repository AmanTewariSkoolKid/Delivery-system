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

public class AdminDashboardGUI extends JFrame {

    private User adminUser;
    private WarehouseDAO warehouseDAO;
    private OrderDAO orderDAO;

    public AdminDashboardGUI(User adminUser) {
        this.adminUser = adminUser;
        this.warehouseDAO = new WarehouseDAO();
        this.orderDAO = new OrderDAO();

        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome " + adminUser.getUsername());
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(255, 140, 0));
        welcomeLabel.setBounds(300, 50, 300, 30);
        add(welcomeLabel);

        // Overview Label
        JLabel overviewLabel = new JLabel("Here's your quick overview");
        overviewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        overviewLabel.setForeground(new Color(255, 140, 0));
        overviewLabel.setBounds(300, 90, 300, 25);
        add(overviewLabel);

        // Specific Warehouse Stock Button
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

        // All Warehouse Stock Button
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

        // Show All Orders Button
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

        // Logout Button
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

        // Edit Orders Button
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

    private void showWarehouseList() {
        List<Warehouse> warehouses = warehouseDAO.getAllWarehouses();
        String[] warehouseNames = new String[warehouses.size()];
        for (int i = 0; i < warehouses.size(); i++) {
            warehouseNames[i] = warehouses.get(i).getWarehouseName();
        }

        String selectedWarehouse = (String) JOptionPane.showInputDialog(
                AdminDashboardGUI.this,
                "Select a warehouse:",
                "Warehouse Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                warehouseNames,
                warehouseNames[0]);

        if (selectedWarehouse != null) {
            long selectedWarehouseId = warehouses.stream()
                    .filter(w -> w.getWarehouseName().equals(selectedWarehouse))
                    .findFirst()
                    .map(Warehouse::getWarehouseId)
                    .orElse(0L);

            showOrdersByWarehouse(selectedWarehouseId);
        }
    }

    private void showOrdersByWarehouse(long warehouseId) {
        List<Order> orders = orderDAO.getAllOrders().stream()
                .filter(order -> order.getWarehouseId() == warehouseId)
                .collect(Collectors.toList());

        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(AdminDashboardGUI.this, 
                    "No orders found for Warehouse ID " + warehouseId, 
                    "No Data", 
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] columnNames = {"Order ID", "Description", "Customer Phone", "Warehouse ID", "Driver ID", "Delivery Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

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

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 500));
        JOptionPane.showMessageDialog(AdminDashboardGUI.this, scrollPane, 
                "Orders for Warehouse ID " + warehouseId, 
                JOptionPane.PLAIN_MESSAGE);
    }

    private void showAllWarehousesTable() {
        List<Warehouse> warehouses = warehouseDAO.getAllWarehouses();
        String[] columnNames = {"Warehouse ID", "Name", "Location", "Contact Person", "Contact Phone"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Warehouse warehouse : warehouses) {
            model.addRow(new Object[]{
                    warehouse.getWarehouseId(),
                    warehouse.getWarehouseName(),
                    warehouse.getLocation(),
                    warehouse.getContactPerson(),
                    warehouse.getContactPhone()
            });
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 300));
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        if (warehouses.size() > 5) {
            JButton showAllButton = new JButton("Show All");
            showAllButton.addActionListener(e -> showAllWarehousesInScrollableTable(warehouses));
            panel.add(showAllButton);
        }

        JOptionPane.showMessageDialog(AdminDashboardGUI.this, panel, "All Warehouses", JOptionPane.PLAIN_MESSAGE);
    }

    private void showAllWarehousesInScrollableTable(List<Warehouse> warehouses) {
        String[] columnNames = {"Warehouse ID", "Name", "Location", "Contact Person", "Contact Phone"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Warehouse warehouse : warehouses) {
            model.addRow(new Object[]{
                    warehouse.getWarehouseId(),
                    warehouse.getWarehouseName(),
                    warehouse.getLocation(),
                    warehouse.getContactPerson(),
                    warehouse.getContactPhone()
            });
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 500));
        JOptionPane.showMessageDialog(AdminDashboardGUI.this, scrollPane, "All Warehouses (Scrollable)", JOptionPane.PLAIN_MESSAGE);
    }

    private void showAllOrdersDetails() {
        List<Order> orders = orderDAO.getAllOrders();
        String[] columnNames = {"Order ID", "Description", "Customer Phone", "Warehouse ID", "Driver ID", "Delivery Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

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

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 500));
        JOptionPane.showMessageDialog(AdminDashboardGUI.this, scrollPane, "All Orders Details", JOptionPane.PLAIN_MESSAGE);
    }

    private void openEditOrdersWindow() {
        // Create an instance of EditOrdersGUI
        EditOrdersGUI editOrdersGUI = new EditOrdersGUI(orderDAO);
        editOrdersGUI.setVisible(true); // Make the EditOrdersGUI window visible
    }
}