package cookproject;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import cookproject.CustomerProfile;
import cookproject.CustomerProfileService;
import cookproject.Customer;
import cookproject.User;

import java.util.Map;
import java.util.HashSet;
import java.util.Set;

public class ManageDietaryPreferencesSteps {


    private String currentPage;
    private Map<String, String> enteredPreferences;
    private boolean isInputValid = true;
    private String displayedMessage;
    private String selectedCustomerId;
    private final CustomerProfileService profileService = new CustomerProfileService();
    private CustomerProfile profile1;
    private CustomerProfile profile2;
    private Customer customer;
    private Customer customerNoUser;
    private Set<String> testRestrictions = new HashSet<>();

  
    public ManageDietaryPreferencesSteps() {
        System.out.println("Step definitions initialized");
    }

   
    @Given("the customer is logged in to the system1")
    public void the_customer_is_logged_in_to_the_system1() {
        System.out.println("Customer is logged in.");
    }

    @Given("the chef is logged in to the system1")
    public void the_chef_is_logged_in_to_the_system1() {
        System.out.println("Chef logged in.");
    }


    @When("the customer navigates to the {string} page")
    public void the_customer_navigates_to_the_page(String page) {
        currentPage = page;
        System.out.println("Navigated to: " + page);
    }

    @When("the chef navigates to the {string} page")
    public void the_chef_navigates_to_the_page(String page) {
        currentPage = page;
        System.out.println("Chef navigated to: " + page);
    }

  
    @When("the customer enters the following dietary preferences:")
    public void the_customer_enters_the_following_dietary_preferences(DataTable dataTable) {
        enteredPreferences = dataTable.asMap(String.class, String.class);
        System.out.println("Entered preferences: " + enteredPreferences);
    }

    @When("the customer enters invalid characters in the preferences field")
    public void the_customer_enters_invalid_characters_in_the_preferences_field() {
        enteredPreferences = Map.of(
                "Dietary Restriction", "Vegetarian",
                "Allergy", "Gluten ,Soy");
        System.out.println("Entered invalid preferences: " + enteredPreferences);
    }

    @When("the customer entered invalid values for dietary restrictions or allergies")
    public void the_customer_entered_invalid_values() {
        enteredPreferences = Map.of(
                "Dietary Restriction", "!!!",
                "Allergy", "Gluten@, Soy#");
        System.out.println("Entered invalid values: " + enteredPreferences);
    }

    @Then("the system validates the input")
    public void the_system_validates_the_input() {
        isInputValid = enteredPreferences.entrySet().stream()
                .noneMatch(entry -> {
                    String value = entry.getValue();
                    return value != null && value.matches(".*[^a-zA-Z0-9,\\s].*");
                });

        System.out.println("Validation result: " + isInputValid);
        System.out.println("Input being validated: " + enteredPreferences);
    }

    
    @Then("the system saves the dietary preferences and allergies")
    public void the_system_saves_the_dietary_preferences_and_allergies() {
        if (!isInputValid)
            throw new AssertionError("Invalid input.");
        profileService.savePreferences(
                "1234",
                enteredPreferences.get("Dietary Restriction"),
                enteredPreferences.get("Allergy"));
        System.out.println("Preferences saved successfully");
    }

    @Given("the customer with ID {string} has dietary preferences:")
    public void the_customer_with_id_has_dietary_preferences(String customerId, DataTable dataTable) {
        Map<String, String> prefs = dataTable.asMap(String.class, String.class);
        profileService.savePreferences(
                customerId,
                prefs.get("Dietary Restriction"),
                prefs.get("Allergy"));
        System.out.println("Saved preferences for customer: " + customerId);
    }

    @When("the chef selects the customer profile with ID {string}")
    public void the_chef_selects_the_customer_profile_with_id(String customerId) {
        selectedCustomerId = customerId;
        System.out.println("Selected customer profile: " + customerId);
    }

    @When("the chef requests preferences for non-existent customer {string}")
    public void the_chef_requests_preferences_for_non_existent_customer(String customerId) {
        CustomerProfile profile = profileService.getPreferences(customerId);
        if (profile != null) {
            throw new AssertionError("Expected null for non-existent customer");
        }
        System.out.println("Requested non-existent customer: " + customerId);
    }

    
    @Then("the system displays {string}")
    public void the_system_displays(String expectedMessage) {
        displayedMessage = isInputValid ? "Preferences saved successfully."
                : "Invalid input. Please check your entries.";

        if (!expectedMessage.equals(displayedMessage)) {
            throw new AssertionError(
                    "Expected: " + expectedMessage + " but got: " + displayedMessage);
        }
        System.out.println("Displayed message: " + displayedMessage);
    }

    @Then("the system displays the dietary preferences:")
    public void the_system_displays_the_dietary_preferences(DataTable expectedTable) {
        Map<String, String> expected = expectedTable.asMap(String.class, String.class);
        CustomerProfile profile = profileService.getPreferences(selectedCustomerId);

        if (profile == null ||
                !profile.getDietaryRestriction().equals(expected.get("Dietary Restriction")) ||
                !profile.getAllergies().equals(expected.get("Allergy"))) {
            throw new AssertionError("Preferences mismatch!");
        }

        System.out.println("Expected: " + expected);
        System.out.println("Actual Restriction: " + profile.getDietaryRestriction());
        System.out.println("Actual Allergy: " + profile.getAllergies());
    }

    @Then("the system returns null for non-existent customer")
    public void the_system_returns_null_for_non_existent_customer() {
        System.out.println("Verified null return for non-existent customer");
    }

    @Given("two customer profiles with the same data")
    public void two_customer_profiles_with_same_data() {
        profile1 = new CustomerProfile("001", "Vegan", "Nuts");
        profile2 = new CustomerProfile("001", "Vegan", "Nuts");
        System.out.println("Created two identical profiles");
    }

    @Given("two customer profiles with different dietary restrictions")
    public void two_customer_profiles_with_different_dietary_restrictions() {
        profile1 = new CustomerProfile("001", "Vegan", "Nuts");
        profile2 = new CustomerProfile("001", "Vegetarian", "Nuts");
        System.out.println("Created profiles with different restrictions");
    }

    @Given("two customer profiles with null data")
    public void two_customer_profiles_with_null_data() {
        profile1 = new CustomerProfile(null, null, null);
        profile2 = new CustomerProfile(null, null, null);
        System.out.println("Created profiles with null data");
    }

    @Given("a customer profile is compared with null")
    public void a_customer_profile_is_compared_with_null() {
        profile1 = new CustomerProfile("001", "Vegan", "Nuts");
        System.out.println("Created profile for null comparison");
    }

    @Given("a customer profile with null customer ID")
    public void a_customer_profile_with_null_customer_id() {
        profile1 = new CustomerProfile(null, "Vegan", "Nuts");
        System.out.println("Created profile with null customer ID");
    }

    @Then("the system confirms they are equal")
    public void the_system_confirms_they_are_equal() {
        if (!profile1.equals(profile2)) {
            throw new AssertionError("Profiles should be equal");
        }
        if (profile1.hashCode() != profile2.hashCode()) {
            throw new AssertionError("Hash codes should match");
        }
        System.out.println("Verified profile equality");
    }

    @Then("the system confirms they are not equal")
    public void the_system_confirms_they_are_not_equal() {
        if (profile1.equals(profile2)) {
            throw new AssertionError("Profiles should not be equal");
        }
        System.out.println("Verified profile inequality");
    }

    @Then("the comparison returns false")
    public void the_comparison_returns_false() {
        if (profile1.equals(null)) {
            throw new AssertionError("Comparison with null should return false");
        }
        System.out.println("Verified null comparison");
    }

    @Then("the system tests equality edge cases")
    public void the_system_tests_equality_edge_cases() {
        if (!profile1.equals(profile1)) {
            throw new AssertionError("Object should equal itself");
        }
        if (profile1.equals(new Object())) {
            throw new AssertionError("Should not equal an object of different class");
        }
        System.out.println("Verified equality edge cases");
    }

    @Then("the system tests hash codes with nulls")
    public void the_system_tests_hash_codes_with_nulls() {
        int hash = profile1.hashCode();
        System.out.println("Hash with nulls: " + hash);
    }

    @Then("the system handles null customer ID in profile")
    public void the_system_handles_null_customer_id_in_profile() {
        if (profile1.getCustomerId() != null) {
            throw new AssertionError("Customer ID should be null");
        }
        System.out.println("Verified null customer ID handling");
    }

    
    @Given("a customer with a user account")
    public void a_customer_with_a_user_account() {
        customer = new Customer(new User(currentPage, currentPage, null));
        System.out.println("Created customer with user account");
    }

    @Given("a customer is created without a user")
    public void a_customer_is_created_without_a_user() {
        customerNoUser = new Customer();
        System.out.println("Created customer without user");
    }

    @When("the customer adds dietary restrictions:")
    public void the_customer_adds_dietary_restrictions(DataTable dataTable) {
        dataTable.asList().forEach(restriction -> {
            customer.addDietaryRestriction(restriction);
            testRestrictions.add(restriction.toLowerCase());
        });
        System.out.println("Added dietary restrictions: " + testRestrictions);
    }

    @Then("the customer has no user")
    public void the_customer_has_no_user() {
        if (customerNoUser.getUser() != null) {
            throw new AssertionError("Expected user to be null");
        }
        System.out.println("Verified customer has no user");
    }

    @Then("the system correctly identifies restricted ingredients:")
    public void the_system_correctly_identifies_restricted_ingredients(DataTable dataTable) {
        Map<String, Boolean> testCases = dataTable.asMap(String.class, Boolean.class);
        testCases.forEach((ingredient, expected) -> {
            boolean actual = customer.isRestricted(ingredient);
            if (actual != expected) {
                throw new AssertionError(String.format(
                        "For ingredient '%s': expected %b but got %b",
                        ingredient, expected, actual));
            }
        });
        System.out.println("Verified ingredient restrictions");
    }

    @Then("the system handles case sensitivity correctly")
    public void the_system_handles_case_sensitivity_correctly() {
        customer.addDietaryRestriction("VEGAN");
        if (!customer.isRestricted("vegan")) {
            throw new AssertionError("Case sensitivity check failed");
        }
        System.out.println("Verified case sensitivity");
    }

    
    @When("the system saves preferences with null values")
    public void the_system_saves_preferences_with_null_values() {
        profileService.savePreferences("null_test", null, null);
        System.out.println("Saved preferences with null values");
    }

    @Then("the null preferences are stored correctly")
    public void the_null_preferences_are_stored_correctly() {
        CustomerProfile profile = profileService.getPreferences("null_test");
        if (profile.getDietaryRestriction() != null || profile.getAllergies() != null) {
            throw new AssertionError("Null preferences not stored correctly");
        }
        System.out.println("Verified null preference storage");
    }


    @When("the customer clicks {string}")
    public void the_customer_clicks(String button) {
        System.out.println("Customer clicked: " + button);
    }
}