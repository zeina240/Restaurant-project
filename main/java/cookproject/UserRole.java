package cookproject;

import java.util.Arrays;
import java.util.List;

public enum UserRole {
    CUSTOMER(Arrays.asList("PLACE_ORDER", "VIEW_MENU")),
    CHEF(Arrays.asList("VIEW_ORDERS", "UPDATE_ORDER_STATUS")),
    ADMIN(Arrays.asList("MANAGE_USERS", "MANAGE_ROLES", "VIEW_REPORTS", "CONFIGURE_SETTINGS")),
    WORKER(Arrays.asList("ASSIST_CHEF", "CLEAN_KITCHEN")),
    DELIVERY(Arrays.asList("VIEW_DELIVERY_TASKS", "UPDATE_DELIVERY_STATUS")),
    SUPPLIER(Arrays.asList("MANAGE_INVENTORY", "VIEW_REQUESTS")),
    KITCHEN_MANAGER(Arrays.asList("ASSIGN_TASKS", "OVERSEE_OPERATIONS"));

    private final List<String> permissions;

    UserRole(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<String> getPermissions() {
        return permissions;
    }
}