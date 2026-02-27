
package cookproject;
import java.util.logging.Logger;

public class AlertService {
    private static final Logger logger = Logger.getLogger(AlertService.class.getName());
    private String latestAlert;

    public void sendAlert(String message) {
        this.latestAlert = message;
        logger.info("ALERT: " + message);
    }

    public String getLatestAlert() {
        return latestAlert;
    }
}





   

    
