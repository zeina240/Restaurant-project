package cookproject;

import java.time.LocalDateTime;

public class Supplier {
    private String id;
    private String name;
    private String contact;
    private double unitPrice;
    private LocalDateTime lastUpdated;

    // الكونستركتور الكامل مع lastUpdated
    public Supplier(String id, String name, String contact, double unitPrice, LocalDateTime lastUpdated) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.unitPrice = unitPrice;
        this.lastUpdated = lastUpdated;
    }

    // الكونستركتور الجديد المطلوب: بدون lastUpdated، يتم تعيينه تلقائياً للوقت الحالي
    public Supplier(String id, String name, String contact, double unitPrice) {
        this(id, name, contact, unitPrice, LocalDateTime.now());
    }

    // الكونستركتور المختصر (اختياري)
    public Supplier(String name, double unitPrice, LocalDateTime lastUpdated) {
        this(null, name, null, unitPrice, lastUpdated);
    }

    // الجيتّرات
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    // السيّترات
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        this.lastUpdated = LocalDateTime.now();
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Supplier{id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", contact='" + contact + '\'' +
               ", unitPrice=" + unitPrice +
               ", lastUpdated=" + lastUpdated + '}';
    }
}
