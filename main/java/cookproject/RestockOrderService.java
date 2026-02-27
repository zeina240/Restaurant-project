package cookproject;


public class RestockOrderService {
    private String lastOrderMessage;
    private String status = "Pending";

    public String createOrder(String item, int quantity) {
        lastOrderMessage = "Order: " + quantity + " units of " + item;
        status = "Generated";
        return lastOrderMessage;
    }

    public void sendOrder() {
        System.out.println("Order sent to supplier: " + lastOrderMessage);
        status = "Sent";
    }

    public void markCompleted() {
        status = "Completed";
    }

    public String getStatus() {
        return status;
    }
}
