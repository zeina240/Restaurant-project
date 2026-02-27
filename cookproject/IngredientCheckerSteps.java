package cookproject;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

import java.util.*;

public class IngredientCheckerSteps {

    private IngredientService ingredientService;
    private String selectedIngredient1;
    private String selectedIngredient2;
    private String notificationMessage = "";

    @Given("the menu has a list of ingredients with availability and compatibility data")
    public void the_menu_has_a_list_of_ingredients_with_availability_and_compatibility_data() {
        ingredientService = new IngredientService();
    }

    @Given("the customer selects {string} and {string} as ingredients for their meal")
    public void the_customer_selects_and_as_ingredients_for_their_meal(String ingredient1, String ingredient2) {
        selectedIngredient1 = ingredient1;
        selectedIngredient2 = ingredient2;
    }

    @When("the customer attempts to add the selected ingredients to their order")
    public void the_customer_attempts_to_add_the_selected_ingredients_to_their_order() {
       
        boolean isAvailable1 = ingredientService.isAvailable(selectedIngredient1);
        boolean isAvailable2 = ingredientService.isAvailable(selectedIngredient2);
        boolean areCompatible = ingredientService.areCompatible(selectedIngredient1, selectedIngredient2);

     
        if (!isAvailable1 || !isAvailable2) {
            notificationMessage = ingredientService
                    .getAvailabilityMessage(isAvailable1 ? selectedIngredient2 : selectedIngredient1);
        } else if (!areCompatible) {
            notificationMessage = ingredientService.getCompatibilityMessage(selectedIngredient1, selectedIngredient2);
        } else {
            notificationMessage = "Your selection is valid!";
        }
    }

    @Then("the system should confirm that both {string} and {string} are available and compatible")
    public void the_system_should_confirm_that_both_and_are_available_and_compatible(String ing1, String ing2) {
        assertEquals("Your selection is valid!", notificationMessage);
    }

    @Then("the customer should be notified that their selection is valid")
    public void the_customer_should_be_notified_that_their_selection_is_valid() {
        assertEquals("Your selection is valid!", notificationMessage);
    }

    @Then("the system should check the availability of {string}")
    public void the_system_should_check_the_availability_of(String ingredient) {
        String availabilityMessage = ingredientService.getAvailabilityMessage(ingredient);
        assertEquals(ingredient + " is unavailable.", availabilityMessage);
    }

    @Then("the system should notify the customer that {string} is unavailable")
    public void the_system_should_notify_the_customer_that_is_unavailable(String ingredient) {
        notificationMessage = ingredientService.getAvailabilityMessage(ingredient);
        assertEquals(ingredient + " is unavailable.", notificationMessage);
    }

    @Then("the system should check the compatibility of {string} and {string}")
    public void the_system_should_check_the_compatibility_of_and(String ing1, String ing2) {
        String compatibilityMessage = ingredientService.getCompatibilityMessage(ing1, ing2);
        assertEquals(ing1 + " and " + ing2 + " are incompatible and cannot be combined.", compatibilityMessage);
    }

    @Then("the system should notify the customer that {string} and {string} are incompatible and cannot be combined")
    public void the_system_should_notify_the_customer_that_and_are_incompatible_and_cannot_be_combined(String ing1,
            String ing2) {
        notificationMessage = ingredientService.getCompatibilityMessage(ing1, ing2);
        assertEquals(ing1 + " and " + ing2 + " are incompatible and cannot be combined.", notificationMessage);
    }
}