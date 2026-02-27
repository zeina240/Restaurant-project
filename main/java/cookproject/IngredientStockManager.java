
package cookproject;

public class IngredientStockManager {
    private boolean loggedIn = false;
    private InventoryService inventoryService;
    private AlertService alertService;

    public IngredientStockManager() {
        inventoryService = new InventoryService();
        alertService = new AlertService();
    }

    public void initializeStock() {
       
    }

    public void login(String user, String password) {
        if (user.equals("manager") && password.equals("password")) {
            loggedIn = true;
        }
    }

    public boolean viewStockOverview() {
        return loggedIn;
    }

    public void setIngredientStockLevel(String name, int quantity) {
        inventoryService.updateIngredient(name, quantity);
    }

    public boolean checkLowStockLevel() {
        for (Ingredient ing : inventoryService.getAllIngredients()) {
            if (ing.getStockLevel() < 5) {
                alertService.sendAlert("Ingredient " + ing.getName() +
                        " is low (Qty: " + ing.getStockLevel() + "). Suggested restock: 20 units.");
                return true;
            }
        }
        return false;
    }

    public InventoryService getInventoryService() {
        return inventoryService;

    }

    public AlertService getAlertService() {
        return alertService;
    }
}