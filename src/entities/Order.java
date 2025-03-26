package entities;

/**
 * Represents an order in the delivery system.
 * Contains information about the order, customer, warehouse, driver, and delivery status.
 */
public class Order {
    // Unique identifier for the order
    private Long orderId;
    // Description of the order contents
    private String description;
    // Contact phone number of the customer
    private String customerPhone;
    // ID of the warehouse where the order is processed
    private Long warehouseId;
    // ID of the driver assigned to deliver the order
    private Long driverId;
    // Current status of the delivery (e.g., "pending", "in transit", "delivered")
    private String deliveryStatus;

    /**
     * Default constructor for creating an empty order.
     */
    public Order() {}

    /**
     * Constructor for creating a fully initialized order.
     * @param orderId Unique identifier for the order
     * @param description Description of the order contents
     * @param customerPhone Contact phone number of the customer
     * @param warehouseId ID of the warehouse processing the order
     * @param driverId ID of the driver delivering the order
     * @param deliveryStatus Current status of the delivery
     */
    public Order(Long orderId, String description, String customerPhone, Long warehouseId, Long driverId, String deliveryStatus) {
        this.orderId = orderId;
        this.description = description;
        this.customerPhone = customerPhone;
        this.warehouseId = warehouseId;
        this.driverId = driverId;
        this.deliveryStatus = deliveryStatus;
    }

    /**
     * @return The order's unique identifier
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId The order identifier to set
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return The order's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The customer's phone number
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * @param customerPhone The customer phone number to set
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * @return The warehouse's ID
     */
    public Long getWarehouseId() {
        return warehouseId;
    }

    /**
     * @param warehouseId The warehouse ID to set
     */
    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * @return The driver's ID
     */
    public Long getDriverId() {
        return driverId;
    }

    /**
     * @param driverId The driver ID to set
     */
    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    /**
     * @return The current delivery status
     */
    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    /**
     * @param deliveryStatus The delivery status to set
     */
    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}