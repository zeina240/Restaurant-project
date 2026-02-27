package cookproject;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    private String name; // اسم الوجبة
    private List<Ingredient> ingredients; // قائمة المكونات
    private boolean containsSubstitution; // هل تحتوي على استبدال مكوّنات
    private double price; // سعر الوجبة
    private String substitutionMessage = ""; // رسالة الاستبدال

    // الباني 1: الاسم والمكونات
    public Meal(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients != null ? new ArrayList<>(ingredients) : new ArrayList<>();
        this.price = 0.0;
        this.containsSubstitution = false;
    }

    // الباني 2: الاسم، المكونات، السعر
    public Meal(String name, List<Ingredient> ingredients, double price) {
        this.name = name;
        this.ingredients = ingredients != null ? new ArrayList<>(ingredients) : new ArrayList<>();
        this.price = price;
        this.containsSubstitution = false;
    }

    // الباني 3: الاسم فقط
    public Meal(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
        this.price = 0.0;
        this.containsSubstitution = false;
    }

    // getter & setter للاسم
    public String getName() { return name; }
    public void setName(String newName) { this.name = newName; }

    // getter للمكونات
    public List<Ingredient> getIngredients() { return ingredients; }

    // إضافة مكون
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    // إزالة مكون بالاسم
    public boolean removeIngredientByName(String ingToRemove) {
        return ingredients.removeIf(ing -> ing.getName().equalsIgnoreCase(ingToRemove));
    }

    // السعر
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    // هل يوجد استبدال؟
    public boolean isContainsSubstitution() { return containsSubstitution; }
    public void setContainsSubstitution(boolean containsSubstitution) { 
        this.containsSubstitution = containsSubstitution;
    }

    // رسالة الاستبدال
    public String getSubstitutionMessage() {
        return !substitutionMessage.isEmpty() ? substitutionMessage : 
               (containsSubstitution ? "This meal contains substituted ingredients." : "No substitutions.");
    }
    public void setSubstitutionMessage(String message) {
        this.substitutionMessage = message;
    }

    // تمثيل نصي للوجبة
    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
    @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Meal meal = (Meal) o;

    if (Double.compare(meal.price, price) != 0) return false;
    if (!name.equals(meal.name)) return false;
    return ingredients.equals(meal.ingredients);
}

@Override
public int hashCode() {
    int result;
    long temp;
    result = name.hashCode();
    result = 31 * result + ingredients.hashCode();
    temp = Double.doubleToLongBits(price);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
}

}
