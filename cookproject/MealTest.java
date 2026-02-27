package cookproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class MealTest {

    private Ingredient tomato;
    private Ingredient cheese;
    private Meal meal;

    @BeforeEach
    public void setUp() {
        tomato = new Ingredient("Tomato", 10);
        cheese = new Ingredient("Cheese", 5);
        meal = new Meal("Pizza", Arrays.asList(tomato, cheese), 10.0);
    }

    @Test
    public void testConstructorWithNameOnly() {
        Meal m = new Meal("Burger");
        assertEquals("Burger", m.getName());
        assertEquals(0.0, m.getPrice());
        assertTrue(m.getIngredients().isEmpty());
        assertFalse(m.isContainsSubstitution());
    }

    @Test
    public void testAddIngredient() {
        Ingredient lettuce = new Ingredient("Lettuce", 3);
        meal.addIngredient(lettuce);
        assertTrue(meal.getIngredients().contains(lettuce));
    }

    @Test
    public void testRemoveIngredientByName() {
        boolean removed = meal.removeIngredientByName("Tomato");
        assertTrue(removed);
        assertFalse(meal.getIngredients().contains(tomato));
    }

    @Test
    public void testSetAndGetPrice() {
        meal.setPrice(12.5);
        assertEquals(12.5, meal.getPrice());
    }

    @Test
    public void testSetAndGetSubstitutionMessage() {
        meal.setContainsSubstitution(true);
        meal.setSubstitutionMessage("Substituted cheese with vegan cheese.");
        assertEquals("Substituted cheese with vegan cheese.", meal.getSubstitutionMessage());
    }

    @Test
    public void testDefaultSubstitutionMessageWhenEmpty() {
        meal.setContainsSubstitution(true);
        assertEquals("This meal contains substituted ingredients.", meal.getSubstitutionMessage());
    }

    @Test
    public void testNoSubstitutionMessage() {
        meal.setContainsSubstitution(false);
        assertEquals("No substitutions.", meal.getSubstitutionMessage());
    }

    @Test
    public void testToString() {
        assertEquals("Pizza ($10.0)", meal.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        Meal anotherMeal = new Meal("Pizza", Arrays.asList(tomato, cheese), 10.0);
        assertEquals(meal, anotherMeal);
        assertEquals(meal.hashCode(), anotherMeal.hashCode());
    }

    @Test
    public void testSetName() {
        meal.setName("Veggie Pizza");
        assertEquals("Veggie Pizza", meal.getName());
    }
}
