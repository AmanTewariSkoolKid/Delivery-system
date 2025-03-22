package services;

import daos.WarehouseDAO;
import entities.Warehouse;
import java.io.IOException;
import java.util.List;

public class WarehouseService {
    private WarehouseDAO warehouseDAO = new WarehouseDAO();

    public List<Warehouse> getAllWarehouses() throws IOException {
        return warehouseDAO.getAllWarehouses();
    }
    public void saveWarehouse(Warehouse warehouse)throws IOException{
        warehouseDAO.saveWarehouse(warehouse);
    }
}