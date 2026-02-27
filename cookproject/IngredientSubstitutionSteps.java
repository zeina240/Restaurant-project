package cookproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class IngredientSubstitutionSteps {

    private Customer customer;
    private Meal meal;
    private Meal substitutedMeal;

    @Given("the customer has dietary restrictions saved in their profile,")
    public void the_customer_has_dietary_restrictions_saved_in_their_profile() {
        customer = new Customer();
        customer.addDietaryRestriction("gluten");
    }

    @Given("the customer selects a meal that contains a restricted ingredient,")
    public void the_customer_selects_a_meal_that_contains_a_restricted_ingredient() {
     meal = new Meal("Pasta", List.of(
    new Ingredient("gluten"),
    new Ingredient("tomato")
));

    }

    @When("the customer attempts to add the meal to their order,")
    public void the_customer_attempts_to_add_the_meal_to_their_order() {
        substitutedMeal = IngredientSubstitutionEngine.checkAndSubstitute(meal, customer);
    }

    @Then("the system should suggest an alternative ingredient,")
public void the_system_should_suggest_an_alternative_ingredient() {
    if (substitutedMeal == null) {
        throw new AssertionError("No substituted meal was provided by the system.");
    }

    assertTrue(substitutedMeal.isContainsSubstitution(),
        "Expected substitution suggestion, but none was found.");
}
@When("the system suggests a substitution,")
public void the_system_suggests_a_substitution() {
    substitutedMeal = new Meal(null, null);
    substitutedMeal.setContainsSubstitution(true); // تأكد من وجود هذه الدالة في كلاس Meal
}


    @Then("the system should display \"Ingredient {string} is replaced with {string} due to your dietary restriction.\"")
public void the_system_should_display(String original, String replacement) {
    String expectedMessage = "Ingredient " + original + " is replaced with " + replacement + " due to your dietary restriction.";
    String actualMessage = substitutedMeal.getSubstitutionMessage();
    assertEquals(expectedMessage, actualMessage);
}


}
