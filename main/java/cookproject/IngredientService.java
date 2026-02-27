package cookproject;

import java.util.*;

public class IngredientService {
    private final Map<String, Boolean> availabilityMap = new HashMap<>();
    private final Map<String, Set<String>> compatibilityMap = new HashMap<>();

    public IngredientService() {
      
        availabilityMap.put("Chicken", true);
        availabilityMap.put("Rice", true);
        availabilityMap.put("Chocolate", true);
        availabilityMap.put("Unicorn Meat", false);

        compatibilityMap.put("Chicken", new HashSet<>(Arrays.asList("Rice")));
        compatibilityMap.put("Rice", new HashSet<>(Arrays.asList("Chicken")));
        compatibilityMap.put("Chocolate", new HashSet<>());
    }

    public boolean isAvailable(String ingredient) {
        return availabilityMap.getOrDefault(ingredient, false);
    }

    public boolean areCompatible(String ing1, String ing2) {
        Set<String> compatible = compatibilityMap.getOrDefault(ing1, Collections.emptySet());
        return compatible.contains(ing2);
    }

    public String getAvailabilityMessage(String ingredient) {
        if (!isAvailable(ingredient)) {
            return ingredient + " is unavailable.";
        }
        return ingredient + " is available.";
    }

    public String getCompatibilityMessage(String ing1, String ing2) {
        if (!areCompatible(ing1, ing2)) {
            return ing1 + " and " + ing2 + " are incompatible and cannot be combined.";
        }
        return ing1 + " and " + ing2 + " are compatible.";
    }
}