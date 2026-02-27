package cookproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IngredientServiceTest {

    private IngredientService ingredientService;

    @BeforeEach
    void setUp() {
        ingredientService = new IngredientService();
    }

    @Test
    void testAvailabilityOfKnownIngredients() {
        assertTrue(ingredientService.isAvailable("Chicken"));
        assertTrue(ingredientService.isAvailable("Rice"));
        assertTrue(ingredientService.isAvailable("Chocolate"));
        assertFalse(ingredientService.isAvailable("Unicorn Meat"));
    }

    @Test
    void testAvailabilityOfUnknownIngredient() {
        assertFalse(ingredientService.isAvailable("Banana"));
    }

    @Test
    void testCompatibilityBetweenCompatibleIngredients() {
        assertTrue(ingredientService.areCompatible("Chicken", "Rice"));
        assertTrue(ingredientService.areCompatible("Rice", "Chicken"));
    }

    @Test
    void testCompatibilityBetweenIncompatibleIngredients() {
        assertFalse(ingredientService.areCompatible("Chocolate", "Chicken"));
        assertFalse(ingredientService.areCompatible("Rice", "Chocolate"));
    }

    @Test
    void testCompatibilityWithUnknownIngredient() {
        assertFalse(ingredientService.areCompatible("Chicken", "Banana"));
        assertFalse(ingredientService.areCompatible("Banana", "Chicken"));
    }

    @Test
    void testAvailabilityMessages() {
        assertEquals("Chicken is available.", ingredientService.getAvailabilityMessage("Chicken"));
        assertEquals("Unicorn Meat is unavailable.", ingredientService.getAvailabilityMessage("Unicorn Meat"));
        assertEquals("Banana is unavailable.", ingredientService.getAvailabilityMessage("Banana"));
    }

    @Test
    void testCompatibilityMessages() {
        assertEquals("Chicken and Rice are compatible.", ingredientService.getCompatibilityMessage("Chicken", "Rice"));
        assertEquals("Chocolate and Chicken are incompatible and cannot be combined.",
                ingredientService.getCompatibilityMessage("Chocolate", "Chicken"));
        assertEquals("Chicken and Banana are incompatible and cannot be combined.",
                ingredientService.getCompatibilityMessage("Chicken", "Banana"));
    }
}
