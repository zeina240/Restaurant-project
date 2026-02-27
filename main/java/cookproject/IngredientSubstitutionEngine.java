package cookproject;

import java.util.ArrayList;
import java.util.List;

public class IngredientSubstitutionEngine {

    public static Meal checkAndSubstitute(Meal meal, Customer customer) {
        if (meal == null || customer == null) {
            return meal; // لا تعديل إذا كان أحدهما null
        }

        List<String> restrictions = customer.getDietaryRestrictions();
        if (restrictions == null || restrictions.isEmpty()) {
            return new Meal(meal.getName(), meal.getIngredients(), meal.getPrice());
        }

        List<Ingredient> newIngredients = new ArrayList<>();
        boolean substitutionDone = false;
        StringBuilder substitutionMsg = new StringBuilder();

        for (Ingredient ing : meal.getIngredients()) {
            if (restrictions.contains(ing.getName().toLowerCase())) {
                String replacement = getReplacementForIngredient(ing.getName());
                if (replacement != null) {
                    substitutionDone = true;
                    substitutionMsg.append("Ingredient ")
                        .append(ing.getName())
                        .append(" is replaced with ")
                        .append(replacement)
                        .append(" due to your dietary restriction.\n");
                    newIngredients.add(new Ingredient(replacement));
                } else {
                    newIngredients.add(ing); // لا يوجد بديل، نحتفظ بالمكون الأصلي
                }
            } else {
                newIngredients.add(ing); // غير مقيد، نضيفه كما هو
            }
        }

        Meal resultMeal = new Meal(meal.getName(), newIngredients, meal.getPrice());
        resultMeal.setContainsSubstitution(substitutionDone);
        if (substitutionDone) {
            resultMeal.setSubstitutionMessage(substitutionMsg.toString().trim());
        }

        return resultMeal;
    }

    private static String getReplacementForIngredient(String ingredientName) {
        switch (ingredientName.toLowerCase()) {
            case "gluten":
                return "gluten-free pasta";
            case "dairy":
                return "almond milk";
            case "tomato":
                return "roasted red pepper";
            // أضف المزيد من البدائل حسب الحاجة
            default:
                return null;
        }
    }
}
