package cookproject;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {

    @Test
    public void testUserNotFound() {
        UserRepository mockRepo = mock(UserRepository.class);
        when(mockRepo.findByEmail("notfound@example.com")).thenReturn(null);

        AuthService authService = new AuthService(mockRepo);
        String result = authService.login("notfound@example.com", "any");
        assertEquals("user_not_found", result);
    }

    @Test
    public void testInvalidPassword() {
        User mockUser = new User("test@example.com", "correctpass", UserRole.CUSTOMER);
        UserRepository mockRepo = mock(UserRepository.class);
        when(mockRepo.findByEmail("test@example.com")).thenReturn(mockUser);

        AuthService authService = new AuthService(mockRepo);
        String result = authService.login("test@example.com", "wrongpass");
        assertEquals("invalid_password", result);
    }

    @Test
    public void testSuccessfulLogin() {
        User mockUser = new User("admin@example.com", "admin123", UserRole.ADMIN);
        UserRepository mockRepo = mock(UserRepository.class);
        when(mockRepo.findByEmail("admin@example.com")).thenReturn(mockUser);

        AuthService authService = new AuthService(mockRepo);
        String result = authService.login("admin@example.com", "admin123");
        assertEquals("success:ADMIN", result);
    }
}
