package cookproject;



import io.cucumber.java.en.*;

public class ChefSubstitutionSteps {

    private boolean substitutionSuggested = false;
    private boolean customerConfirmed = false;
    private boolean orderSentToKitchen = false;
    private boolean chefAlerted = false;
    private boolean chefReviewed = false;
    private boolean chefApprovedOrAdjusted = false;
    private String substitutionMessage = "";

    @Given("the system has suggested an ingredient substitution for a meal,")
    public void the_system_has_suggested_an_ingredient_substitution_for_a_meal() {
        substitutionSuggested = true;
        System.out.println("System has suggested a substitution: Ingredient X -> Ingredient Y.");
    }

    @Given("the customer has confirmed the substitution,")
    public void the_customer_has_confirmed_the_substitution() {
        if (substitutionSuggested) {
            customerConfirmed = true;
            System.out.println("Customer confirmed the substitution.");
        }
    }

    @When("the order is sent to the kitchen,")
    public void the_order_is_sent_to_the_kitchen() {
        if (customerConfirmed) {
            orderSentToKitchen = true;
            System.out.println("Order sent to the kitchen.");
        }
    }

    @Then("the chef should receive an alert about the substitution,")
    public void the_chef_should_receive_an_alert_about_the_substitution() {
        if (orderSentToKitchen) {
            chefAlerted = true;
            substitutionMessage = "Ingredient X has been substituted with Ingredient Y for this order.";
            System.out.println("Chef alert: " + substitutionMessage);
        }
    }

    @Then("the system should display substitution message {string}")
public void the_system_should_display_substitution_message(String message) {
    System.out.println("Substitution message: " + message);
}


    @When("the chef reviews the order in the kitchen system,")
    public void the_chef_reviews_the_order_in_the_kitchen_system() {
        if (chefAlerted) {
            chefReviewed = true;
            System.out.println("Chef is reviewing the order and substitution.");
        }
    }

    @Then("the chef should have an option to approve or adjust the suggested substitution,")
    public void the_chef_should_have_an_option_to_approve_or_adjust_the_suggested_substitution() {
        if (chefReviewed) {
            chefApprovedOrAdjusted = true;
            System.out.println("Chef has option to approve or make adjustments.");
        }
    }

    @Then("any adjustments should be updated in the customer's order summary.")
    public void any_adjustments_should_be_updated_in_the_customer_s_order_summary() {
        if (chefApprovedOrAdjusted) {
            System.out.println("Customer's order summary updated with chef's adjustment.");
        }
    }
}
