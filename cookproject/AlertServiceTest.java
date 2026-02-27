package cookproject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlertServiceTest {

    @Test
    public void testSendAlert() {
        AlertService service = new AlertService();
        service.sendAlert("Test message");

        assertEquals("Test message", service.getLatestAlert());
    }
}
