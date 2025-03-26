package services;

import daos.WarehouseDAO;
import entities.Warehouse;
import java.io.IOException;
import java.util.List;

/**
 * Service class for managing warehouse-related operations.
 * This class acts as a bridge between the controller and DAO layers,
 * providing business logic for warehouse management.
 */
public class WarehouseService {
    private WarehouseDAO warehouseDAO = new WarehouseDAO();

    /**
     * Retrieves all warehouses from the data source.
     * Delegates the call to the DAO layer.
     * 
     * @return List of all warehouses.
     * @throws IOException if an I/O error occurs.
     */
    public List<Warehouse> getAllWarehouses() throws IOException {
        return warehouseDAO.getAllWarehouses();
    }

    /**
     * Saves a new warehouse to the data source.
     * Delegates the call to the DAO layer.
     * 
     * @param warehouse The warehouse to be saved.
     * @throws IOException if an I/O error occurs.
     */
    public void saveWarehouse(Warehouse warehouse) throws IOException {
        warehouseDAO.saveWarehouse(warehouse);
    }
}