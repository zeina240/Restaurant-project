package cookproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class AuthenticationServiceTest {

    @Test
    public void testRedirectCustomer() {
        User user = new User("customer@example.com", "1234", UserRole.CUSTOMER);
        String result = AuthenticationService.redirectUser(user);
        assertEquals("/customer/dashboard", result);
    }

    @Test
    public void testRedirectChef() {
        User user = new User("chef@example.com", "1234", UserRole.CHEF);
        String result = AuthenticationService.redirectUser(user);
        assertEquals("/chef/dashboard", result);
    }

    @Test
    public void testRedirectAdmin() {
        User user = new User("admin@example.com", "1234", UserRole.ADMIN);
        String result = AuthenticationService.redirectUser(user);
        assertEquals("/admin/control-panel", result);
    }

    @Test
    public void testRedirectWorker() {
        User user = new User("worker@example.com", "1234", UserRole.WORKER);
        String result = AuthenticationService.redirectUser(user);
        assertEquals("/worker/dashboard", result);
    }

    @Test
    public void testRedirectDelivery() {
        User user = new User("delivery@example.com", "1234", UserRole.DELIVERY);
        String result = AuthenticationService.redirectUser(user);
        assertEquals("/delivery/dashboard", result);
    }

    @Test
    public void testRedirectSupplier() {
        User user = new User("supplier@example.com", "1234", UserRole.SUPPLIER);
        String result = AuthenticationService.redirectUser(user);
        assertEquals("/supplier/management-panel", result);
    }

    @Test
    public void testRedirectKitchenManager() {
        User user = new User("kitchen@example.com", "1234", UserRole.KITCHEN_MANAGER);
        String result = AuthenticationService.redirectUser(user);
        assertEquals("/kitchen/management-panel", result);
    }

    @Test
    public void testRedirectUnknownRole() {
        // إنشاء دور غير معروف عبر كلاس فرعي وهمي
        User user = new User("unknown@example.com", "1234") {
            @Override
            public UserRole getRole() {
                return null; // simulate unknown role
            }
        };
        String result = AuthenticationService.redirectUser(user);
        assertEquals("/unknown", result);
    }

    @Test
public void testPrivateConstructorCoverage() throws Exception {
    Constructor<AuthenticationService> constructor = AuthenticationService.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    try {
        constructor.newInstance();
        fail("Expected UnsupportedOperationException to be thrown");
    } catch (InvocationTargetException e) {
        Throwable cause = e.getCause();
        assertTrue(cause instanceof UnsupportedOperationException);
        assertEquals("Utility class", cause.getMessage());
    }
}

}
