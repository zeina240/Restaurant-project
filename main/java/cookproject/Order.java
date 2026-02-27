package cookproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Order {
    private String orderId;
    private String customerId;
    private List<Meal> mealItems;
    private String date; // يمكن تغييره إلى LocalDate إذا تريد، أو إزالته إذا غير مستخدم
    private double totalCost;
    private String paymentMethod;
    private LocalDateTime deadline;
    private String dishName;
    private LocalDateTime time;

    private boolean paid = false;
    private boolean prepared = false;
    private boolean ready = false;
    private boolean served = false;
    private boolean delivered = false;
    private String deliveryStatus = "";

    public Order(String orderId, String customerId, List<Meal> mealItems, String date, double totalCost) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.mealItems = mealItems;
        this.date = date;
        this.totalCost = totalCost;
    }

    // Getters & Setters

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public List<Meal> getMealItems() { return mealItems; }
    public void setMealItems(List<Meal> mealItems) { this.mealItems = mealItems; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }

    public boolean isPrepared() { return prepared; }
    public void setPrepared(boolean prepared) { this.prepared = prepared; }

    public boolean isReady() { return ready; }
    public void setReady(boolean ready) { this.ready = ready; }

    public boolean isServed() { return served; }
    public void setServed(boolean served) { this.served = served; }

    public boolean isDelivered() { return delivered; }
    public void setDelivered(boolean delivered) { this.delivered = delivered; }

    public String getDeliveryStatus() { return deliveryStatus; }
    public void setDeliveryStatus(String deliveryStatus) { this.deliveryStatus = deliveryStatus; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public LocalDateTime getDeadline() { return deadline; }
    public void setDeadline(LocalDateTime deadline) { this.deadline = deadline; }

    public String getDishName() {
        if (dishName != null) return dishName;
        return (mealItems == null || mealItems.isEmpty()) ? "Unknown dish" : mealItems.get(0).getName();
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public LocalDateTime getTime() { return time; }
    public void setTime(LocalDateTime time) { this.time = time; }

    // === Logic ===
    public double calculateTax() {
        return totalCost * 0.15;
    }

    public String getDeadlineFormatted() {
        if (deadline == null) {
            return "No deadline available";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma");
        return deadline.format(formatter);
    }

    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("OrderID: ").append(orderId).append(", Items: ");
        for (Meal m : mealItems) {
            sb.append(m.getName()).append(" ");
        }
        sb.append(", Total: $").append(totalCost);
        return sb.toString();
    }


    public boolean isCompleted() {
        return paid && prepared && ready && served;
    }
}
