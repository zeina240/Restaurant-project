package cookproject;

import java.util.*;

public class SupplierService {
    private Map<String, Supplier> suppliers = new HashMap<>();
    private Map<String, SupplyOrder> orders = new HashMap<>();

    // إضافة مزود جديد
    public void addSupplier(Supplier supplier) {
        suppliers.put(supplier.getId(), supplier);
    }

    // الحصول على كل المزودين
    public Collection<Supplier> getAllSuppliers() {
        return suppliers.values();
    }

    // إضافة طلب تزويد جديد
    public void addSupplyOrder(SupplyOrder order) {
        orders.put(order.getOrderId(), order);
    }

    // عرض طلبات مزود معين
    public List<SupplyOrder> getOrdersBySupplierId(String supplierId) {
        List<SupplyOrder> result = new ArrayList<>();
        for (SupplyOrder order : orders.values()) {
            if (order.getSupplierId().equals(supplierId)) {
                result.add(order);
            }
        }
        return result;
    }

    // تحديث حالة الطلب
    public boolean updateOrderStatus(String orderId, String newStatus) {
        SupplyOrder order = orders.get(orderId);
        if (order != null) {
            order.setStatus(newStatus);
            return true;
        }
        return false;
    }
}
