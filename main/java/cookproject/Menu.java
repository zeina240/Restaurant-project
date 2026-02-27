package cookproject;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<String, Ingredient> ingredients = new HashMap<>();
    private Map<String, Meal> meals = new HashMap<>();

    // === إدارة المكونات ===
    public void addIngredient(Ingredient ingredient) {
        ingredients.put(ingredient.getName(), ingredient);
    }

    public Ingredient getIngredient(String ingredientName) {
        return ingredients.get(ingredientName);
    }

    public boolean containsIngredient(String ingredientName) {
        return ingredients.containsKey(ingredientName);
    }

    public Map<String, Ingredient> getAllIngredients() {
        return ingredients;
    }

    // === إدارة الوجبات ===
    public void addMeal(Meal meal) {
        meals.put(meal.getName(), meal);
    }

    public Meal getMeal(String mealName) {
        return meals.get(mealName);
    }

    public boolean containsMeal(String mealName) {
        return meals.containsKey(mealName);
    }

    public Map<String, Meal> getAllMeals() {
        return meals;
    }

    // === الدوال المضافة ===
    public Map<String, Meal> getMeals() {
        return this.meals;
    }

    public boolean removeMeal(String mealName) {
    if (meals.containsKey(mealName)) {
        meals.remove(mealName);
        return true;
    }
    return false;
}


    public Meal findMealByName(String mealName) {
        return this.meals.get(mealName);
    }
}
