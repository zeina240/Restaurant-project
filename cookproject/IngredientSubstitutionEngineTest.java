package cookproject;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class IngredientSubstitutionEngineTest {

    @Test
    public void testNullMealReturnsNull() {
        Customer customer = new Customer();
        Meal result = IngredientSubstitutionEngine.checkAndSubstitute(null, customer);
        assertNull(result);
    }

    @Test
    public void testNullCustomerReturnsOriginalMeal() {
        Meal meal = new Meal("Spaghetti", Arrays.asList(new Ingredient("gluten")));
        Meal result = IngredientSubstitutionEngine.checkAndSubstitute(meal, null);
        assertEquals(meal, result);
    }

    @Test
    public void testNoRestrictionsReturnsOriginalMeal() {
        Meal meal = new Meal("Salad", Arrays.asList(new Ingredient("lettuce")));
        Customer customer = new Customer();
        customer.setDietaryRestrictions(Collections.emptyList());
        Meal result = IngredientSubstitutionEngine.checkAndSubstitute(meal, customer);
        assertEquals(meal, result);
    }

    @Test
    public void testSubstitutionHappens() {
        Meal meal = new Meal("Pasta", Arrays.asList(new Ingredient("gluten")));
        Customer customer = new Customer();
        customer.setDietaryRestrictions(Arrays.asList("gluten"));

        Meal result = IngredientSubstitutionEngine.checkAndSubstitute(meal, customer);

        assertNotSame(meal, result);
        assertTrue(result.isContainsSubstitution());
        assertEquals("gluten-free pasta", result.getIngredients().get(0).getName());
        assertTrue(result.getSubstitutionMessage().contains("replaced"));
    }

    @Test
    public void testMultipleSubstitutions() {
        Meal meal = new Meal("Combo", Arrays.asList(
            new Ingredient("gluten"),
            new Ingredient("dairy"),
            new Ingredient("tomato")
        ));

        Customer customer = new Customer();
        customer.setDietaryRestrictions(Arrays.asList("gluten", "dairy", "tomato"));

        Meal result = IngredientSubstitutionEngine.checkAndSubstitute(meal, customer);

        List<String> expected = Arrays.asList("gluten-free pasta", "almond milk", "roasted red pepper");
        for (int i = 0; i < result.getIngredients().size(); i++) {
            assertEquals(expected.get(i), result.getIngredients().get(i).getName());
        }

        assertTrue(result.isContainsSubstitution());
        assertTrue(result.getSubstitutionMessage().contains("replaced"));
    }

    @Test
    public void testNoSubstitutionWhenIngredientNotRestricted() {
        Meal meal = new Meal("Soup", Arrays.asList(new Ingredient("onion")));
        Customer customer = new Customer();
        customer.setDietaryRestrictions(Arrays.asList("gluten"));

        Meal result = IngredientSubstitutionEngine.checkAndSubstitute(meal, customer);

        assertEquals(meal, result); // نفس الكائن
        assertFalse(result.isContainsSubstitution());
    }

    @Test
    public void testEmptyIngredientsList() {
        Meal meal = new Meal("Air Meal", Collections.emptyList());
        Customer customer = new Customer();
        customer.setDietaryRestrictions(Arrays.asList("gluten"));

        Meal result = IngredientSubstitutionEngine.checkAndSubstitute(meal, customer);

        assertEquals(0, result.getIngredients().size());
        assertEquals(meal, result);
        assertFalse(result.isContainsSubstitution());
    }

    @Test
    public void testIngredientWithNoReplacementStaysSame() {
        Meal meal = new Meal("Mystery Dish", Arrays.asList(new Ingredient("rarething")));
        Customer customer = new Customer();
        customer.setDietaryRestrictions(Arrays.asList("rarething"));

        Meal substitutedMeal = IngredientSubstitutionEngine.checkAndSubstitute(meal, customer);

        assertNotSame(meal, substitutedMeal); // لأنه صار نسخة جديدة
        assertEquals("rarething", substitutedMeal.getIngredients().get(0).getName());
        assertFalse(substitutedMeal.isContainsSubstitution()); // تعديل هنا
    }
}
