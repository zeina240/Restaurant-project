package cookproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientStockManagerTest {

    private IngredientStockManager stockManager;

    @BeforeEach
    void setUp() {
        stockManager = new IngredientStockManager();
    }

    @Test
    void testLoginSuccess() {
        stockManager.login("manager", "password");
        assertTrue(stockManager.viewStockOverview());
    }

    @Test
    void testLoginFailure() {
        stockManager.login("wrong", "credentials");
        assertFalse(stockManager.viewStockOverview());
    }

    @Test
    void testSetIngredientStockLevel() {
        stockManager.setIngredientStockLevel("Onions", 25);
        Ingredient ing = stockManager.getInventoryService().getAllIngredients()
                .stream().filter(i -> i.getName().equals("Onions")).findFirst().orElse(null);
        assertNotNull(ing);
        assertEquals(25, ing.getStockLevel());
    }

    @Test
    void testCheckLowStockLevelShouldTriggerAlert() {
        // Reducing stock level below 5 to trigger alert
        stockManager.setIngredientStockLevel("Onions", 2);
        boolean result = stockManager.checkLowStockLevel();
        assertTrue(result);
    }

    @Test
    void testCheckLowStockLevelNoAlertIfEnoughStock() {
        stockManager.setIngredientStockLevel("Onions", 10);
        stockManager.setIngredientStockLevel("Flour", 100);
        boolean result = stockManager.checkLowStockLevel();
        assertFalse(result);
    }

    @Test
    void testGetInventoryServiceAndAlertServiceNotNull() {
        assertNotNull(stockManager.getInventoryService());
        assertNotNull(stockManager.getAlertService());
    }
}
