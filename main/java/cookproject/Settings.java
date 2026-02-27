package cookproject;

public class Settings {
    private static String restaurantName = "Default Restaurant";
    private static double taxRate = 0.1; // 10%
    private static int defaultChefCount = 3;
    private static int dailyOrderLimit = 100;

    public static String getRestaurantName() {
        return restaurantName;
    }

    public static void setRestaurantName(String name) {
        restaurantName = name;
    }

    public static double getTaxRate() {
        return taxRate;
    }

    public static void setTaxRate(double rate) {
        taxRate = rate;
    }

    public static int getDefaultChefCount() {
        return defaultChefCount;
    }

    public static void setDefaultChefCount(int count) {
        defaultChefCount = count;
    }

    public static int getDailyOrderLimit() {
        return dailyOrderLimit;
    }

    public static void setDailyOrderLimit(int limit) {
        dailyOrderLimit = limit;
    }
}