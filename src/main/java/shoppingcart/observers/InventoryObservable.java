package shoppingcart.observers;

public interface InventoryObservable {
    void addObserver(InventoryObserver observer);

    void removeObserver(InventoryObserver observer);

    void notifyObservers();
}
