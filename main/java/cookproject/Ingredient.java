package cookproject;

public class Ingredient {
    private String name; 
    private int stockLevel; 
    private double price;
    private String imagePath;


    public Ingredient(String name, int stockLevel) {
        this.name = name;
        this.stockLevel = stockLevel;
    }

    public Ingredient(String name, double price, String imagePath) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
    }

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getQuantity() {
   
        throw new UnsupportedOperationException("Unimplemented method 'getQuantity'");
    }
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Ingredient that = (Ingredient) o;

    return name.equals(that.name);
}

@Override
public int hashCode() {
    return name.hashCode();
}

}
