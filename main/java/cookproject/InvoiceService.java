package cookproject;

public class InvoiceService {
    public Invoice generateInvoice(Order order) {
        Invoice invoice = new Invoice();
        invoice.setOrderDetails(order.getDetails());
        invoice.setTotalAmount(order.getTotalCost());
        invoice.setTaxAmount(order.calculateTax());
        invoice.setPaymentMethod(order.getPaymentMethod());
        return invoice;
    }
}
