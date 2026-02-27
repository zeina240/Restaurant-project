package cookproject;

public class CustomerProfile {
    private final String customerId;
    private final String dietaryRestriction;
    private final String allergies;

    public CustomerProfile(String customerId, String dietaryRestriction, String allergies) {
        this.customerId = customerId;
        this.dietaryRestriction = dietaryRestriction;
        this.allergies = allergies;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getDietaryRestriction() {
        return dietaryRestriction;
    }

    public String getAllergies() {
        return allergies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CustomerProfile))
            return false;
        CustomerProfile that = (CustomerProfile) o;

        boolean dietMatch = (dietaryRestriction == null) ? that.dietaryRestriction == null
                : dietaryRestriction.equals(that.dietaryRestriction);

        boolean allergiesMatch = (allergies == null) ? that.allergies == null : allergies.equals(that.allergies);

        return dietMatch && allergiesMatch;
    }

    @Override
    public int hashCode() {
        int result = dietaryRestriction != null ? dietaryRestriction.hashCode() : 0;
        result = 31 * result + (allergies != null ? allergies.hashCode() : 0);
        return result;
    }

    public Customer getCustomerById(String customerId2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCustomerById'");
    }
}