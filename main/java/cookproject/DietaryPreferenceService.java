package cookproject;

import java.util.Set;

public class DietaryPreferenceService {

    public boolean validatePreferences(Set<String> restrictions, Set<String> allergies) {
        if (restrictions == null || allergies == null) return false;

        for (String r : restrictions) {
            if (r.trim().isEmpty() || !r.matches("^[a-zA-Z\\s]+$")) {
                return false;
            }
        }

        for (String a : allergies) {
            if (a.trim().isEmpty() || !a.matches("^[a-zA-Z,\\s]+$")) {
                return false;
            }
        }

        return true;
    }

    public void updatePreferences(Customer customer, Set<String> restrictions, Set<String> allergies) {
        customer.clearPreferences();
        for (String r : restrictions) {
            customer.addDietaryRestriction(r);
        }
        for (String a : allergies) {
            customer.addAllergy(a);
        }
        System.out.println("Preferences updated in the system.");
    }
}