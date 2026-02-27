package cookproject;

public class PurchaseOrder {
    private Ingredient ingredient;
    private Supplier supplier;
    private int quantity;

    public PurchaseOrder(Ingredient ingredient, Supplier supplier, int quantity) {
        this.ingredient = ingredient;
        this.supplier = supplier;
        this.quantity = quantity;
    }

    public String generateOrderDetails() {
        return "Ingredient: " + ingredient.getName() +
               ", Supplier: " + supplier.getName() +
               ", Unit Price: " + supplier.getUnitPrice() +
               ", Quantity: " + quantity;
    }
}
