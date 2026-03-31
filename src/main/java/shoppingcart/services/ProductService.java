package shoppingcart.services;

import shoppingcart.models.Product;

import java.util.Map;

public class ProductService {
    private final Map<String, Product> productRegistry;

    public ProductService(Map<String, Product> productRegistry) {
        this.productRegistry = productRegistry;
    }

    public Product getProduct(String productId) {
        return productRegistry.entrySet().stream()
                .filter(p -> p.getKey().equals(productId))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product " + productId + " not found in the registry."));
    }

    public void addProduct(Product product) {
        if (productRegistry.containsKey(product.getProductId()))
            throw new IllegalArgumentException("Product " + product.getProductId() + " already exists!");
        productRegistry.put(product.getProductId(), product);
    }

    public void removeProduct(String productId) {
        if (!productRegistry.containsKey(productId))
            throw new IllegalArgumentException("Product " + productId + " not found!");
        productRegistry.remove(productId);
    }
}
