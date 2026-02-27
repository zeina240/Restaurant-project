package cookproject;

public class AuthenticationService {

    // Private constructor to prevent instantiation
    private AuthenticationService() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String redirectUser(User user) {
    if (user.getRole() == null) {
        return "/unknown";
    }
    switch (user.getRole()) {
        case CUSTOMER:
            return "/customer/dashboard";
        case CHEF:
            return "/chef/dashboard";
        case ADMIN:
            return "/admin/control-panel";
        case WORKER:
            return "/worker/dashboard";
        case DELIVERY:
            return "/delivery/dashboard";
        case SUPPLIER:
            return "/supplier/management-panel";
        case KITCHEN_MANAGER:
            return "/kitchen/management-panel";
        default:
            return "/unknown";
    }
}

}
