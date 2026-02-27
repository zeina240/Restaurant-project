package cookproject;

import java.util.HashMap;
import java.util.Map;

public class RoleRedirector {
    private static final Map<String, String> redirectMap = new HashMap<>();

    static {
        redirectMap.put("Customer", "/customer/dashboard");
        redirectMap.put("Chef", "/chef/dashboard");
        redirectMap.put("Admin", "/admin/control-panel");
        redirectMap.put("Worker", "/worker/dashboard");
        redirectMap.put("Delivery Personnel", "/delivery/dashboard");
        redirectMap.put("Supplier", "/supplier/management-panel");
        redirectMap.put("Kitchen Manager", "/kitchen/management-panel");
    }

    public static String getRedirectURL(String role) {
        return redirectMap.getOrDefault(role, "/unknown");
    }
}
