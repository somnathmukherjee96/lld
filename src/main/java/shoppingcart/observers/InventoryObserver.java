package shoppingcart.observers;

import java.util.Map;

public interface InventoryObserver {
    void update(Map<String, Integer> inventory);
}
