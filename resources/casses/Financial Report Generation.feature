Feature: Financial Report Generation

  As a system administrator,
  I want to generate financial reports,
  So that I can analyze revenue and track business performance.

  Scenario: Generate a monthly revenue report
    Given the administrator logs into the system
    When the administrator selects the "Generate Monthly Report" option
    Then the system should generate a detailed report with total revenue, expenses, and net profit

  Scenario: Export financial report to PDF
    Given the system has generated the financial report
    When the administrator chooses the "Export to PDF" option
    Then the system should create a PDF file with the report data
    And allow the administrator to download it

  Scenario: Track revenue trends
    Given the administrator wants to analyze revenue trends
    When the administrator views the "Revenue Analytics" page
    Then the system should display graphical charts showing revenue growth and decline over time