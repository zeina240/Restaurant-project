package cookproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryService {

    private Map<String, Integer> stockLevels = new HashMap<>();
    private Map<String, Integer> averageUsage = new HashMap<>();
   
    private List<Ingredient> ingredients = new ArrayList<>();

    public InventoryService() {
      
        averageUsage.put("Olive Oil", 20);
        averageUsage.put("Flour", 50);
        
     
        ingredients.add(new Ingredient("Tomatoes", 10));
        ingredients.add(new Ingredient("Onions", 5));
        ingredients.add(new Ingredient("Olive Oil", 30));   
        ingredients.add(new Ingredient("Flour", 100));     }

    
    public void setStock(String item, int quantity) {
        stockLevels.put(item, quantity);
    }

    
    public int getStock(String item) {
        return stockLevels.getOrDefault(item, 0);
    }

    
    public boolean isLowStock(String item, int threshold) {
        return getStock(item) < threshold;
    }

    
    public int suggestRestock(String item) {
        return averageUsage.getOrDefault(item, 30);
    }

   
    public void updateStock(String item, int amount) {
        int current = getStock(item);
        stockLevels.put(item, current + amount);
    }

    
    public void updateIngredient(String ingredientName, int newStockLevel) {
        if (ingredients == null) {
            ingredients = new ArrayList<>();  
        } 

        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(ingredientName)) {
                ingredient.setStockLevel(newStockLevel); 
                break;
            }
        }
    }

 
    public List<Ingredient> getAllIngredients() {
        if (ingredients == null) {
            ingredients = new ArrayList<>();
        }
        return ingredients;
    }

   
    public void addIngredient(Ingredient ingredient) {
        if (ingredients == null) {
            ingredients = new ArrayList<>();
        }
        ingredients.add(ingredient);
    }
}