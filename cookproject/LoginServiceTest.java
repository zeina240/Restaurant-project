package cookproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    private LoginService loginService;

    @BeforeEach
    void setUp() {
        loginService = new LoginService();
    }

    @Test
    void testSuccessfulLoginForCustomer() {
        User user = loginService.login("customer1", "pass123");
        assertNotNull(user);
        assertEquals("customer1", user.getUsername());
        assertEquals(UserRole.CUSTOMER, user.getRole());
    }

    @Test
    void testSuccessfulLoginForChef() {
        User user = loginService.login("chef1", "chefpass");
        assertNotNull(user);
        assertEquals("chef1", user.getUsername());
        assertEquals(UserRole.CHEF, user.getRole());
    }

    @Test
    void testSuccessfulLoginForAdmin() {
        User user = loginService.login("admin", "admin123");
        assertNotNull(user);
        assertEquals(UserRole.ADMIN, user.getRole());
    }

    @Test
    void testSuccessfulLoginForWorker() {
        User user = loginService.login("worker", "worker123");
        assertNotNull(user);
        assertEquals(UserRole.WORKER, user.getRole());
    }

    @Test
    void testSuccessfulLoginForDelivery() {
        User user = loginService.login("delivery", "delivery123");
        assertNotNull(user);
        assertEquals(UserRole.DELIVERY, user.getRole());
    }

    @Test
    void testSuccessfulLoginForSupplier() {
        User user = loginService.login("supplier", "supplier123");
        assertNotNull(user);
        assertEquals(UserRole.SUPPLIER, user.getRole());
    }

    @Test
    void testSuccessfulLoginForKitchenManager() {
        User user = loginService.login("kitchenManager", "kitchen123");
        assertNotNull(user);
        assertEquals(UserRole.KITCHEN_MANAGER, user.getRole());
    }

    @Test
    void testLoginFailsWithWrongPassword() {
        User user = loginService.login("admin", "wrongpassword");
        assertNull(user);
    }

    @Test
    void testLoginFailsWithUnknownUsername() {
        User user = loginService.login("nonexistent", "whatever");
        assertNull(user);
    }
}
