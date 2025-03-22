package controllers;

import entities.Warehouse;
import services.WarehouseService;

import java.io.IOException;
import java.util.List;

public class WarehouseController {
    private WarehouseService warehouseService = new WarehouseService();

    public List<Warehouse> getAllWarehouses() throws IOException {
        return warehouseService.getAllWarehouses();
    }
    public void createWarehouse(Warehouse warehouse) throws IOException{
        warehouseService.saveWarehouse(warehouse);
    }
}