package cookproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;
    private Meal meal1;
    private Meal meal2;

    @BeforeEach
    void setUp() {
        meal1 = new Meal("Pasta", Collections.emptyList(), 10.0);
        meal2 = new Meal("Salad", Collections.emptyList(), 5.0);
        List<Meal> meals = Arrays.asList(meal1, meal2);
        order = new Order("ORD123", "CUST1", meals, "2025-05-22", 15.0);
    }

    @Test
    void testCalculateTax() {
        assertEquals(2.25, order.calculateTax(), 0.001);
    }

    @Test
    void testGetDishNameFallback() {
        assertEquals("Pasta", order.getDishName());
    }

    @Test
    void testGetDishNameOverride() {
        order.setDishName("Special Dish");
        assertEquals("Special Dish", order.getDishName());
    }

    @Test
    void testGetDeadlineFormatted() {
        LocalDateTime deadline = LocalDateTime.of(2025, 5, 30, 17, 30);
        order.setDeadline(deadline);
        assertEquals("2025-05-30 05:30PM", order.getDeadlineFormatted());
    }

    @Test
    void testGetDeadlineFormattedNull() {
        order.setDeadline(null);
        assertEquals("No deadline available", order.getDeadlineFormatted());
    }

    @Test
    void testGetDetails() {
        String details = order.getDetails();
        assertTrue(details.contains("ORD123"));
        assertTrue(details.contains("Pasta"));
        assertTrue(details.contains("Salad"));
        assertTrue(details.contains("$15.0"));
    }

    @Test
    void testOrderCompletionStatus() {
        order.setPaid(true);
        order.setPrepared(true);
        order.setReady(true);
        order.setServed(true);
        assertTrue(order.isCompleted());
    }

    @Test
    void testOrderIncompleteStatus() {
        order.setPaid(true);
        order.setPrepared(false);
        order.setReady(true);
        order.setServed(true);
        assertFalse(order.isCompleted());
    }

    @Test
    void testGettersAndSetters() {
        order.setOrderId("ORD999");
        assertEquals("ORD999", order.getOrderId());

        order.setCustomerId("CUST2");
        assertEquals("CUST2", order.getCustomerId());

        order.setDate("2025-06-01");
        assertEquals("2025-06-01", order.getDate());

        order.setTotalCost(20.0);
        assertEquals(20.0, order.getTotalCost());

        order.setPaymentMethod("Cash");
        assertEquals("Cash", order.getPaymentMethod());

        LocalDateTime now = LocalDateTime.of(2025, 6, 1, 12, 0);
        order.setTime(now);
        assertEquals(now, order.getTime());

        order.setDeliveryStatus("Delivered");
        assertEquals("Delivered", order.getDeliveryStatus());

        order.setMealItems(Collections.singletonList(meal1));
        assertEquals(1, order.getMealItems().size());

        order.setPaid(true);
        order.setPrepared(true);
        order.setReady(true);
        order.setServed(true);
        order.setDelivered(true);
        assertTrue(order.isDelivered());
    }
}
