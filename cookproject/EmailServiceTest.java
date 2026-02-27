package cookproject;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EmailServiceTest {

    @Test
    public void testSendInvoiceReturnsTrue() {
        EmailService emailService = new EmailService();

        // إنشاء Invoice وتعبئته ببيانات
        Invoice invoice = new Invoice();
        invoice.setOrderDetails("Order #123: 2 Pizzas");
        invoice.setTotalAmount(45.5);
        invoice.setTaxAmount(3.5);
        invoice.setPaymentMethod("Credit Card");

        String email = "customer@example.com";

        boolean result = emailService.sendInvoice(invoice, email);

        assertTrue(result, "sendInvoice should return true");
    }
}
