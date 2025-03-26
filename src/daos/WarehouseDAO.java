    package daos;

    import entities.Warehouse;
    import java.util.ArrayList;
    import java.util.List;

    /**
     * Data Access Object for Warehouse entities.
     * Handles CRUD operations for warehouse data.
     */
    public class WarehouseDAO {
        // List to store warehouse objects in memory
        private List<Warehouse> warehouses = new ArrayList<>();

        /**
         * Constructor initializes the warehouse list with sample data.
         */
        public WarehouseDAO() {
            populateWarehouses();
        }

        /**
         * Retrieves all warehouses.
         * @return List of all warehouses
         */
        public List<Warehouse> getAllWarehouses() {
            return warehouses;
        }

        /**
         * Saves a new warehouse to the list.
         * @param warehouse The warehouse object to save
         */
        public void saveWarehouse(Warehouse warehouse) {
            warehouses.add(warehouse);
        }

        /**
         * Populates the warehouse list with initial sample data.
         * This method is called during DAO initialization.
         */
        private void populateWarehouses() {
            // Add sample warehouses with different IDs, names, locations, contacts, and phone numbers
            warehouses.add(new Warehouse(1L, "Warehouse A", "Location A", "Contact A", "100-200-3000"));
            warehouses.add(new Warehouse(2L, "Warehouse B", "Location B", "Contact B", "400-500-6000"));
            warehouses.add(new Warehouse(3L, "Warehouse C", "Location C", "Contact C", "700-800-9000"));
        }
    }