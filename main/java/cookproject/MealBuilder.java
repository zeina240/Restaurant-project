package cookproject;

import java.util.*;

public class MealBuilder {
    private List<Ingredient> selectedIngredients = new ArrayList<>();
    private double totalPrice = 0.0;
    private double budget = Double.MAX_VALUE;

    public void addIngredient(Ingredient ingredient) {
        selectedIngredients.add(ingredient);
        totalPrice += ingredient.getPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<Ingredient> getSelectedIngredients() {
        return selectedIngredients;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public boolean exceedsBudget() {
        return totalPrice > budget;
    }

    public String getVisualPreview() {
        StringBuilder preview = new StringBuilder("Meal Preview: ");
        for (Ingredient ing : selectedIngredients) {
            preview.append(ing.getName()).append(" ");
        }
        return preview.toString().trim();
    }

    public void setName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setName'");
    }

    public Meal build() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'build'");
    }
}