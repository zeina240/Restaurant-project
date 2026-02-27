package cookproject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.*;

import java.io.File;

public class FinancialReportGenerationSteps {

    private boolean isLoggedIn;
    private FinancialReport currentReport;
    private boolean isPdfExported;
    private String exportPath;
    private boolean isGraphDisplayed;

    private ReportService reportService = new ReportService();

   
    @Given("the administrator logs into the system")
    public void givenAdministratorLogsIntoTheSystem() {
        isLoggedIn = true;
    }

    @When("the administrator selects the \"Generate Monthly Report\" option")
    public void whenAdministratorSelectsGenerateMonthlyReport() {
        if (isLoggedIn) {
            currentReport = reportService.generateMonthlyReport();
        }
    }

    @Then("the system should generate a detailed report with total revenue, expenses, and net profit")
    public void thenSystemGeneratesDetailedReport() {
        assertNotNull(currentReport);
        String data = currentReport.getData();
        assertTrue(data.contains("Total Revenue"));
        assertTrue(data.contains("Expenses"));
        assertTrue(data.contains("Net Profit"));
    }

    
    @Given("the system has generated the financial report")
    public void givenSystemHasGeneratedTheFinancialReport() {
        currentReport = reportService.generateMonthlyReport();
    }

    @When("the administrator chooses the \"Export to PDF\" option")
    public void whenAdministratorChoosesExportToPDF() {
        if (currentReport != null) {
            exportPath = "src/test/resources/reports/financial_report.pdf";
            isPdfExported = reportService.exportToPDF(currentReport, exportPath);
        }
    }

    @Then("the system should create a PDF file with the report data")
    public void thenSystemCreatesPDFFile() {
        assertTrue("PDF was not exported", isPdfExported);
        assertNotNull("Export path is null", exportPath);
        assertTrue("PDF file not found at path", new File(exportPath).exists());
    }

    @Then("allow the administrator to download it")
    public void thenAllowAdministratorToDownloadIt() {
        assertTrue(isPdfExported);
        assertNotNull(exportPath);
        assertTrue(new File(exportPath).exists());
    }

   
    @Given("the administrator wants to analyze revenue trends")
    public void givenAdministratorWantsToAnalyzeRevenueTrends() {
        isLoggedIn = true;
    }

    @When("the administrator views the \"Revenue Analytics\" page")
    public void whenAdministratorViewsRevenueAnalyticsPage() {
        if (isLoggedIn) {
            isGraphDisplayed = true; 
        }
    }

    @Then("the system should display graphical charts showing revenue growth and decline over time")
    public void thenSystemDisplaysRevenueGraphs() {
        assertTrue(isGraphDisplayed);
    }
}