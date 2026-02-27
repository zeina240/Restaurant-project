package cookproject;

import java.util.*;

public class PricingService {
    private Map<String, List<Supplier>> ingredientPrices = new HashMap<>();

    public void addSupplier(String ingredient, Supplier supplier) {
        ingredientPrices.putIfAbsent(ingredient, new ArrayList<>());
        ingredientPrices.get(ingredient).add(supplier);
    }

    public List<Supplier> getSuppliersFor(String ingredient) {
        return ingredientPrices.getOrDefault(ingredient, new ArrayList<>());
    }

    public Supplier getBestSupplier(String ingredient) {
        List<Supplier> suppliers = ingredientPrices.getOrDefault(ingredient, new ArrayList<>());
        if (suppliers.isEmpty()) {
            System.out.println("No suppliers available for ingredient: " + ingredient);
            return null;
        }
        return suppliers.stream()
                .min(Comparator.comparingDouble(Supplier::getUnitPrice))
                .orElse(null);
    }

    public Set<String> getAllIngredients() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getAllIngredients'");
    }

    public Set<String> getAllSuppliers() {
    
        throw new UnsupportedOperationException("Unimplemented method 'getAllSuppliers'");
    }

}