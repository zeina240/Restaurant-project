package cookproject;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InvoiceServiceTest {

    @Test
    public void testGenerateInvoice_withValidOrder_returnsCorrectInvoice() {
        // Arrange
        // إنشاء مكونات مع اسم ومستوى المخزون (يمكن تعديلها حسب الحاجة)
        Ingredient cheese = new Ingredient("Cheese", 10);
        Ingredient bread = new Ingredient("Bread", 20);

        // إنشاء وجبات مع مكونات وأسعار
        Meal sandwich = new Meal("Sandwich", Arrays.asList(cheese, bread), 15.0);
        Meal salad = new Meal("Salad", Arrays.asList(new Ingredient("Lettuce", 5)), 10.0);

        List<Meal> meals = Arrays.asList(sandwich, salad);

        // إنشاء طلب Order مع الوجبات، التاريخ، والتكلفة الإجمالية
        Order order = new Order("ORD001", "CUST001", meals, "2025-05-22", 25.0);
        order.setPaymentMethod("CASH");

        InvoiceService invoiceService = new InvoiceService();

        // Act
        Invoice invoice = invoiceService.generateInvoice(order);

        // Assert
        assertNotNull(invoice);
        assertEquals(order.getDetails(), invoice.getOrderDetails());
        assertEquals(order.getTotalCost(), invoice.getTotalAmount(), 0.001);
        assertEquals(order.calculateTax(), invoice.getTaxAmount(), 0.001);
        assertEquals(order.getPaymentMethod(), invoice.getPaymentMethod());
        assertTrue(invoice.containsDetails());
    }
}
