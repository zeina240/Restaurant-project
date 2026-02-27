package cookproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    private Menu menu;
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Meal meal1;
    private Meal meal2;

    @BeforeEach
    public void setup() {
        menu = new Menu();

        ingredient1 = new Ingredient("Tomato", 10);
        ingredient2 = new Ingredient("Cheese", 5);

        meal1 = new Meal("Pizza");
        meal2 = new Meal("Pasta");
    }

    // ======= Ingredient tests =======
    @Test
    public void testAddAndGetIngredient() {
        menu.addIngredient(ingredient1);
        Ingredient retrieved = menu.getIngredient("Tomato");
        assertNotNull(retrieved);
        assertEquals("Tomato", retrieved.getName());
    }

    @Test
    public void testContainsIngredient() {
        menu.addIngredient(ingredient1);
        assertTrue(menu.containsIngredient("Tomato"));
        assertFalse(menu.containsIngredient("Cheese"));
    }

    @Test
    public void testGetAllIngredients() {
        menu.addIngredient(ingredient1);
        menu.addIngredient(ingredient2);

        Map<String, Ingredient> allIngredients = menu.getAllIngredients();
        assertEquals(2, allIngredients.size());
        assertTrue(allIngredients.containsKey("Tomato"));
        assertTrue(allIngredients.containsKey("Cheese"));
    }

    // ======= Meal tests =======
    @Test
    public void testAddAndGetMeal() {
        menu.addMeal(meal1);
        Meal retrieved = menu.getMeal("Pizza");
        assertNotNull(retrieved);
        assertEquals("Pizza", retrieved.getName());
    }

    @Test
    public void testContainsMeal() {
        menu.addMeal(meal1);
        assertTrue(menu.containsMeal("Pizza"));
        assertFalse(menu.containsMeal("Pasta"));
    }

    @Test
    public void testGetAllMeals() {
        menu.addMeal(meal1);
        menu.addMeal(meal2);

        Map<String, Meal> allMeals = menu.getAllMeals();
        assertEquals(2, allMeals.size());
        assertTrue(allMeals.containsKey("Pizza"));
        assertTrue(allMeals.containsKey("Pasta"));
    }

    @Test
    public void testGetMealsReturnsSameAsGetAllMeals() {
        menu.addMeal(meal1);
        Map<String, Meal> meals1 = menu.getMeals();
        Map<String, Meal> meals2 = menu.getAllMeals();

        assertSame(meals1, meals2);
    }

    @Test
    public void testRemoveMeal() {
        menu.addMeal(meal1);
        boolean removed = menu.removeMeal("Pizza");
        assertTrue(removed);
        assertFalse(menu.containsMeal("Pizza"));
    }

    @Test
    public void testRemoveMealNotFound() {
        boolean removed = menu.removeMeal("NonExistentMeal");
        assertFalse(removed);
    }

    @Test
    public void testFindMealByName() {
        menu.addMeal(meal1);
        Meal found = menu.findMealByName("Pizza");
        assertNotNull(found);
        assertEquals("Pizza", found.getName());
    }

    @Test
    public void testFindMealByNameNotFound() {
        Meal found = menu.findMealByName("NonExistentMeal");
        assertNull(found);
    }
}
