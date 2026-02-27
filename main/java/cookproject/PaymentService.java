package cookproject;

public class PaymentService {
    public boolean processPayment(Order order, String method) {
        order.setPaid(true);
        order.setPaymentMethod(method);
        return true;
    }
}
