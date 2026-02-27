package cookproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FinancialReportTest {

    @Test
    void testFinancialReportCreation() {
        String expectedType = "Monthly";
        String expectedData = "Revenue: $5000";

        FinancialReport report = new FinancialReport(expectedType, expectedData);

        assertEquals(expectedType, report.getType(), "Type should match the one passed to constructor.");
        assertEquals(expectedData, report.getData(), "Data should match the one passed to constructor.");
    }
}
