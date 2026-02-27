package cookproject;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MealBuilderTest {

    @Test
    public void testAddIngredientAndTotalPrice() {
        MealBuilder builder = new MealBuilder();

        Ingredient cheese = new Ingredient("Cheese", 5.0, "");
        Ingredient bread = new Ingredient("Bread", 3.0, "");

        builder.addIngredient(cheese);
        builder.addIngredient(bread);

        assertEquals(2, builder.getSelectedIngredients().size());
        assertTrue(builder.getSelectedIngredients().contains(cheese));
        assertTrue(builder.getSelectedIngredients().contains(bread));
        assertEquals(8.0, builder.getTotalPrice(), 0.001);
    }

    @Test
    public void testExceedsBudget() {
        MealBuilder builder = new MealBuilder();
        builder.setBudget(10.0);

        Ingredient cheese = new Ingredient("Cheese", 7.0, "");
        Ingredient bread = new Ingredient("Bread", 5.0, "");

        builder.addIngredient(cheese);
        assertFalse(builder.exceedsBudget());

        builder.addIngredient(bread);
        assertTrue(builder.exceedsBudget());
    }

    @Test
    public void testGetVisualPreview() {
        MealBuilder builder = new MealBuilder();

        Ingredient cheese = new Ingredient("Cheese", 5.0, "");
        Ingredient bread = new Ingredient("Bread", 3.0, "");

        builder.addIngredient(cheese);
        builder.addIngredient(bread);

        String preview = builder.getVisualPreview();
        assertEquals("Meal Preview: Cheese Bread", preview);
    }

    @Test
    public void testSetNameThrowsException() {
        MealBuilder builder = new MealBuilder();
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
            builder.setName("My Meal");
        });

        assertEquals("Unimplemented method 'setName'", exception.getMessage());
    }

    @Test
    public void testBuildThrowsException() {
        MealBuilder builder = new MealBuilder();
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
            builder.build();
        });

        assertEquals("Unimplemented method 'build'", exception.getMessage());
    }
}
