package cookproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class IngredientStockSteps {

    private IngredientStockManager stockManager;
    private InventoryService inventoryService;
    private AlertService alertService;

    private boolean isStockListed = false;
    private boolean isAlertSent = false;

    @Before
    public void setup() {
      
        stockManager = new IngredientStockManager();
        inventoryService = new InventoryService(); 
        alertService = new AlertService();
        
       
        inventoryService.addIngredient(new Ingredient("Tomatoes", 10));
        inventoryService.addIngredient(new Ingredient("Onions", 5));

    
        stockManager.initializeStock();
    }

    @Given("the kitchen manager logs into the inventory system1")
    public void the_kitchen_manager_logs_into_the_inventory_system1() {
        stockManager.login("manager", "password");
    }

    @Given("the manager navigates to the stock overview page")
    public void the_manager_navigates_to_the_stock_overview_page() {
        isStockListed = stockManager.viewStockOverview();
    }

    @Then("the system should display a list of all ingredients with their current stock levels")
    public void the_system_should_display_a_list_of_all_ingredients_with_their_current_stock_levels() {
       
        List<Ingredient> stockList = inventoryService.getAllIngredients();
        assertNotNull("Stock list should not be null", stockList);
        assertFalse("Stock levels should be displayed", stockList.isEmpty());

    
        for (Ingredient ing : stockList) {
            System.out.println("Ingredient: " + ing.getName() + ", Quantity: " + ing.getStockLevel());
        }
    }

    @Given("the ingredient {string} falls below the minimum threshold")
    public void the_ingredient_falls_below_the_minimum_threshold(String ingredient) {
     
        stockManager.setIngredientStockLevel(ingredient, 2);
    }

    @When("the system detects the low stock level")
    public void the_system_detects_the_low_stock_level() {
      
        isAlertSent = stockManager.checkLowStockLevel();
        System.out.println("Is alert sent: " + isAlertSent); 
    }

    @Then("the kitchen manager should receive an alert notification")
    public void the_kitchen_manager_should_receive_an_alert_notification() {
     
        assertTrue("Alert should be sent to kitchen manager", isAlertSent);
    }

    @Then("the alert should include the ingredient name, current quantity, and suggested restock amount")
    public void the_alert_should_include_the_ingredient_name_current_quantity_and_suggested_restock_amount() {
     
        String alert = alertService.getLatestAlert();
        System.out.println("Generated Alert: " + alert);

       
        assertNotNull("Alert should not be null", alert);
        assertTrue("Alert should include ingredient name", alert.contains("Tomatoes"));
        assertTrue("Alert should include current quantity", alert.matches(".*\\d+.*"));
        assertTrue("Alert should include suggested restock amount", alert.toLowerCase().contains("suggested"));
    }
}