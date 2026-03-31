package shoppingcart.services;

import shoppingcart.models.Product;
import shoppingcart.observers.InventoryObservable;
import shoppingcart.observers.InventoryObserver;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryService implements InventoryObservable {
    private final Map<String, Integer> inventory = new HashMap<>();
    private final List<InventoryObserver> observers = new ArrayList<>();

    public void addInventory(Product product, int qty) {
        inventory.put(product.getProductId(), qty);
    }

    public boolean hasStock(String productId, int qty) {
        int stock = inventory.entrySet().stream()
                .filter(entry -> entry.getKey().equals(productId))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product " + productId + " not found!"));

        return stock > qty;
    }

    public void reserveStock(String productId, int qty) {
        if (!hasStock(productId, qty)) throw new IllegalArgumentException("Product is out of stock");

        inventory.merge(productId, qty, (currentQty, deduct) -> currentQty - deduct);
    }

    public void releaseStock(String productId, int qty) {
        inventory.merge(productId, qty, Integer::sum);
    }

    @Override
    public void addObserver(InventoryObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(InventoryObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers
                .forEach(observer -> observer.update(inventory));
    }
}
