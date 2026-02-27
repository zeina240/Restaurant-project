package cookproject;

public class SupplyOrder {
    private String orderId;
    private String supplierId; // ربط الطلب بالمزود عن طريق المعرف
    private String productName;
    private int quantity;
    private String deliveryDate;
    private String status; // مثلاً: "Pending", "Delivered", "Cancelled"

    public SupplyOrder(String orderId, String supplierId, String productName,
                       int quantity, String deliveryDate, String status) {
        this.orderId = orderId;
        this.supplierId = supplierId;
        this.productName = productName;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    // getters
    public String getOrderId() { return orderId; }
    public String getSupplierId() { return supplierId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public String getDeliveryDate() { return deliveryDate; }
    public String getStatus() { return status; }

    // setters
    public void setStatus(String status) { this.status = status; }
}
