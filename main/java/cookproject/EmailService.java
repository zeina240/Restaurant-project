package cookproject;

public class EmailService {
    public boolean sendInvoice(Invoice invoice, String email) {
        System.out.println("Sending invoice to: " + email);
        return true;
    }
}
