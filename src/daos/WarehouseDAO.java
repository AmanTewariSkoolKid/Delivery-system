package daos;

import entities.Warehouse;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAO {
    private List<Warehouse> warehouses = new ArrayList<>();

    public WarehouseDAO() {
        populateWarehouses();
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouses;
    }

    public void saveWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
    }

    private void populateWarehouses() {
        warehouses.add(new Warehouse(1L, "Warehouse A", "Location A", "Contact A", "100-200-3000"));
        warehouses.add(new Warehouse(2L, "Warehouse B", "Location B", "Contact B", "400-500-6000"));
        warehouses.add(new Warehouse(3L, "Warehouse C", "Location C", "Contact C", "700-800-9000"));
    }
}