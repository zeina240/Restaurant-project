package cookproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    private InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        inventoryService = new InventoryService();
    }

    @Test
    void testSetAndGetStock() {
        inventoryService.setStock("Tomatoes", 15);
        assertEquals(15, inventoryService.getStock("Tomatoes"));

        assertEquals(0, inventoryService.getStock("Unknown")); // default
    }

    @Test
    void testIsLowStock() {
        inventoryService.setStock("Onions", 5);
        assertTrue(inventoryService.isLowStock("Onions", 10));
        assertFalse(inventoryService.isLowStock("Onions", 5));
        assertFalse(inventoryService.isLowStock("Onions", 3));
    }

    @Test
    void testSuggestRestock() {
        assertEquals(20, inventoryService.suggestRestock("Olive Oil"));
        assertEquals(50, inventoryService.suggestRestock("Flour"));
        assertEquals(30, inventoryService.suggestRestock("Unknown Item")); // default fallback
    }

    @Test
    void testUpdateStock() {
        inventoryService.setStock("Flour", 50);
        inventoryService.updateStock("Flour", 10);
        assertEquals(60, inventoryService.getStock("Flour"));

        // If item doesn't exist, should initialize to 0 then add amount
        inventoryService.updateStock("NewItem", 5);
        assertEquals(5, inventoryService.getStock("NewItem"));
    }

    @Test
    void testUpdateIngredientStockLevel() {
        List<Ingredient> ingredients = inventoryService.getAllIngredients();
        Ingredient oliveOil = ingredients.stream()
                .filter(i -> i.getName().equals("Olive Oil"))
                .findFirst().orElse(null);

        assertNotNull(oliveOil);
        assertEquals(30, oliveOil.getStockLevel());

        inventoryService.updateIngredient("Olive Oil", 60);
        assertEquals(60, oliveOil.getStockLevel());
    }

    @Test
    void testAddIngredient() {
        Ingredient newIngredient = new Ingredient("Butter", 25);
        inventoryService.addIngredient(newIngredient);

        List<Ingredient> ingredients = inventoryService.getAllIngredients();
        boolean found = ingredients.stream()
                .anyMatch(i -> i.getName().equals("Butter") && i.getStockLevel() == 25);
        assertTrue(found);
    }

    @Test
    void testGetAllIngredientsNotNull() {
        List<Ingredient> ingredients = inventoryService.getAllIngredients();
        assertNotNull(ingredients);
        assertFalse(ingredients.isEmpty());
    }
}
