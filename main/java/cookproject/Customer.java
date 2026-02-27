package cookproject;

import java.util.List;
import java.util.ArrayList;

public class Customer extends User {
    private String dietaryPreference;
    private String allergyInfo;
    private String address;
    private List<String> dietaryRestrictions = new ArrayList<>();
    private List<String> allergies = new ArrayList<>();

    // Constructor كامل
    public Customer(String username, String email, String dietaryPreference, String allergyInfo, String address) {
        super(username, email, true);
        this.dietaryPreference = dietaryPreference;
        this.allergyInfo = allergyInfo;
        this.address = address;
    }

    // Constructor بسيط للاختبارات
    public Customer() {
        super("testuser", "test@example.com", true);
        this.dietaryPreference = "None";
        this.allergyInfo = "None";
        this.address = "Test Address";
    }

    // Constructor باستخدام كائن User فقط
    public Customer(User user) {
        super(user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
        this.dietaryPreference = "None";
        this.allergyInfo = "None";
        this.address = "Unknown";
    }

    // Getters & Setters
    public String getDietaryPreference() {
        return dietaryPreference;
    }

    public void setDietaryPreference(String dietaryPreference) {
        this.dietaryPreference = dietaryPreference;
    }

    public String getAllergyInfo() {
        return allergyInfo;
    }

    public void setAllergyInfo(String allergyInfo) {
        this.allergyInfo = allergyInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(List<String> restrictions) {
    this.dietaryRestrictions = restrictions != null ? restrictions : new ArrayList<>();
}


    public List<String> getAllergies() {
        return allergies;
    }

    public void addDietaryRestriction(String restriction) {
        if (this.dietaryPreference == null || this.dietaryPreference.equals("None")) {
            this.dietaryPreference = restriction;
        } else {
            this.dietaryPreference += ", " + restriction;
        }
        dietaryRestrictions.add(restriction);
    }

    public void addAllergy(String allergy) {
        if (this.allergyInfo == null || this.allergyInfo.equals("None")) {
            this.allergyInfo = allergy;
        } else {
            this.allergyInfo += ", " + allergy;
        }
        allergies.add(allergy);
    }

    public void clearPreferences() {
        this.dietaryPreference = "None";
        this.allergyInfo = "None";
        dietaryRestrictions.clear();
        allergies.clear();
    }

    public boolean isRestricted(String restriction) {
        return dietaryRestrictions.contains(restriction) ||
               (dietaryPreference != null && dietaryPreference.contains(restriction));
    }

    public User getUser() {
        return this;
    }
}
