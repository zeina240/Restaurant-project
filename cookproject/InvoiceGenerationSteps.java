package cookproject;

import io.cucumber.java.en.*;

public class InvoiceGenerationSteps {

    private OrderService orderService = new OrderService();
    private PaymentService paymentService = new PaymentService();
    private InvoiceService invoiceService = new InvoiceService();
    private EmailService emailService = new EmailService();
    private PrinterService printerService = new PrinterService();

    private Order currentOrder;
    private Invoice invoice;

    @Given("the customer has completed a purchase at the restaurant")
    public void the_customer_has_completed_a_purchase_at_the_restaurant() {
        currentOrder = orderService.completeOrder("cust1");
        System.out.println("Customer has completed a purchase.");
    }

    @When("the payment is processed successfully")
    public void the_payment_is_processed_successfully() {
        boolean success = paymentService.processPayment(currentOrder, "Credit Card");
        System.out.println("Payment processed successfully: " + success);
    }

    @Then("the system should automatically generate an invoice")
    public void the_system_should_automatically_generate_an_invoice() {
        invoice = invoiceService.generateInvoice(currentOrder);
        System.out.println("System generated an invoice.");
    }

    @Then("the invoice should include the order details, total amount, taxes, and payment method")
    public void the_invoice_should_include_the_order_details_total_amount_taxes_and_payment_method() {
        assert invoice.containsDetails();
        System.out.println("Invoice contains order details, total amount, taxes, and payment method.");
    }

    @Then("the customer should receive the invoice via email or printed copy")
    public void the_customer_should_receive_the_invoice_via_email_or_printed_copy() {
        boolean sent = emailService.sendInvoice(invoice, "customer@example.com");
        boolean printed = printerService.printInvoice(invoice);
        assert sent || printed;
        System.out.println("Customer received invoice via email or printed copy.");
    }
}
