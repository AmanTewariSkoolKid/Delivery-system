package entities;

public class Order {
    private Long orderId;
    private String description;
    private String customerPhone;
    private Long warehouseId;
    private Long driverId;
    private String deliveryStatus;

    public Order() {} // Default constructor

    public Order(Long orderId, String description, String customerPhone, Long warehouseId, Long driverId, String deliveryStatus) {
        this.orderId = orderId;
        this.description = description;
        this.customerPhone = customerPhone;
        this.warehouseId = warehouseId;
        this.driverId = driverId;
        this.deliveryStatus = deliveryStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}